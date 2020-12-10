package day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandyHaversacks {
	
	public static void main(String[] args) throws IOException{
		System.out.println(countBagsPartTwo());
	}

	private static int countBags() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_7.txt")));
		
		String patternString="\\w+\\s\\w+\\sbag";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher;
		
		Map<String,List<String>>bagMap=new HashMap<>();
		Set<String>bagSet=new HashSet<>();
		
		String line;
		while((line=reader.readLine())!=null) {			
			matcher=pattern.matcher(line);
			List<String>bags=new ArrayList<>();
			while(matcher.find()) {
				bags.add(matcher.group());
			}			
			String key=bags.get(0);
			bags.remove(0);
			bagMap.put(key, bags);			
		}

		int size=bagSet.size();
		while(true) {	
			for(Map.Entry<String,List<String>>entry:bagMap.entrySet()) {
				if(entry.getValue().contains("shiny gold bag")||entry.getValue().stream().anyMatch(bagSet::contains)) {
					bagSet.add(entry.getKey());
				}
			}
			if(bagSet.size()==size)break;
			size=bagSet.size();
		}
		
		
//		bagMap.forEach((string,list)->{
//			System.out.print("key: "+string+ "           items:[");
//			list.forEach(s->System.out.print(s+","));
//			System.out.print("]");
//			System.out.println();
//		});
		
		return bagSet.size();
	}
	
	private static int countBagsPartTwo() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_7.txt")));
		
		String patternString="(\\d+\\s)?\\w+\\s\\w+\\sbag";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher;
		
		Map<String,List<String>>bagMap=new HashMap<>();
		Set<String>bagSet=new HashSet<>();
		
		String line;
		while((line=reader.readLine())!=null) {			
			matcher=pattern.matcher(line);
			List<String>bags=new ArrayList<>();
			while(matcher.find()) {
				bags.add(matcher.group());
			}			
			String key=bags.get(0);
			bags.remove(0);
			bagMap.put(key, bags);			
		}

		int size=0;
		while(true) {
			if(size==0) {
				for(String bag:bagMap.get("shiny gold bag")) {
					bagSet.add(bag);
				}
			}
			size=bagSet.size();
			Set<String>copy=new HashSet<>(bagSet);
			for(String bag:copy) {
				if(bag.contains("no other bag"))continue;
				for(String nestedBag:bagMap.get(bag.replaceFirst("\\d+\\s",""))) {
					bagSet.add(nestedBag);
				}
			}			
			if(size==bagSet.size())break;
		}
		
		int total=1;
		
		for(String bag:bagSet) {
			if(!bag.contains("no other bag")) {
				int bagCount=getNumber(bag);
				total+=bagCount;
				for(String innerBag:bagMap.get(bag.replaceFirst("\\d+\\s",""))) {
					if(!innerBag.contains("no other bag")) {
						total+=bagCount*getNumber(innerBag);
					}
				}
			}
		}
		
		return total;
	}
	
	private static int getNumber(String input) {
		return input.matches("\\d+.*")?Integer.parseInt(input.split(" ")[0]):0;
	}

}
