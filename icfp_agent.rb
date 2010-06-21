require 'mechanize'

module Util
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
end

class ICFPAgent
  include Util

  class Car
    include Util

    attr_reader :car_id, :icfp, :car_page, :result

    def initialize(agent, car_id, car_page)
      @icfp = agent
      @car_id = car_id
      @car_page = car_page
    end

    # Description of the car.
    def description
      @description ||= @car_page.content.slice(/"roo_solution_instance"(.*?)div/,1).slice(/label.*?(\d+)/,1)
    end

    # Submit the circuit for the car. Returns
    # true or false, if circuit provided fuel or not.
    # Sets :result to the text given in either case.
    def submit_fuel(fuel)
      fuel_form = @car_page.forms[0]
      fuel_form.contents = fuel

      @result = with_retry do
        @icfp.submit(fuel_form).content.slice(/\<pre\>(.*)\<\/pre\>/m,1).gsub("\n","<br>")
      end
      
      @result =~ /Good!/
    end
  end

  attr_reader :agent
  
  def initialize
    @agent = Mechanize.new
    with_retry do 
      login_form = @agent.get('http://icfpcontest.org/icfp10/login').form('f')
      login_form.j_username = "ooplss2010"
      login_form.j_password = "1484968454509167853383152085303289771444721966896581041165450"
      @agent.submit login_form
    end
  end

  def submit(form)
    @agent.submit(form)
  end

  def safe_get(url)
    with_retry do
      @agent.get(url)
    end
  end
  
  def unsafe_get(url)
    @agent.get(url)
  rescue SystemError
  end

  def logout
    unsafe_get('http://icfpcontest.org/icfp10/static/j_spring_security_logout')
  end

  # Enumerates a list of cars.
  class CarEnum
    include Enumerable
    def initialize(icfp, car_list)
      @car_list = car_list
      @icfp = icfp
    end

    def each
      @car_list.gsub(/car.*?(\d+)/) do |__|
        car_id = $1
        car_page = @icfp.safe_get("http://icfpcontest.org/icfp10/instance/#{car_id}/solve/form")
        yield Car.new(@icfp, car_id, car_page)
      end
    end
  end

  # Return an array of all cars
  def all_cars
    CarEnum.new(self, @car_list ||= safe_get('http://nfa.imn.htwk-leipzig.de/information/cars').content)
  end
end
