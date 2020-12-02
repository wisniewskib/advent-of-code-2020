package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PasswordPhilosophy {
	
	public static void main(String[] args) throws IOException {
		System.out.println(countValidPasswords());
	}

	public static long countValidPasswords() throws IOException {
		File file=new File("src/input_files/input_day_2.txt");
		BufferedReader reader=new BufferedReader(new FileReader(file));
//		return reader.lines().filter(isValidPasswordPartOne()).count();
		return reader.lines().filter(isValidPasswordPartTwo()).count();
	}
	
	public static Predicate<String> isValidPasswordPartOne(){
		return s->{
			String[]splitted=s.split(" ");
			int min=Integer.parseInt(splitted[0].split("-")[0]);
			int max=Integer.parseInt(splitted[0].split("-")[1]);
			long count= Arrays.stream(splitted[2].split("")).filter(c->c.equals(splitted[1].substring(0,1))).count();
			return count>=min && count<=max;
		};
	}
	
	public static Predicate<String> isValidPasswordPartTwo(){
		return s->{
			String[]splitted=s.split(" ");
			String password=splitted[2];
			int first=Integer.parseInt(splitted[0].split("-")[0]);
			int second=Integer.parseInt(splitted[0].split("-")[1]);
			char firstChar=password.charAt(first-1);
			char secondChar=password.charAt(second-1);	
			char toCheck=splitted[1].charAt(0);
			return second-1>password.length()?false:firstChar==toCheck^secondChar==toCheck;
		};
	}
}
