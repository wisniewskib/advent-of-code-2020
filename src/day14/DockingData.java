package day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import aoc.AoC;

public class DockingData extends AoC {

	public static void main(String[] args) throws IOException {
//		System.out.println(solve());
//		for(String s:getAddresses("01X0X1")) {
//			System.out.println(s);
//		}
		System.out.println(solvePartTwo());
	}

	public static long solve() throws IOException {
		BufferedReader reader = getInputAsBufferedReader(14);

		Map<Integer, Long> memory = new HashMap<>();

		String line;
		String currentMask = "";
		int address;
		long value;

		while ((line = reader.readLine()) != null) {

			if (line.contains("mask")) {
				currentMask = line.split(" ")[2];
			} else {
				String[] parts = line.split("=");
				address = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
				value = Long.parseLong(parts[1].replaceAll("[^0-9]", ""));

				StringBuilder binaryRep = toBinaryRepresentation(value, currentMask.length());
				for (int i = 0; i < currentMask.length(); i++) {
					char c = currentMask.charAt(i);
					if (c != 'X') {
						binaryRep.setCharAt(i, c);
					}
				}

				value = Long.parseLong(binaryRep.toString(), 2);
				memory.put(address, value);
			}
		}

		return memory.values().stream().mapToLong(s -> s).sum();
	}
	
	public static long solvePartTwo() throws NumberFormatException, IOException {
		BufferedReader reader = getInputAsBufferedReader(14);

		Map<Long, Long> memory = new HashMap<>();

		String line;
		String currentMask = "";
		long address;
		long value;

		while ((line = reader.readLine()) != null) {

			if (line.contains("mask")) {
				currentMask = line.split(" ")[2];
			} else {
				String[] parts = line.split("=");
				address = Long.parseLong(parts[0].replaceAll("[^0-9]", ""));
				value = Long.parseLong(parts[1].replaceAll("[^0-9]", ""));
				
				StringBuilder binaryRep = toBinaryRepresentation(address, currentMask.length());
				for (int i = 0; i < currentMask.length(); i++) {
					char c = currentMask.charAt(i);
					if (c == 'X' || c == '1') {
						binaryRep.setCharAt(i, c);
					}
				}
				
				for(String s:getAddresses(binaryRep.toString())) {
					memory.put(Long.parseLong(s,2),value);
				}

			}
		}

		return memory.values().stream().mapToLong(s -> s).sum();
	}

	private static StringBuilder toBinaryRepresentation(long input, int length) {
		StringBuilder binaryRep = new StringBuilder(Long.toBinaryString(input));
		int loops = length - binaryRep.length();
		if (binaryRep.length() < length) {
			for (int i = 0; i < loops; i++) {
				binaryRep.insert(0, "0");
			}
		}
		return binaryRep;
	}
	
	private static Set<String> getAddresses(String input){
		if(input.length()==0) return new HashSet<>(Arrays.asList(""));
		
		String firstChar=input.substring(0,1);
		String restAddress=input.substring(1);
		Set<String>partialAddresses=getAddresses(restAddress);
		if(firstChar.equals("X")) {
			Set<String> addresses= new HashSet<>();
			addresses.addAll(partialAddresses.stream().map(s->"1"+s).collect(Collectors.toSet()));
			addresses.addAll(partialAddresses.stream().map(s->"0"+s).collect(Collectors.toSet()));
			return addresses;
		}else {
			return partialAddresses.stream().map(s->firstChar+s).collect(Collectors.toSet());
		}
		
	}

}
