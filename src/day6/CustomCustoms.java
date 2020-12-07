package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomCustoms {

	public static void main(String[] args) throws IOException{
		System.out.println(countAnswersPartTwo());
	}
	
	public static long countAnswers() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_6.txt")));
		String line;
		int index=0;
		ArrayList<String>groupedLines=new ArrayList<>();
		while((line=reader.readLine())!=null) {
			if(groupedLines.size()<=index) {
				groupedLines.add(line);
			}else {
				groupedLines.set(index, groupedLines.get(index)+line);
			}
			if(line.isBlank()) {
				index++;
			}
		}
		return groupedLines.stream().mapToLong(group->Arrays.stream(group.split("")).distinct().count()).sum();
	}
	
	public static long countAnswersPartTwo() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_6.txt")));
		String line;
		int index=0;
		int pplCount=0;
		int answers=0;
		ArrayList<Map<Character,Integer>>groups=new ArrayList<>();
		
		while((line=reader.readLine())!=null) {
			char[]chars=line.toCharArray();
			
			if(groups.size()<=index) {
				groups.add(new HashMap<>());
				for(char c:chars) {
					groups.get(index).put(c, 1);
				}
			}else {
				Map<Character,Integer>map=groups.get(index);
				for(char c:chars) {
					if(map.containsKey(c)) {
						map.put(c, map.get(c)+1);
					}
				}
				pplCount++;
				
			}
			if(line.isBlank()) {
				Map<Character,Integer>map=groups.get(index);
				for(Map.Entry<Character, Integer>entry:map.entrySet()) {
					if(entry.getValue()==pplCount) {
						answers++;
					}
				}				
				index++;
				pplCount=0;
			}
		}
		
		Map<Character,Integer>map=groups.get(index);
		for(Map.Entry<Character, Integer>entry:map.entrySet()) {
			if(entry.getValue()==pplCount+1) {
				answers++;
			}
		}
		return answers;
	}
	
	
}
