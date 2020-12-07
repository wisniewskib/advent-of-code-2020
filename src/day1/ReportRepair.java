package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportRepair {

	public static void main(String[] args)throws IOException {
//		System.out.println(repairReport());
		System.out.println(repairReportPartTwo());
	}
	
	public static int repairReport() throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader(new File("src/input_files/input_day_1.txt")));
		Set<Integer>expenses=reader.lines().map(Integer::parseInt).collect(Collectors.toSet());
		int result=0;
		for(int currentValue:expenses) {
			if(expenses.contains(2020-currentValue)) {
				result=currentValue*(2020-currentValue);
			}
		}
		return result;		
	}
	
	public static int repairReportPartTwo() throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader(new File("src/input_files/input_day_1.txt")));
		Set<Integer>expenses=reader.lines().map(Integer::parseInt).collect(Collectors.toSet());
		int result=0;
		for(int firstValue:expenses) {
			for(int secondValue:expenses) {
				if(expenses.contains(2020-firstValue-secondValue)) {
					result=firstValue*secondValue*(2020-firstValue-secondValue);
				}	
			}
		}
		return result;		
	}

}
