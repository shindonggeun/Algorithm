import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// DNA 문자열 길이 입력
		int P = Integer.parseInt(st.nextToken());	// 비밀번호로 사용할 부분문자열 길이 입력
		
		String dna = br.readLine();	// DNA 문자열 입력받음
		// key: 부문문자열에 포함되어야 할 {'A', 'C', 'G', 'T'}, value: 해당 문자에 최소 개수
		Map<Character, Integer> requireMap = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		// 해당 문자에 따른 필요한 개수 맵에 저장해줌
		requireMap.put('A', Integer.parseInt(st.nextToken()));
		requireMap.put('C', Integer.parseInt(st.nextToken()));
		requireMap.put('G', Integer.parseInt(st.nextToken()));
		requireMap.put('T', Integer.parseInt(st.nextToken()));
		
		int[] alpabetCount = new int[26];	// [0] = 'A'의 개수, ..., [25] = 'Z'의 개수
		// 일단 부분문자열 길이만큼 탐색해줌
		for(int i=0; i<P; i++) {
			char ch = dna.charAt(i);
			alpabetCount[ch - 'A']++;
		}
		
		int idx = 0;	// 초기 인덱스 설정 (-1인거 주의)
		int count = 0;	// 만들 수 있는 비밀번호 종류의 수 
		// 슬라이딩 윈도우 알고리즘 이용
		// 부분문자열 길이만큼 이동시켜서 마지막 인덱스를 나타내는 N보다 작거나 같을때 까지 반복문 시행
		while(P+idx <= N) {
			if(alpabetCount[0] >= requireMap.get('A') && alpabetCount[2] >= requireMap.get('C') && alpabetCount[6] >= requireMap.get('G') && alpabetCount[19] >= requireMap.get('T')) {
				count++;
			}
			if(P+idx < N) {
				int start = dna.charAt(idx) - 65;
				alpabetCount[start]--;
				int end = dna.charAt(P+idx) - 65;
				alpabetCount[end]++;
			}
			idx++;
		}
		System.out.println(count);

	}

}
