import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();	// key: 숫자, value: 숫자가 나온 횟수
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<A; i++) {
			int answer = Integer.parseInt(st.nextToken());
			map.put(answer, map.getOrDefault(answer, 0) + 1);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<B; i++) {
			int answer = Integer.parseInt(st.nextToken());
			map.put(answer, map.getOrDefault(answer, 0) + 1);
			// 숫자가 나온 횟수가 2이면 map에서 삭제해버림
			if(map.get(answer) == 2) {
				map.remove(answer);
			}
		}
		
		System.out.println(map.size());
	}

}