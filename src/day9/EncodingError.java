package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError {
	
	public static void main(String[] args)throws IOException {
//		System.out.println(findTheNumber());
		System.out.println(findTheNumberPartTwo());
	}

	public static long findTheNumber() throws IOException {
		long[] numbers = Files.lines(Path.of("src/input_files/input_day_9.txt")).mapToLong(Long::parseLong)
				.toArray();

		int preamble = 25;
		boolean passed=true;
		for (int i = preamble; i < numbers.length;i++) {

			//refactor to use set for better runtime
			for(int j=i-preamble;j<i;j++) {
				passed=false;
				for(int k=j;k<i;k++) {
					if(numbers[j]+numbers[k]==numbers[i]) {
						passed=true;
						break;
					}
				}
				if(passed) {
					break;
				}
			}
			
			if(!passed) {
				return numbers[i];
			}
			
		}

		return 0;
	}
	
	public static int findTheNumberPartTwo() throws IOException{
		long[] numbers = Files.lines(Path.of("src/input_files/input_day_9.txt")).mapToLong(Long::parseLong)
				.toArray();
		long target=findTheNumber();
		int start=0;
		int end=0;
		int sum=0;
		
		for(int i=start;i<numbers.length;i++) {
			sum+=numbers[i];
			if(sum>target) {
				start++;
				i=start;
				sum=0;
			}else if(sum==target) {
				end=i;
				break;
			}
		}
		
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		
		for(int i=start;i<=end;i++) {
			max=(int) Math.max(max, numbers[i]);
			min=(int) Math.min(min, numbers[i]);
		}
		
		return max+min;
	}
}
