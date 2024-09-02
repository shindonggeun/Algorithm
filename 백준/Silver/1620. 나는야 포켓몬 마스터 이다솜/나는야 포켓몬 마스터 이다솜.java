import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 포켓몬의 개수
	static int M; // 맞춰야하는 문제 개수
	// 해당 번호에 맞는 포켓몬의 이름
	static Map<Integer, String> numberMap; // key: 해당 포켓몬 번호, value: 포켓몬 이름
	// 해당 포멧몬 이름에 맞는 번호
	static Map<String, Integer> nameMap; // key: 해당 포켓몬 이름, value: 포켓몬 번호

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 해시맵 초기화
		numberMap = new HashMap<>();
		nameMap = new HashMap<>();
		
		// 해당 번호 및 포켓몬 이름 각 해시맵에 저장
		for (int i=1; i<=N; i++) {
			String name = br.readLine();
			numberMap.put(i, name);
			nameMap.put(name, i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			String input = br.readLine(); // 문제 입력
			char ch = input.charAt(0); // 해당 문제의 첫번째 글자 추출
			
			// 해당 글자가 숫자인 경우
			if (Character.isDigit(ch)) {
				int number = Integer.parseInt(input); // 해당 문제의 숫자(번호) 추출
				String name = numberMap.get(number); // 해당 번호에 따른 포켓몬 이름 해시맵에서 검색
				
				sb.append(name);
			}
			// 해당 글자가 숫자가 아닌 경우
			else {
				int number = nameMap.get(input); // 해당 문제의 이름에 따른 포켓몬 번호 해시맵에서 검색
				
				sb.append(number);
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}