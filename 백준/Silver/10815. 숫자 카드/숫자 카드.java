import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();	// key: 상근이가 가지고 있는 숫자 카드, value: 숫자카드 개수
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(num, 0) + " ");	// 상근이가 가지고있는 숫자카드의 없는 수면 0으로
		}
		System.out.println(sb);
		
		
    }
}