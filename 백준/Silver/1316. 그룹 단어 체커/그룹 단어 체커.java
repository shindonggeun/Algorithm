import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int count = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if(groupWordChecker(str) == true) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	// 그룹단어 체크해주는 메서드
	public static boolean groupWordChecker(String str) {
		boolean[] alpabet = new boolean[26];    // 알파벳 소문자 a ~ z
		int prev = 0;
		
		for(int i=0; i<str.length(); i++) {
			int now = str.charAt(i);	// char형 -> int로 변환해서 저장(아스키코드값)
			
			// 이전 문자 아스키코드값과 현재 문자 아스키코드값이 같지 않은 경우
			if(now != prev) {
				// 현재 문자가 처음 나온 경우
				if(alpabet[now - 'a'] == false) {
					alpabet[now - 'a'] = true;
					prev = now;
				}
				// 현재 문자가 이미 나온적이 있는 경우(그룹단어 아님)
				else {
					return false;
				}
			}
			// 이전문자와 현재문자가 같은 경우
			else {
				continue;
			}
		}
		
		return true;
	}
	
}