package day13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.AoC;

public class ShuttleSearch extends AoC {

	public static void main(String[] args) {
//		System.out.println(solve());
	}

	public static int solve() {
		List<String> input = getInputAsList(13);
		double timestamp = Double.parseDouble(input.get(0));
		double[] buses = Arrays.asList(input.get(1).split(","))
				.stream()
				.filter(s -> s.matches("[0-9]*"))
				.mapToDouble(Double::parseDouble)
				.toArray();
				
		Map<Integer,Integer>busesMap=new HashMap<>();
		
		double minBusId=0;
		double minMinutes=Integer.MAX_VALUE;
		
		for(double bus:buses) {
			double waitTime=Math.ceil(timestamp/bus)*bus-timestamp;
			if(waitTime<minMinutes) {
				minBusId=bus;
				minMinutes=waitTime;
			}
		}
		
		return (int)(minBusId * minMinutes);
	}

}
