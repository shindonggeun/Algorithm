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
		
		map = new HashMap<>(); // 해시맵 생성
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<A; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1); // 해시맵에 해당 원소 및 원소 개수 1 증가해서 저장
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<B; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1); // 해시맵에 해당 원소 및 원소 개수 1 증가해서 저장
			
			// 해시맵에 해당 원소의 개수가 2개인 경우
			if (map.get(num) == 2) {
				map.remove(num); // 해시맵에서 해당 원소 제거
			}
		}
		
		System.out.println(map.size()); // 해시맵에 저장된 원소 개수 출력 (대칭 차집합의 원소 개수와 같음)

	}

}