package day10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.AoC;

public class AdapterArray extends AoC {
	
	static Map<String,Long>cache=new HashMap<>();

	public static void main(String[] args) {
//		System.out.println(solve());
		System.out.println(solvePartTwo());
	}

	public static int solve() {
		int[] adapters = getInputAsStream(10).mapToInt(Integer::parseInt).sorted().toArray();

		int oneJolt = adapters[0] == 1 ? 1 : 0;
		int threeJolts = adapters[0] == 3 ? 2 : 1;

		for (int i = 0; i < adapters.length - 1; i++) {
			int diff = adapters[i + 1] - adapters[i];
			if (diff == 1) {
				oneJolt++;
			} else if (diff == 3) {
				threeJolts++;
			}
		}

		return oneJolt * threeJolts;
	}

	public static long solvePartTwo() {
		List<Integer> inputs = getInputAsStream(10).map(Integer::parseInt).sorted().collect(Collectors.toList());
		inputs.add(0, 0);
		inputs.add(inputs.get(inputs.size() - 1) + 3);
		return getArrangements(inputs);
	}

	public static long getArrangements(List<Integer> inputs) {
		if (inputs.size() == 1)
			return 1;

		long arrangements = 0;
		int index = 1;
		int current = inputs.get(0);
		while (inputs.size() > index && inputs.get(index) - current < 4) {
			List<Integer> subList = inputs.subList(index, inputs.size());
			String subListStr = Arrays.toString(subList.toArray(new Integer[0]));

			if (cache.containsKey(subListStr)) {
				arrangements += cache.get(subListStr);
			} else {
				long subArrangements = getArrangements(subList);
				cache.put(subListStr, subArrangements);
				arrangements += subArrangements;
			}
			index++;
		}
		return arrangements;
	}

}
