import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<T; tc++) {
			TreeMap<Integer, Integer> map = new TreeMap<>();	// key: 해당 값, value: 해당 값이 나온 횟수
			int k = Integer.parseInt(br.readLine());
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				// 데이터 삽입하는 명령어인 경우
				if(command.equals("I")) {
					map.put(num, map.getOrDefault(num, 0) + 1);
				}
				// 데이터 삭제하는 명령어인 경우
				else if(command.equals("D")) {
					// 맵이 비어있는 경우 넘어감
					if(map.size() == 0) {
						continue;
					}
					// 맵이 비어있지 않은 경우(최대값 또는 최소값 삭제)
					else {
						// num == 1인 경우 최대값 삭제 연산
						// num == -1인 경우 최소값 삭제 연산
						int key = num == 1 ? map.lastKey() : map.firstKey();
						int cnt = map.get(key);
						
						if(cnt == 1) {
							map.remove(key);
						}
						else {
							map.put(key, cnt - 1);
						}
					}
				}
			}
			
			if(map.size() == 0) {
				sb.append("EMPTY").append("\n");
			}
			else {
				sb.append(map.lastKey() + " " + map.firstKey()).append("\n");
			}
		}
		System.out.print(sb);

	}

}
