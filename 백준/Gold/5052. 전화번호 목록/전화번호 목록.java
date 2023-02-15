import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String[] phoneNumber = new String[n];
			
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				phoneNumber[j] = st.nextToken();
			}
			
			// 문자열 배열이 ["125", "37", "123", "1244", "1234"] 인 경우
			// 문자열 오름차순 정렬하게 되면
			// ["123", "1234", "1244", "125", "37"] 으로 정렬된다
			Arrays.sort(phoneNumber);	 
			
			// 전화번호 비교했을 때 접두어 찾은 경우 NO 출력
			if(isPhonNumberContain(n, phoneNumber)) {
				sb.append("NO").append("\n");
			} 
			// 접두어 찾지 못한 경우 YES 출력
			else {
				sb.append("YES").append("\n");
			}
		}
		System.out.print(sb);

	}
	
	public static boolean isPhonNumberContain(int n, String[] phoneNumber) {
		boolean find = false;	// 접두어 찾은 경우를 판단하는 변수
		for(int i=0; i<n-1; i++) {
			// 한 번호가 다른 전화번호의 접두어인 경우
			// 예를들어 i+1 => "1234" i => "123"이면 "1234"는 "123"으로 시작하므로(접두어) true
			if(phoneNumber[i+1].startsWith(phoneNumber[i])) {
				find = true;
			}
		}
		
		return find;
	}

}