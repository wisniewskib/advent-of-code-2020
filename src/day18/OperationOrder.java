package day18;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

import aoc.AoC;

public class OperationOrder extends AoC {

	public static void main(String[] args) {
//		System.out.println(solve());
		System.out.println(solvePartTwo());
	}

	public static long solve() {
		List<String> expressions = getInputAsList(18);

		long sum=0;
		
		for(String expression:expressions) {
			List<String> tokens = Stream.of(expression.replaceAll(" ", "").split("")).collect(toList());
			long result=solveExpression(tokens);
			System.out.println(expression+" = "+result);
			sum+=result;
		}
		return sum;
	}
	
	public static long solvePartTwo() {
		List<String> expressions = getInputAsList(18);

		long sum=0;
		
		for(String expression:expressions) {
			List<String> tokens = Stream.of(expression.replaceAll(" ", "").split("")).collect(toList());
			long result=solveExpressionPartTwo(tokens);
			sum+=result;
		}
		return sum;
	}

	public static long solveExpression(List<String> tokens) {

		if(!tokens.contains("(")) {
			if(tokens.size()<2) {
				return Long.parseLong(tokens.get(0));
			}
			Long result=0L;
			Long number1=null;
			Long number2=null;
			String operand =null;
			for(String c:tokens) {
				if(c.matches("\\d+")) {
					if(number1==null) {
						number1=Long.parseLong(c);
					}else {
						number2=Long.parseLong(c);
					}
				}else {
					operand=c;
				}
				
				if(number1!=null && number2!=null && operand!=null) {
					if(operand.equals("*")) {
						result=number1*number2;
					}else if(operand.equals("+")) {
						result=number1+number2;
					}
					number1=result;
					number2=null;
					operand=null;
				}
			}
			return result;
		}
		
		while (tokens.contains("(")) {
			int end = tokens.indexOf(")");
			int start = 0;
			for (int i = end; !tokens.get(i).equals("("); i--) {
				start = i;
			}
			List<String>copy=new ArrayList<>(tokens);
			tokens.subList(start-1, end+1).clear();
			tokens.add(start-1, solveExpression(copy.subList(start, end))+"");
		}	
		

		return solveExpression(tokens);
	}

	public static long solveExpressionPartTwo(List<String> tokens) {

		if(!tokens.contains("(") && tokens.contains("+") && tokens.size()>3) {
			int plusIndex=tokens.indexOf("+");
			tokens.add(plusIndex-1,"(");
			tokens.add(plusIndex+3,")");
//			tokens=Stream.of(String.join("",tokens).replaceAll("\\d+\\+\\d+","($0)").split("")).collect(toList());
		}
		
		if(!tokens.contains("(")) {
			if(tokens.size()<2) {
				return Long.parseLong(tokens.get(0));
			}
			Long result=0L;
			Long number1=null;
			Long number2=null;
			String operand =null;
			for(String c:tokens) {
				if(c.matches("\\d+")) {
					if(number1==null) {
						number1=Long.parseLong(c);
					}else {
						number2=Long.parseLong(c);
					}
				}else {
					operand=c;
				}
				
				if(number1!=null && number2!=null && operand!=null) {
					if(operand.equals("*")) {
						result=number1*number2;
					}else if(operand.equals("+")) {
						result=number1+number2;
					}
					number1=result;
					number2=null;
					operand=null;
				}
			}
			return result;
		}
		
		while (tokens.contains("(")) {
			int end = tokens.indexOf(")");
			int start = 0;
			for (int i = end; !tokens.get(i).equals("("); i--) {
				start = i;
			}
			List<String>copy=new ArrayList<>(tokens);
			tokens.subList(start-1, end+1).clear();
			tokens.add(start-1, solveExpressionPartTwo(copy.subList(start, end))+"");
		}
		
		return solveExpressionPartTwo(tokens);
	}
}
