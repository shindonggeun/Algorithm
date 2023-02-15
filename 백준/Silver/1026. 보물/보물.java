import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Integer> AList = new ArrayList<>();
		List<Integer> BList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			AList.add(num);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			BList.add(num);
		}
		
		Collections.sort(AList);								// 리스트 A는 오름차순 정렬
		Collections.sort(BList, Collections.reverseOrder());	// 리스트 B는 내림차순 정렬
		
		int sum = 0;	// 두 리스트의 원소들 곱한 값들 다 더한거 최소값
		for(int i=0; i<N; i++) {
			sum += (AList.get(i) * BList.get(i));
		}
		System.out.println(sum);
	}

}