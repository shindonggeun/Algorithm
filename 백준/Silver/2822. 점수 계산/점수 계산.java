import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 문제에서 모든 문제에대한 점수 서로 다르다고 했으므로
		Map<Integer, Integer> map = new HashMap<>();	// key: 점수, value: 문제 번호
		
		for(int i=1; i<=8; i++) {
			st = new StringTokenizer(br.readLine());
			
			map.put(Integer.parseInt(st.nextToken()), i);
		}
		
		List<Integer> scoreList = new ArrayList<>(map.keySet());	// 문제 점수들 담은 list
		Collections.sort(scoreList, Collections.reverseOrder());	// 점수 내림차순 정렬
		List<Integer> numList = new ArrayList<>();	// 상위 5문제 번호 담을 리스트
		
		int sum = 0;
		// 문제 총점 계산(점수 가장 높은 5문제들)
		for(int i=0; i<5; i++) {
			int score = scoreList.get(i);
			sum+=score;
			numList.add(map.get(score));
		}
		
		Collections.sort(numList);	// 문제 번호 담은 리스트들 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		
		for(int i: numList) {
			sb.append(i + " ");
		}
		System.out.println(sum);
		System.out.println(sb);

	}

}