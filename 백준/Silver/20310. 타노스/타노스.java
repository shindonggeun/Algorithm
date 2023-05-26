import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		String zero = input.replace("1", "");	// 입력된 문자열에서 1은 모두 빈문자열로 치환
		String one = input.replace("0", "");	// 입력된 문자열에서 0은 모두 빈문자열로 치환
		char[] arr = input.toCharArray();	// 문자열에서 문자 하나씩 탐색하기 위해 char 배열로 변환
		
		int zeroCount = zero.length();	// 입력된 문자열에서 0의 개수
		int oneCount = one.length();	// 입력된 문자열에서 1의 개수
		
		// 개수 절반으로 만들어주기 (타노스 하기 위해)
		zeroCount /= 2;	
		oneCount /= 2;	
		
		// 그리디하게 풀자 
		// 입력된 문자열을 타노스해서 사전순으로 가장 빠르게 만들어야하므로
		// 문자열 왼쪽에서부터 오른쪽으로 탐색할 때(-> 방향)으로 탐색할 때는 1을 삭제
		// 문자열 오른쪽에서부터 왼쪽으로 탐색할 때(<- 방향)으로 탐색할 떄는 0을 삭제
		
		for(int i=0; i<input.length() && oneCount!=0; i++) {
			if(arr[i] == '1') {
				oneCount--;
				arr[i] = ' ';
			}
		}
		
		for(int i=input.length()-1; i>=0 && zeroCount!=0; i--) {
			if(arr[i] == '0') {
				zeroCount--;
				arr[i] = ' ';
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length(); i++) {
			if(arr[i] != ' ') {
				sb.append(arr[i]);
			}
		}
		System.out.println(sb);

	}

}
