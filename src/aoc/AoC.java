package aoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public abstract class AoC {
	
	public static Stream<String> getInputAsStream(int day)   {
		try {
			return Files.lines(Path.of("src/input_files/input_day_"+day+".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getInputAsList(int day)  {
		try {
			return Files.readAllLines(Path.of("src/input_files/input_day_"+day+".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedReader getInputAsBufferedReader(int day)   {
		try {
			return new BufferedReader(new FileReader(new File("src/input_files/input_day_"+day+".txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
