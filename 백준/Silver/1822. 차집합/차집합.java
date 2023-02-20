import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<Integer> set = new HashSet<>();		// 집합 A가 가진 원소들 목록 set
		st = new StringTokenizer(br.readLine());
		// 집합 A가 가진 원소들 저장
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			set.add(num);
		}
		
		st = new StringTokenizer(br.readLine());
		// 집합 B가 가진 원소들 입력받기
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 집합 B의 원소가 집합 A에 있으면
			if(set.contains(num)) {
				set.remove(num);	// 집합 A에서 해당 원소 삭제
			}
		}
		List<Integer> list = new ArrayList<>(set);	// 집합 A - B인 원소 모음(차집합) 
		Collections.sort(list);		// 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		
		System.out.println(set.size());				// 차집합 원소 개수 출력
		
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);

	}

}
