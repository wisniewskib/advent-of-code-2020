package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.AoC;

public class SeatingSystem extends AoC {

	static char[][] seats;

	public static void main(String[] args) throws IOException {
		System.out.println(solve());
//		System.out.println(solveTest());
		
//		char[][]arr1=new char[][] {{'D','D'},{'D','D'}};
//		char[][]arr2=copyOf(arr1);
//		System.out.println(arr1[0][0]);
//		System.out.println(arr2[0][0]);
//		System.out.println(Arrays.deepEquals(arr1, arr2));
//		arr2[0][0]='L';
//		System.out.println(Arrays.deepEquals(arr1, arr2));
//		System.out.println(arr1[0][0]);
//		System.out.println(arr2[0][0]);
	}

	public static int solve() {
		seats = getInputAsStream(11).map(String::toCharArray).toArray(char[][]::new);
		char[][] copy;
			
		
		while (true){			
			copy=copyOf(seats);		
			for (int row = 0; row < copy.length; row++) {
				for (int column = 0; column < copy[0].length; column++) {
					char currentState=copy[row][column];
					int adjacentOccupied = countAdjacentOccupied(row, column);
					copy[row][column] = determineSeatState(adjacentOccupied,currentState);
				}
			}
			if(Arrays.deepEquals(copy, seats)) {
				break;
			}
			seats=copyOf(copy);
		}		

		int occupiedCount=0;
		for(char[]charArray:seats) {
			for(char c:charArray) {
				if(c=='#')occupiedCount++;
			}
		}
		
		return occupiedCount;
	}
	
	
	private static int countAdjacentOccupied(int row, int column) {
		int lastColumn = seats[0].length - 1;
		int lastRow = seats.length - 1;
		int count = 0;

		if (column > 0) // left
			count += seats[row][column - 1] == '#' ? 1 : 0;

		if (column < lastColumn) // right
			count += seats[row][column + 1] == '#' ? 1 : 0;

		if (column > 0 && row > 0) // left top
			count += seats[row - 1][column - 1] == '#' ? 1 : 0;

		if (row > 0) // top
			count += seats[row - 1][column] == '#' ? 1 : 0;

		if (column < lastColumn && row > 0) // right top
			count += seats[row - 1][column + 1] == '#' ? 1 : 0;

		if (row < lastRow) // bottom
			count += seats[row + 1][column] == '#' ? 1 : 0;

		if (row < lastRow && column > 0) // left bottom
			count += seats[row + 1][column - 1] == '#' ? 1 : 0;

		if (row < lastRow && column < lastColumn) // right bottom
			count += seats[row + 1][column + 1] == '#' ? 1 : 0;

		return count;
	}

	private static char determineSeatState(int adjacentOccupied,char currentState) {
		if(currentState=='L' && adjacentOccupied==0) {
			return '#';
		}else if(currentState=='#' && adjacentOccupied>=4) {
			return 'L';
		}
		return currentState;
	}

	private static char[][] copyOf(char[][]array){
		return Arrays.stream(array).map(char[]::clone).toArray(char[][]::new);
	}
}
