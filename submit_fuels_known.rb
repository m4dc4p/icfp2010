require 'rubygems'
require 'mechanize'
require 'pathname'

def with_retry
  begin
    yield
  rescue Timeout::Error
    puts "Timeout Error: retrying."
    retry
  rescue Mechanize::ResponseCodeError
    if $!.response_code =~ /503/
      puts "503 Error: retrying."
      retry
    else
      puts "Server Error: #{$!.response_code} (#{$!.class}, #{$!.response_code.class})."
    end
  rescue Object
    puts "Error: #{$!} (#{$!.class})."
  end
end  

success_cnt, failed_cnt = 0, 0

# Arguments are file continaing input circuit and 
# file containing log of IDs that previously suceeded.
input_circuit_file, car_ids_file = ARGV[0], ARGV[1]
input_circuit = open(input_circuit_file, "r").gets(nil)

lines = open(Pathname.new(car_ids_file), "r").gets(nil).collect { |l| l.split("\t")[0].strip }
car_ids = lines.slice(2, lines.length)

# Create directory to hold results, based on input file file name.
result_dir = "submit_fuel_#{Pathname.new(input_circuit_file).basename}"
if ! File.exists?(result_dir)
  Dir.mkdir result_dir
end

agent = Mechanize.new
with_retry do 
  login_form = agent.get('http://icfpcontest.org/icfp10/login').form('f')
  login_form.j_username = "ooplss2010"
  login_form.j_password = "1484968454509167853383152085303289771444721966896581041165450"
  agent.submit login_form
end

open("#{result_dir}\\failed.txt", "w") do |failed|
  failed.puts <<-HDR
CAR\tDESC\tMSG
===\t====\t===
HDR
  open("#{result_dir}\\succeeded.txt", "w") do |succeeded|
    succeeded.puts <<-HDR
CAR\tDESC\tMSG
===\t====\t===
HDR
    car_ids.each do |car_id|
      car_page = with_retry do
        agent.get("http://icfpcontest.org/icfp10/instance/#{car_id}/solve/form")
      end

      car_desc = car_page.content.slice(/"roo_solution_instance"(.*?)div/,1).slice(/label.*?(\d+)/,1)
      
      fuel_form = car_page.forms[0]
      fuel_form.contents = input_circuit
      result = with_retry do
        agent.submit(fuel_form).content.slice(/\<pre\>(.*)\<\/pre\>/m,1).gsub("\n","<br>")
      end
      
      if result =~ /Good!/
        print "*"
        success_cnt += 1
        succeeded.puts "#{car_id}\t#{car_desc}\t#{result}"
      else
        print "X"
        failed_cnt += 1
        failed.puts "#{car_id}\t#{car_desc}\t#{result}"
      end
    end
  end
end

begin
  agent.get('http://icfpcontest.org/icfp10/static/j_spring_security_logout')
rescue
end

puts "\n#{success_cnt} successful, #{failed_cnt} failed, #{success_cnt + failed_cnt} total."

