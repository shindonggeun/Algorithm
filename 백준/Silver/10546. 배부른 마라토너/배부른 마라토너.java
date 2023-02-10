import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// key: 참가자 이름, value: 마라톤 완주 여부(0이면 완주 한것)
		
		// 마라톤 참가한 목록 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			map.put(name, map.getOrDefault(name, 0) + 1);	// 참가자들 value값 1씩 증가
		}
		
		// 완주한 사람들 목록 저장
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			map.put(name, map.getOrDefault(name, 0) - 1);	// 완주하면 map에서 value값 -1 해줌(완주한 경우 value값 0)
		}
		
		StringBuilder sb = new StringBuilder();
		for(String key: map.keySet()) {
			if(map.get(key) != 0) {
				sb.append(key).append("\n");
			}
		}
		System.out.print(sb);
	}

}