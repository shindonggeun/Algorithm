import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		Set<Integer> set = new HashSet<>();	// 중복허용 X, set을 이용해서 탐색을 빠르게 하자
		
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			if(set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append("1").append("\n");
			}
			else {
				sb.append("0").append("\n");
			}
		}
		System.out.print(sb);

	}

}