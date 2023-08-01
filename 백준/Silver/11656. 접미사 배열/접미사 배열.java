import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		List<String> list = new ArrayList<>();	// 접미사들을 저장할 리스트
		for(int i=0; i<input.length(); i++) {
			// 접미사 만들어주기
			// 문자열.substring(문자열의 해당 인덱스); -> 문자열의 해당 인덱스부터 문자열의 끝까지 잘라서 문자열 return 
			String makeStr = input.substring(i);	
			list.add(makeStr);	// 접미사 만든 것 리스트에 추가
		}
		
		Collections.sort(list);	// 접미사들을 저장한 리스트 오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		for(String str: list) {
			sb.append(str).append("\n");
		}
		System.out.print(sb);

	}

}
