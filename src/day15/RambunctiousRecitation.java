package day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RambunctiousRecitation {

	private static final String INPUT = "13,0,10,12,1,5,8";
//	private static final String INPUT="0,3,6";

	public static void main(String[] args) {
		System.out.println(solve());
		System.out.println(solvePartTwo());
	}

	public static int solve() {
		List<Integer> spokenNumbers = Arrays.asList(INPUT.split(",")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());

		for (int i = spokenNumbers.size() - 1; i < 2019; i++) {
			int lastSpokenNumber = spokenNumbers.get(i);
			int nextNumber = 0;
			for (int j = spokenNumbers.size() - 2; j >= 0; j--) {
				if (spokenNumbers.get(j) == lastSpokenNumber) {
					nextNumber = i - j;
					break;
				}
			}
			spokenNumbers.add(nextNumber);
		}
		System.out.println(spokenNumbers.size());
		return spokenNumbers.get(spokenNumbers.size() - 1);
	}

	public static int solvePartTwo() {
		String[] startingNumbers = INPUT.split(",");
		Map<Integer, List<Integer>> spokenNumbers = new HashMap<>();

		for (int i = 0; i < startingNumbers.length; i++) {
			spokenNumbers.put(Integer.parseInt(startingNumbers[i]), new ArrayList<>(Arrays.asList(i)));
		}

		int lastSpokenNumber = Integer.parseInt(startingNumbers[startingNumbers.length - 1]);
		int count = startingNumbers.length-1;

		for (int i = startingNumbers.length - 1; i < 30000000-1; i++) {
			if(!spokenNumbers.containsKey(lastSpokenNumber)) {
				spokenNumbers.put(lastSpokenNumber, new ArrayList<>(Arrays.asList(i)));
				lastSpokenNumber=0;
			}else{
				List<Integer> indexes=spokenNumbers.get(lastSpokenNumber);
				int lastIndex=indexes.get(indexes.size()-1);
				indexes.add(i);
				spokenNumbers.computeIfPresent(lastSpokenNumber, (k,v)->indexes);
				lastSpokenNumber=i-lastIndex;
			}
		}
		
		return lastSpokenNumber;
	}

}
