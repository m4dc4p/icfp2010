require 'rubygems'
require 'icfp_agent'
require 'mechanize'
require 'pathname'

success_cnt, failed_cnt = 0, 0
# Logged in
input_circuit = open(ARGV[0], "r").gets(nil)
result_dir = "submit_fuel_#{Pathname.new(ARGV[0]).basename}"
if ! File.exists?(result_dir)
  Dir.mkdir result_dir
end

icfp = ICFPAgent.new

open("#{result_dir}\\failed.txt", "w") do |failed|
  failed.puts <<-HDR
CAR\tMSG
===\t====\t===
HDR
  open("#{result_dir}\\succeeded.txt", "w") do |succeeded|
    succeeded.puts <<-HDR
CAR\tMSG
===\t====\t===
HDR
    icfp.all_cars.each do |car|
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

icfp.logout

puts "\n#{success_cnt} successful, #{failed_cnt} failed, #{success_cnt + failed_cnt} total."
