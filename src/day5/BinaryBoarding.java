package day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class BinaryBoarding {
	
	public static void main(String[] args) throws IOException{
//		System.out.println(findMaxID());
		System.out.println(findYourSeat());
	}

	public static int findMaxID() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_5.txt")));
		return reader.lines().mapToInt(s->decodeSeatID(decodeRow(s),decodeColumn(s))).max().getAsInt();
	}
	
	public static int findYourSeat()throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/input_files/input_day_5.txt")));
		Set<Integer>seats= reader.lines().map(s->decodeSeatID(decodeRow(s),decodeColumn(s))).collect(Collectors.toSet());
		int yourSeat=0;
		int max=findMaxID();
		for(int i=0;i<max;i++) {
			if(!seats.contains(i)&&seats.contains(i-1)&&seats.contains(i+1)) {
				yourSeat=i;
			}
		}
		return yourSeat;
		
	}
	
	public static int decodeRow(String boardingPass) {
		char[] encodedRow=boardingPass.substring(0,7).toCharArray();
		int min=0;
		int max=127;
		for(char c:encodedRow) {
			if(c=='F') {
				max=max-(max-min)/2-1;
			}else if(c=='B') {
				min=min+(max-min)/2+1;
			}
		}
		return encodedRow[6]=='F'?max:min;
	}
	
	public static int decodeColumn(String boardingPass) {
		char[] encodedColumn=boardingPass.substring(7).toCharArray();
		int min=0;
		int max=7;
		for(char c:encodedColumn) {
			if(c=='L') {
				max=max-(max-min)/2-1;
			}else if(c=='R') {
				min=min+(max-min)/2+1;
			}
		}
		return encodedColumn[2]=='L'?max:min;
	}
	
	public static int decodeSeatID(int row,int column) {
		return row*8+column;
	}
	
}
