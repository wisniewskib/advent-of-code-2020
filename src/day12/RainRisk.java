package day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.AoC;

public class RainRisk extends AoC{
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	public static int solve() {
		List<String>input=getInputAsList(12);		
		
		Direction direction=new Direction();
		
		Map<String,Integer>directions=new HashMap<>();
		directions.put("N", 0);
		directions.put("E", 0);
		directions.put("S", 0);
		directions.put("W", 0);
		
		for(String line:input) {
			String action=line.substring(0,1);
			int value=Integer.parseInt(line.substring(1));
			switch(action) {
			case"L":direction.turnLeft(value);break;
			case"R":direction.turnRight(value);break;
			case"F":directions.compute(direction.currentDirection, (k,v)->v+value);break;
			default:directions.compute(action, (k,v)->v+value);
			}
		}
		
		int sum=(directions.get("N")-directions.get("S"))+(directions.get("W")-directions.get("E"));
		
		return sum;
	}
	
	static class Direction{
		Map<Integer,String>directionsMap;
		String currentDirection="E";
		int degrees=90;
		
		public Direction() {
			this.directionsMap=new HashMap<>();
			this.directionsMap.put(0, "N");
			this.directionsMap.put(90, "E");
			this.directionsMap.put(180, "S");
			this.directionsMap.put(270, "W");
		}
		
		public void turnLeft(int turnDegrees) {
			degrees-=turnDegrees;
			if(degrees<0) {
				degrees=360-Math.abs(degrees);
			}
			currentDirection=directionsMap.get(degrees);
		}
		
		public void turnRight(int turnDegrees) {
			degrees+=turnDegrees;
			if(degrees>=360) {
				degrees=degrees-360;
			}
			currentDirection=directionsMap.get(degrees);
		}		
		
	}
	
}
