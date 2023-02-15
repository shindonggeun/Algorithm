import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();	
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			list.add(num);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			list.add(num);
		}
		
		Collections.sort(list);						// list 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		
		for(long i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

}