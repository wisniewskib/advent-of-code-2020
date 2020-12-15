package day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.AoC;

public class RainRisk2 extends AoC{
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	public static int solve() {
		List<String>input=getInputAsList(12);		
		
		Waypoint waypoint=new Waypoint();
		
		Map<String,Integer>directions=new HashMap<>();
		directions.put("N", 0);
		directions.put("E", 0);
		directions.put("S", 0);
		directions.put("W", 0);
		
		for(String line:input) {
			String action=line.substring(0,1);
			int value=Integer.parseInt(line.substring(1));
			switch(action) {
			case"L":waypoint.turnLeft(value);break;
			case"R":waypoint.turnRight(value);break;
			case"F":{
				for(String key:directions.keySet()) {
					directions.compute(key, (k,v)->v+(waypoint.directionsMap.get(k)*value));
				}
				break;}
			default:waypoint.move(action,value);
			}
		}
		
		int sum=(directions.get("N")-directions.get("S"))+(directions.get("W")-directions.get("E"));
		
		return sum;
	}
	
	static class Waypoint{
		int x=10;
		int y=1;
		Map<String,Integer>directionsMap;
		String currentDirection="E";
		int degrees=90;
		
		public Waypoint() {
			directionsMap=new HashMap<>();
			directionsMap.put("N",0);
			directionsMap.put("E",0);
			directionsMap.put("S",0);
			directionsMap.put("W",0);
			determineDirection();
		}
		
		public void turnLeft(int turnDegrees) {
			int temp;
			for(int i=0;i<turnDegrees/90;i++) {
				temp=x;
				x=-y;
				y=temp;
			}
			determineDirection();
		}
		
		public void turnRight(int turnDegrees) {
			int temp;
			for(int i=0;i<turnDegrees/90;i++) {
				temp=x;
				x=y;
				y=-temp;
			}
			determineDirection();
		}		
		
		private void determineDirection() {
			if(x>0) {
				directionsMap.put("W",0);
				directionsMap.put("E",x);
			}else if(x<0) {
				directionsMap.put("E",0);
				directionsMap.put("W",Math.abs(x));
			}else if(x==0) {
				directionsMap.put("W",0);
				directionsMap.put("E",0);
			}			
			
			if(y>0) {
				directionsMap.put("S", 0);
				directionsMap.put("N", y);				
			}else if(y<0) {
				directionsMap.put("N",0);
				directionsMap.put("S",Math.abs(y));
			}else if(y==0) {
				directionsMap.put("S",0);
				directionsMap.put("N",0);
			}
		}

		public void move(String action,int value) {
			switch(action){
				case"N":y+=value;break;
				case"S":y-=value;break;
				case"E":x+=value;break;
				case"W":x-=value;break;
			}
			determineDirection();
		}
		
		public void printMap() {
			for(Map.Entry<String, Integer>entry:directionsMap.entrySet()) {
				System.out.println("direction: "+entry.getKey()+"       value:"+entry.getValue());
			}
		}
	}
	
}
