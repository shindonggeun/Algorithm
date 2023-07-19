import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		input = input.toUpperCase();	// 문자열 입력값 모두다 대문자로 변환
		int[] alpabet = new int[26];	// A ~ Z까지 알파벳 26개 (0 -> A, ... 25 -> Z)
		
		// 문자열 입력받은거 하나하나씩 탐색
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			// 문자가 아스키코드값 65 ~ 90 사이의 있는 경우 ('A' ~ 'Z')
			if(ch >= 65 && ch <= 90) {
				alpabet[ch - 65]++;	// 해당 알파벳 나온 횟수 증가
			}
		}
		
		int max = 0;
		char ch = '?';
		
		// 알파벳 나온횟수들 다 찾아보기 위해 탐색하기
		for(int i=0; i<alpabet.length; i++) {
			// 알파벳의 나온 횟수가 최대 나온횟수보다 큰 경우
			if(alpabet[i] > max) {
				max = alpabet[i];	// 최대나온 횟수 갱신
				ch = (char) (i + 65);	// 최대로 나온 알파벳 저장
			}
			// 최대나온 횟수와 알파벳이 나온 횟수가 같으면 최대로 나온 알파벳이 중복이므로
			else if(alpabet[i] == max) {
				ch = '?';	// 물음표로 갱신
			}
		}
		System.out.println(ch);
	}

}
