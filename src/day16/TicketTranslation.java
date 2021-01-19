package day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import aoc.AoC;

public class TicketTranslation extends AoC {

	private static String ticket;
	private static List<Range> ranges;
	private static List<Ticket> tickets;
	private static Set<Integer> fieldIndexes;

	public static void main(String[] args) throws IOException {
		System.out.println(solve());
		
		System.out.println();
		
		System.out.println(solvePartTwo());
	}

	public static int solve() throws IOException {
		BufferedReader reader = getInputAsBufferedReader(16);

		ranges = new ArrayList<>();
		tickets = new ArrayList<>();

		String line;
		boolean yourTicket = false;
		while ((line = reader.readLine()) != null) {
			if (line.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?:\\s\\d+-\\d+\\sor\\s\\d+-\\d+")) {
				extractRanges(line);
			} else if (line.matches("your ticket:")) {
				yourTicket = true;
			} else if (yourTicket) {
				ticket = line;
				yourTicket = false;
			} else if (!line.isEmpty() && !line.contains("nearby tickets:")) {
				extractValues(line);
			}
		}

		return calculateErrorRate();
	}

	public static long solvePartTwo() {
		filterInvalidTickets();		
		
		Map<String,List<Integer>> options=new HashMap<>();
		
		for (int i = 0; i < ranges.size(); i ++) {
			Range range=ranges.get(i);
			for (int j = 0; j < tickets.get(0).values.size(); j++) {
				final int valueIndex = j;				
				if (tickets.stream().allMatch(ticket ->range.isInRange(ticket.values.get(valueIndex)))){
					if(options.containsKey(range.name)) {
						options.compute(range.name, (k,v)->{
							v.add(valueIndex);
							return v;
						});
					}else {
						options.put(range.name, new ArrayList<>(Arrays.asList(valueIndex)));
					}
				}
			}
		}
		
		options = options.entrySet().stream()
			    .sorted(Comparator.comparingInt(e -> e.getValue().size()))
			    .collect(Collectors.toMap(
			        Map.Entry::getKey,
			        Map.Entry::getValue,
			        (a, b) -> { throw new AssertionError(); },
			        LinkedHashMap::new
			    )); 
		
		Set<Integer>indexesToRemove=new HashSet<>();
		Set<Integer>finalIndexes=new HashSet<>();		
				
		for(Map.Entry<String, List<Integer>>entry:options.entrySet()) {
			List<Integer> values=entry.getValue();
			values.removeAll(indexesToRemove);
			indexesToRemove.add(values.get(0));
			
			if(entry.getKey().contains("departure")) {
				finalIndexes.add(entry.getValue().get(0));
			}
		}
		
		String[] myValues=ticket.split(",");
		long result=1;
		
		for(int i:finalIndexes) {
			result=result*Integer.parseInt(myValues[i]);
		}
		
		return result;
	}

	private static void extractRanges(String line) {
		int[] numbers = Pattern.compile("\\d+").matcher(line).results().map(MatchResult::group)
				.mapToInt(Integer::parseInt).toArray();

		ranges.add(new Range(line.split(":")[0],numbers[0], numbers[1],numbers[2], numbers[3]));
	}

	private static void extractValues(String line) {
		tickets.add(new Ticket(
				Arrays.asList(line.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList())));
	}

	private static int calculateErrorRate() {
		return tickets.stream().flatMapToInt(ticket -> ticket.values.stream().mapToInt(i -> i))
				.filter(number -> ranges.stream().noneMatch(range -> range.isInRange(number))).sum();
	}

	private static void filterInvalidTickets() {
		tickets = tickets.stream()
				.filter(ticket -> ticket.values.stream()
						.allMatch(number -> ranges.stream().anyMatch(range -> range.isInRange(number))))
				.collect(Collectors.toList());
	}

	static class Range {
		String name;
		int min1;
		int max1;
		int min2;
		int max2;

		public Range(String name, int min1, int max1, int min2, int max2) {
			this.name = name;
			this.min1 = min1;
			this.max1 = max1;
			this.min2 = min2;
			this.max2 = max2;

		}

		@Override
		public String toString() {
			return name+"[min1:" + min1 + ", max1:" + max1 + ", min2:" + min2 + ", max2:" + max2 + "]";
		}

		public boolean isInRange(int number) {
			return Math.max(min1, number) == Math.min(number, max1) || Math.max(min2, number) == Math.min(number, max2);
		}
	}

	static class Ticket {
		List<Integer> values;

		public Ticket(List<Integer> values) {
			this.values = values;
		}
	}
}
