import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "\n");
		
		String input = st.nextToken();	// 입력값
		st = new StringTokenizer(br.readLine(), "\n");	
		String compare = st.nextToken();	// 검색하려는 단어
		
		int count = 0;	// 단어가 중복된 횟수
		
		// 단어가 더이상 검색되지 않을때까지 반복
		while(input.indexOf(compare) != -1) {
			// 문자열 맨 앞에서부터 맨끝문자까지 탐색해야하므로
			// replaceFirst() 메서드에서 빈문자열로 대체하는게 아닌 다른 문자열 하나로 대체해야함!
			// 빈 문자열로 대체시 반례) input -> "aababa", compare -> "aba" 정답 1이 나와야하는데 2가 나오게 됨 
			input = input.replaceFirst(compare, "1");	// 입력값에서 해당 단어가 검색된 처음꺼만 "1"로 대체
			count++;	// 해당 단어 찾은 개수 증가
		}
		System.out.println(count);

	}

}