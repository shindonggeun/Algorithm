import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			System.gc();
			Map<Long, Long> map = new HashMap<>();	// key: 군대 번호, value: 해당 군대 인원수
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<T; j++) {
				long armyNum = Long.parseLong(st.nextToken());
				map.put(armyNum, map.getOrDefault(armyNum, 0L) + 1);
			}
			boolean find = false;
			long resultNum = 0;
			for(long key: map.keySet()) {
				// 해당 번호 군대의 병사가 절반을 초과한 경우
				if(map.get(key) > T / 2) {
					find = true;
					resultNum = key;
					break;
				}
			}
			
			if(find) {
				sb.append(resultNum).append("\n");
			}
			else {
				sb.append("SYJKGW").append("\n");
			}
		}
		System.out.print(sb);
	}

}
