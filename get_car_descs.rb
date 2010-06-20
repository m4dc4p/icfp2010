require 'rubygems'
require 'mechanize'
require 'pathname'

cnt = 0  
# Argument must be path to succeeded.txt
# Puts descriptions in that directory, named succeeded_descs.txt
lines = open(Pathname.new(ARGV[0]), "r").gets(nil).collect { |l| l.split("\t")[0].strip }
car_ids = lines.slice(2, lines.length) 

agent = Mechanize.new
open(Pathname.new(ARGV[0]).dirname + "succeeded_descs.txt", "w") do |f|
  f.puts <<-EOS
CAR ID\tDESCRIPTION
======\t===========
EOS
  login = agent.get('http://icfpcontest.org/icfp10/login')
  login_form = login.form('f')
  
  login_form.j_username = "ooplss2010"
  login_form.j_password = "1484968454509167853383152085303289771444721966896581041165450"
  
  agent.submit login_form
  
  car_list = agent.get('http://icfpcontest.org/icfp10/score/instanceTeamCount')
  car_list.forms.each do |car_form|
    car_id = car_form.action.slice(/icfp10\/instance\/(\d+)\//, 1)
    if car_ids.include? car_id
      car_ids.delete car_id
      car_page = agent.submit car_form
      # includes label
      car_desc = car_page.content.slice(/"roo_solution_instance"(.*?)div/,1).slice(/label.*?(\d+)/,1)
      f.puts "#{car_id}\t#{car_desc}"
      print "*"
      cnt += 1
    end
  end
end

agent.get('http://icfpcontest.org/icfp10/static/j_spring_security_logout')
puts "\nFound #{cnt} cars."
puts "Did not find #{car_ids.length} cars: #{car_ids.join(",")}." if ! car_ids.empty? 
