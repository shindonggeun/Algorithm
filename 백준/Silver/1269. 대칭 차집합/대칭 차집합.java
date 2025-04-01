import java.util.*;
import java.io.*;

public class Main {
	
	static int A; // 집합 A의 원소의 개수
	static int B; // 집합 B의 원소의 개수
	static Map<Integer, Integer> map; // key: 원소, value: 해당 원소의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<A; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<B; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
			if (map.get(num) == 2) {
				map.remove(num);
			}
		}
		
		System.out.println(map.size());

	}

}