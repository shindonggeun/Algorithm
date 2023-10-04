import java.util.*;
import java.io.*;

public class Main {
	
	static int count = 0;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String pattern = br.readLine();
		kmp(input, pattern);
		
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	public static int[] makeTable(String pattern) {
		int length = pattern.length();
		int[] table = new int[length];
		
		int idx = 0;
		for(int i=1; i<length; i++) {
			while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;
				table[i] = idx;
			}
		}
		return table;
	}
	
	public static void kmp(String input, String pattern) {
		int[] table = makeTable(pattern);
		
		int inputLength = input.length();
		int patternLength = pattern.length();
		
		int idx = 0;
		for(int i=0; i<inputLength; i++) {
			while(idx > 0 && input.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(input.charAt(i) == pattern.charAt(idx)) {
				if(idx == patternLength-1) {
					count++;
					list.add(i-idx+1);
					idx = table[idx];
				}
				else {
					idx += 1;
				}
			}
		}
	}

}
