import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> set = new HashSet<>();	// 색상 조합을 담을 HashSet 선언 및 생성 (중복 허용 X)ㄴ
		String[] input = new String[4];	// 입력값들을 담을 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		input[0] = st.nextToken();	// 아빠 새의 몸통 색 입력
		input[1] = st.nextToken();	// 아빠 새의 꼬리 색 입력
		st = new StringTokenizer(br.readLine());
		input[2] = st.nextToken();	// 엄마 새의 몸통 색 입력
		input[3] = st.nextToken();	// 엄마 새의 꼬리 색 입력 
		
		// 완전탐색 이용
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input.length; j++) {
				String color = input[i] + " " + input[j];
				set.add(color);	// 해당 색상 조합 HashSet에 저장
			}
		}
		List<String> colorList = new ArrayList<>(set);	// HashSet을 ArrayList로 변환
		Collections.sort(colorList);	// 색상 사전순으로 정렬
		
		StringBuilder sb = new StringBuilder();
		// 색상 사전순으로 출력
		for(String color: colorList) {
			sb.append(color).append("\n");
		}
		System.out.print(sb);
		

	}

}
