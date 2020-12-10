package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HandheldHalting {

	public static void main(String[] args) throws IOException {
//		System.out.println(getAccumulatorValue());
		System.out.println(getAccumulatorValuePartTwo());
//		System.out.println(nopjmp("nop -298"));
	}

	public static int getAccumulatorValue() throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/input_files/input_day_8.txt"));
		int acc = 0;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			System.out.println(line);
			if (line.split(" ").length > 2)
				break;

			int value = Integer.parseInt(line.split(" ")[1]);
			switch (line.split(" ")[0]) {
			case "acc":
				lines.set(i, line + " visited");
				acc += value;
				break;
			case "jmp":
				lines.set(i, line + " visited");
				i += value - 1;
				break;
			}
		}

		return acc;
	}

	public static int getAccumulatorValuePartTwo() throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/input_files/input_day_8.txt"));
		List<String> copy=new ArrayList<>(lines);
		int acc = 0;
		int i=0;
		int j=0;
		
		while(j<lines.size()) {
			
			if(copy.get(j).contains("nop")||copy.get(j).contains("jmp")) {
				copy.set(j, nopjmp(copy.get(j)));
			}
			i=0;
			while (i<lines.size()) {
				String line = copy.get(i);
				String[]lineArr=line.split(" ");
				
				if (lineArr.length > 2) {
					copy.clear();
					copy.addAll(lines);
					j++;
					break;
				}
					

				int value = Integer.parseInt(lineArr[1]);
				switch (lineArr[0]) {
				case "acc":
					copy.set(i, line + " visited");
					acc += value;
					i++;
					break;
				case "jmp":
					copy.set(i, line + " visited");
					i += value;
					break;
				case "nop":
					i++;
					break;
				}
			}
			System.out.println(acc);
			if(i==lines.size()) {
				break;
			}else {
				acc=0;
				
			}
		}		

		return acc;
	}
	
	private static String nopjmp(String input) {
		if(input.contains("nop")) {
			return input.replaceFirst("nop", "jmp");
		}else if(input.contains("jmp")) {
			return input.replaceFirst("jmp", "nop");
		}
		return input;
	}

}
