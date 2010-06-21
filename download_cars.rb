require 'rubygems'
require 'mechanize'
require 'pathname'

agent = Mechanize.new
car_form = agent.get('http://nfa.imn.htwk-leipzig.de/recent_cars/#hotspot').forms[0]
cnt = 0  
open("cars.txt", "w") do |f|
  f.puts <<-EOS
CAR ID\tDESCRIPTION
======\t===========
EOS
  car_id = 1
  found = true
  while found 
    car_form["G0"] = car_id
    content = agent.submit(car_form).content
    found = false
    content.gsub(/\<pre.*?\>\((\d+),.*?,\&quot\;\d+\&quot\;\)/m) do |car|
      car_id = $1
      f.puts "#{car_id}\t" # don't store descriptions now
      found = true
      print "*"
      cnt += 1
    end
    car_id = car_id.to_i + 1
  end
end

puts "\n#{cnt} cars."
