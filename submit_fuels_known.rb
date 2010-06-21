require 'rubygems'
require 'icfp_agent'
require 'mechanize'
require 'pathname'

def submit_fuel(input_circuit_file)
  success_cnt, failed_cnt = 0, 0

  result_dir = "submit_fuel_" + Pathname.new(input_circuit_file).basename
  input_circuit = open(input_circuit_file, "r").gets(nil)

  car_ids = []
  if File.exists?("#{result_dir}\\succeeded.txt")
    lines = open(Pathname.new("#{result_dir}\\succeeded.txt"), "r").gets(nil).collect { |l| l.split("\t")[0].strip }
    car_ids = lines.slice(2, lines.length)
  end
  
  if File.exists?("#{result_dir}\\failed.txt")
    lines = open(Pathname.new("#{result_dir}\\failed.txt"), "r").gets(nil).collect { |l| l.split("\t")[0].strip }
    car_ids.concat(lines.slice(2, lines.length))
  end

  # Create directory to hold results, based on input file file name.
  if ! File.exists?(result_dir)
    Dir.mkdir result_dir
  end

  icfp = ICFPAgent.new

  open("#{result_dir}\\failed.txt", "w") do |failed|
    failed.puts <<-HDR
CAR\tMSG
===\t===
HDR
    open("#{result_dir}\\succeeded.txt", "w") do |succeeded|
      succeeded.puts <<-HDR
CAR\tMSG
===\t===
HDR
      icfp.all_cars.each do |car|
        if ! car_ids.include? car.car_id
          if car.submit_fuel(input_circuit)
            print "*"
            success_cnt += 1
            succeeded.puts "#{car.car_id}\t#{car.result}"
          else
            print "X"
            failed_cnt += 1
            failed.puts "#{car.car_id}\t#{car.result}"
          end
        end
      end
    end
  end

  icfp.logout
  puts "\n#{Pathname.new(input_circuit_file).basename}: #{success_cnt} successful, #{failed_cnt} failed, #{success_cnt + failed_cnt} new cars total."
  success_cnt + failed_cnt
end

script_total = 0
CONTEST_END = Time.local(2010, 6, 21, 5, 30)

at_exit do
  puts "Total cars: #{script_total}"
end

while true
  run_total = 0
  Dir.new('solved_cars').each do |file|
    if File.fnmatch('*.cir', file)
      puts "submitting #{file}."
      run_total += submit_fuel "solved_cars\\#{file}"
      script_total += run_total
    end
  end

  if Time.now > CONTEST_END
    puts "Contest is over!"
    break
  end

  if run_total == 0
    puts "no new cars; sleeping for 10 minutes."
    sleep 600
  else
    puts "submitted #{run_total} cars."
  end
  
  puts "downloading new cars."
  `ruby download_cars.rb`
end

