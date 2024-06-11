import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static String[] wordArr;
	static boolean[] alphabet;
	static int maxReadCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		wordArr = new String[N];
		alphabet = new boolean[26]; // [0] ~ [25] => 'a' ~ 'z'
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			wordArr[i] = input.replaceAll("anta|tica", "");
		}
		
		if (K < 5) {
			System.out.println(0);
			return;
		}
		
		alphabet['a' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		alphabet['t' - 'a'] = true;
		
		maxReadCount = Integer.MIN_VALUE;
		selectAlphabet(0, 0);
		
		System.out.println(maxReadCount);
	}
	
	public static void selectAlphabet(int depth, int alphabetIdx) {
		if (depth == K - 5) {
			int count = countReadWord();
			maxReadCount = Math.max(maxReadCount, count);
			return;
		}
		
		// 알파벳 소문자들 탐색
		for (int i=alphabetIdx; i<26; i++) {
			if (!alphabet[i]) {
				alphabet[i] = true;
				selectAlphabet(depth+1, i+1);
				alphabet[i] = false;
			}
		}
	}
	
	public static int countReadWord() {
		int count = 0;
		
		for (String word: wordArr) {
			boolean isRead = true;
			for (int i=0; i<word.length(); i++) {
				char ch = word.charAt(i);
				if (!alphabet[ch - 'a']) {
					isRead = false;
					break;
				}
			}
			
			if (isRead) {
				count++;
			}
		}
		
		return count;
	}

}