package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PassportProcessing {

	public static void main(String[] args) throws IOException {
		System.out.println(processPasswords());
	}

	public static int processPasswords() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_4.txt")));
		ArrayList<Map<String, String>> passports = new ArrayList<>();
		
		String line;
		int index = 0;
		while ((line = reader.readLine()) != null) {
			if (line.isEmpty()) {
				index++;
				continue;
			}
			if (passports.isEmpty() || passports.size()<=index) {
				passports.add(lineToMap(line));
			} else {
				passports.get(index).putAll(lineToMap(line));
			}
		}
		
		int count=0;		
		for(Map<String,String> passport:passports) {
			if(isPassportValid(passport))
				count++;
		}
		return count;
	}

	private static Map<String, String> lineToMap(String line) {
		return Arrays.stream(line.split(" ")).collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));
	}
	
	private static boolean isPassportValid(Map<String,String>passport) {
		if(passport.size()<7 || passport.size()==7 && passport.containsKey("cid")) {
			return false;
		}
		
		int byr=Integer.parseInt(passport.get("byr"));
		if(byr<1920 || byr>2002) {
			return false;
		}
		
		int iyr=Integer.parseInt(passport.get("iyr"));
		if(iyr<2010 || iyr>2020) {
			return false;
		}
		
		int eyr=Integer.parseInt(passport.get("eyr"));
		if(eyr<2020 || eyr>2030) {
			return false;
		}
		
		String unit=passport.get("hgt").replaceAll("\\d", "");
		int value=Integer.parseInt(passport.get("hgt").replaceAll("[a-zA-Z]", ""));
		if((unit.equals("cm") && (value<150 || value>193))||(unit.equals("in") && (value<59 || value>76)) || unit.isBlank()) {
			return false;
		}
		
		String hcl=passport.get("hcl");
		if(!hcl.matches("#[0-9a-f]{6}")) {
			return false;
		}
		
		String[]allowedEcl= {"amb","blu","brn","gry","grn","hzl","oth"};
		String ecl=passport.get("ecl");
		if(!Arrays.stream(allowedEcl).anyMatch(s->s.equals(ecl))) {
			return false;
		}
		
		String pid=passport.get("pid");
		if(!pid.matches("[0-9]{9}")) {
			return false;
		}
		
		return true;
		
	}

}
