require 'rubygems'
require 'mechanize'

agent = Mechanize.new
login = agent.get('http://icfpcontest.org/icfp10/login')
login_form = login.form('f')

login_form.j_username = "ooplss2010"
login_form.j_password = "1484968454509167853383152085303289771444721966896581041165450"

agent.submit login_form

# Logged in

open("cars.txt", "w") do |f|
  f.puts <<-EOS
CAR ID\tDESCRIPTION
======\t===========
EOS
  
  car_list = agent.get('http://icfpcontest.org/icfp10/score/instanceTeamCount')
  car_list.forms.each do |car_form|
    # car_list.forms[0].action.slice(/icfp10\/instance\/(\d+)\//, 1)
    car_id = car_form.action.slice(/icfp10\/instance\/(\d+)\//, 1)
    car_page = agent.submit car_form
    # includes label
    car_desc = car_page.content.slice(/"roo_solution_instance"(.*?)div/,1).slice(/label.*?(\d+)/,1)
    f.puts "#{car_id}\t#{car_desc}"
    print "*"
    sleep 0.25
  end
end



