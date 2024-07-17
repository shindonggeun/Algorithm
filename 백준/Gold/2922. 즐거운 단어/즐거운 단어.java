import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static char[] wordArr;
	static long enjoyWordCount;
	static char[] moArr = {'A', 'E', 'I', 'O', 'U'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		N = input.length();
		wordArr = input.toCharArray();
		enjoyWordCount = 0;
		
		generateEnjoyWord(0, 0, 0, 1, 1, input.contains("L"));
		
		System.out.println(enjoyWordCount);
	}
	
	public static void generateEnjoyWord(int depth, long continuousJaCount, long continuousMoCount, long lineJaCount, long lineMoCount, boolean hasL) {
		if (continuousJaCount == 3 || continuousMoCount == 3) {
			return;
		}
		
		if (depth == N) {
			if (hasL) {
				enjoyWordCount += lineJaCount * lineMoCount;
			}
			return;
		}
		
		char ch = wordArr[depth];
		
		if (ch == '_') {
			generateEnjoyWord(depth+1, 0, continuousMoCount+1, lineJaCount, lineMoCount * 5, hasL);
			
			generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount*20, lineMoCount, hasL);
			
			generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount, lineMoCount, true);
		}
		else {
			if (checkMo(ch)) {
				generateEnjoyWord(depth+1, 0, continuousMoCount+1, lineJaCount, lineMoCount, hasL);
			}
			else {
				generateEnjoyWord(depth+1, continuousJaCount+1, 0, lineJaCount, lineMoCount, hasL);
			}
		}
	}
	
	public static boolean checkMo(char ch) {
		for (char mo : moArr) {
			if (ch == mo) {
				return true;
			}
		}
		return false;
	}
}
