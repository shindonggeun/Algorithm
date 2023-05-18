import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 묘목의 수
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			// 각 나무가 다 자라는데 며칠이 걸리는지 입력받아서 리스트에 저장
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list, Collections.reverseOrder());	// 내림차순 정렬
		
		// 묘목 자라는데 무조건 소요되는 시간 2일 (나무를 사고 자라는데 걸리는 시간)
		int cnt = 2;	// 묘목 구입한 날짜 1일 + 다 자란날 1일 (최소 2일)
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			answer = Math.max(answer, cnt + list.get(i));
			cnt++;	// 기본시간 증가
		}
		
		System.out.println(answer);
	}

}
