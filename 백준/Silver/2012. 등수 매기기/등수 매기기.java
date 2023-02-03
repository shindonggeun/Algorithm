import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list);	// 입력값 담은 list 오름차순
		
		long sum = 0;	// 불만도 합이 최소가 되는 변수 (long 타입으로 해야하는거 주의!)
		// 사람들의 불만도 계산 
		for(int i=0; i<N; i++) {
			sum+=Math.abs(list.get(i) - (i+1));
		}
		
		System.out.println(sum);

	}

}