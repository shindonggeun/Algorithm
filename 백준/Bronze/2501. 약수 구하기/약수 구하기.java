import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// N 입력 받기(약수 구하기)
		int K = Integer.parseInt(st.nextToken());	// K 번째
		
		List<Integer> list = new ArrayList<>();
		
		// 브루트포스 알고리즘 이용 (완전탐색)
		for(int i=1; i<=N; i++) {
			if(N % i == 0) {
				list.add(i);
			}
		}
		
		Collections.sort(list);	// 리스트 오름차순 정렬
		
		// 약수의 개수가 K보다 작은 경우
		if(list.size() < K) {
			System.out.println(0);
		}
		else {
			// K번째로 작은 수 뽑기 (list는 0번째 인덱스부터 시작하므로 K-1)
			System.out.println(list.get(K-1));	
		}
	}

}
