package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class TobogganTrajectory {
	
	public static void main(String[] args) throws IOException {
		System.out.println(calculateEncounteredTrees());
		System.out.println(calculateEncounteredTreesPartTwo(1,1));
	}
	
	public static int calculateEncounteredTrees() throws IOException {
		BufferedReader reader=new BufferedReader(new FileReader(new File("src/input_files/input_day_3.txt")));
		String[]map=reader.lines().toArray(String[]::new);
		int x=0;
		int y=0;
		int count=0;
		int width=map[0].length()-1;
		while(y<map.length-1) {
			if(x+3>width)
				x=x-width+2;
			else
				x+=3;
			y++;
			if(map[y].charAt(x)=='#')
				count++;
		}
		return count;
	}
	
	public static int calculateEncounteredTreesPartTwo(int right,int down) throws IOException {
		BufferedReader reader=new BufferedReader(new FileReader(new File("src/input_files/input_day_3.txt")));
		String[]map=reader.lines().toArray(String[]::new);
		int x=0;
		int y=0;
		int count=0;
		int width=map[0].length()-1;
		while(y<map.length-1) {
			if(x+right>width)
				x=x-width+right-1;
			else
				x+=right;
			y+=down;
			if(map[y].charAt(x)=='#')
				count++;
		}
		return count;
	}
	
	public static int calculateEncounteredTreesPartTwo(int right,int down,String input) throws IOException {
		String[]map=input.split("\\n");
		int x=0;
		int y=0;
		int count=0;
		int width=map[0].length()-1;
		while(y<map.length-1) {
			if(x+right>width)
				x=x-width+right-1;
			else
				x+=right;
			y+=down;
			if(map[y].charAt(x)=='#')
				count++;
		}
		return count;
	}
	
	public static int calculateEncounteredTrees(String input) throws IOException {
		String[]map=input.split("\\n");
		int x=0;
		int y=0;
		int count=0;
		int width=map[0].length()-1;
		System.out.println("width:"+width);
		while(y<map.length-1) {			
			if(x+3>width)
				x=x-width+2;
			else
				x+=3;
			y++;
			if(map[y].charAt(x)=='#')
				count++;
		}
		return count;
	}

}
