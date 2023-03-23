import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// N명의 유치원 원생 수
		int K = Integer.parseInt(st.nextToken());	// K개의 조가 되게끔 입력
		
		List<Integer> list = new ArrayList<>();		// 유치원 원생수의 키를 담은 리스트
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());	// 키 입력
			list.add(height);
		}
		
		Collections.sort(list);	// 키 오름차순 정렬
		int temp = list.get(0);	
		List<Integer> diffList = new ArrayList<>();	// 인접한 원생수의 키 차이를 뺀 리스트
		
		for(int i=1; i<N; i++) {
			int diff = list.get(i) - temp;
			diffList.add(diff);
			temp = list.get(i);
		}
		Collections.sort(diffList);	// 인접값끼리 뺸(키 차이) 값을 담은 리스트 오름차순 정렬
		int sum = 0;	// 티셔츠 만드는 비용의 최소 값
		
		for(int i=0; i<N-K; i++) {
			sum+=diffList.get(i);
		}
		System.out.println(sum);
		
	}

}