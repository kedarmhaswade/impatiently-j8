#!/usr/bin/env ruby
require 'rubygems'
require 'nokogiri'
require 'open-uri'
# script to parse wikipedia page on US cities
page = Nokogiri::HTML(open("https://en.wikipedia.org/wiki/List_of_United_States_cities_by_population"))
File.open("cities.csv", "w") do |f|
  page.css('#mw-content-text > table:nth-child(17) > tr').each do |tr|
    tds = tr.css("td")
    if tds[1] and tds[2] and tds[3]
      f.puts "#{tds[1].text.gsub(/\[.*\]/, '')}, #{tds[2].text}, #{tds[3].text.gsub(',', '')}"
    end
  end
end
