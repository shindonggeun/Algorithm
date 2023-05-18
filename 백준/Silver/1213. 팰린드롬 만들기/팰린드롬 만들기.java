import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		String input = st.nextToken();
		int[] alphabet = new int[26];	// [0] = 'A'의 개수, ... [25] = 'Z'의 개수
		
		for(int i=0; i<input.length(); i++) {
			int idx = input.charAt(i) - 'A';
			alphabet[idx]++;	// 해당 문자 개수 증가
		}
		
		int isOneCount = 0;	// 홀수개를 가지는 알파벳의 개수
		for(int i=0; i<alphabet.length; i++) {
			// 해당 알파벳의 개수가 짝수개가 아닌경우(홀수개인 경우)
			if(alphabet[i] % 2 != 0) {
				isOneCount++;
			}
		}
		
		String answer = "";
		
		// 홀수개를 가지는 알파벳의 개수가 1보다 크면 펠린드롬 문자열 만들 수 없음
		if(isOneCount > 1) {
			answer = "I'm Sorry Hansoo";
		}
		// 펠린드롬 문자열 만들 수 있는 경우
		else {
			// 앞부문 문자열 만들어주기
			for(int i=0; i<alphabet.length; i++) {
				for(int j=0; j<alphabet[i]/2; j++) {
					sb.append((char)(i+65));
				}
			}
			
			answer += sb.toString();
			// 뒷부분 문자열 만들어주기
			String end = sb.reverse().toString();
			
			sb = new StringBuilder();
			// 홀수개의 알파벳을 펠린드롬 문자열에서 맨 가운데에 넣어주는 작업
			for(int i=0; i<alphabet.length; i++) {
				if(alphabet[i] % 2 == 1) {
					sb.append((char)(i+65));
				}
			}
			answer += sb.toString() + end;
		}
		System.out.println(answer);
		
	}

}
