import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		List<String> list = new ArrayList<>();
		
		// 브루트포스 알고리즘 이용 (완전탐색)
		for(int i=2; i<input.length(); i++) {
			for(int j=1; j<i; j++) {
				// substring(i) -> 문자열에서 index i번부터 문자열 끝까지 잘라서 문자열 값 반환
				StringBuilder sb1 = new StringBuilder(input.substring(0, j));	// 단어 나눈것 첫번째 구간
				StringBuilder sb2 = new StringBuilder(input.substring(j, i));		// 단어 나눈것 두번째 구간
				StringBuilder sb3 = new StringBuilder(input.substring(i));	//	단어 나눈것 세번째 구간 
				
				// 문자열 뒤집기 과정
				String str1 = sb1.reverse().toString();	
				String str2 = sb2.reverse().toString();
				String str3 = sb3.reverse().toString();
				
				list.add(str1 + str2 + str3);	// 만든 문자열 리스트에 추가
			}
		}
		
		Collections.sort(list);	// 문자열 리스트 오름차순 정렬
		System.out.println(list.get(0));
		
	}

}
