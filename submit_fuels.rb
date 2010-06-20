require 'rubygems'
require 'mechanize'

agent = Mechanize.new
login = agent.get('http://icfpcontest.org/icfp10/login')
login_form = login.form('f')

login_form.j_username = "ooplss2010"
login_form.j_password = "1484968454509167853383152085303289771444721966896581041165450"

agent.submit login_form

success_cnt, failed_cnt = 0, 0
# Logged in
input_circuit = open(ARGV[0], "r").gets(nil)

open("failed.txt", "w") do |failed|
  failed.puts <<-HDR
CAR\tMSG
===\t===
HDR
  open("succeeded.txt", "w") do |succeeded|
    succeeded.puts <<-HDR
CAR\tMSG
===\t===
HDR
    car_list = agent.get('http://icfpcontest.org/icfp10/score/instanceTeamCount')
    car_list.forms.each do |car_form|
      car_id = car_form.action.slice(/icfp10\/instance\/(\d+)\//, 1)
      fuel_form = agent.get("http://icfpcontest.org/icfp10/instance/#{car_id}/solve/form").forms[0]
      fuel_form.contents = input_circuit
      result = agent.submit(fuel_form).content.slice(/\<pre\>(.*)\<\/pre\>/m,1).gsub("\n","<br>")

      if result =~ /Good!/
        print "*"
        success_cnt += 1
        succeeded.puts "#{car_id}\t#{result}"
      else
        print "X"
        failed_cnt += 1
        failed.puts "#{car_id}\t#{result}"
      end
      sleep 0.1
    end
  end
end

agent.get('http://icfpcontest.org/icfp10/static/j_spring_security_logout')
puts "\n#{success_cnt} successful, #{failed_cnt} failed, #{success_cnt + failed_cnt} total."
