import java.util.*;
import java.io.*;

public class Main {

	static int T; // 테스트 케이스 개수
	static int K; // 연산의 개수
	static TreeMap<Integer, Integer> treeMap; // 이중우선순위큐를 대체할 트리맵 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			K = Integer.parseInt(br.readLine());
			// 트리맵 생성
			treeMap = new TreeMap<>(); // key: 해당 값, value: 해당 값이 나온 횟수
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				
				char command = st.nextToken().charAt(0); // 연산 입력
				int num = Integer.parseInt(st.nextToken());
				
				// 삽입 연산인 경우
				if (command == 'I') {
					// 트리맵에 해당 값 저장 및 개수 1개 늘려줌
					treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
				}
				// 삭제 연산인 경우
				else {
					if (treeMap.size() == 0) {
						continue;
					}
					
					int key = 0; // 해당 key 값 (최대값 or 최소값)
					// 최대값 삭제 연산(1)인 경우
					if (num == 1) {
						key = treeMap.lastKey(); // 해당 key값 최대값으로 설정
					}
					// 최소값 삭제 연산(-1)인 경우
					else {
						key = treeMap.firstKey(); // 해당 key값 최소값으로 설정
					}
					
					int count = treeMap.get(key); // 해당 key값에 해당하는 개수 뽑아내기
					
					// 해당 삭제할 값의 개수가 1개인 경우
					if (count == 1) {
						treeMap.remove(key); // 해당 값 트리맵에서 삭제
					}
					// 해당 삭제할 값의 개수가 2개 이상인 경우
					else {
						treeMap.put(key, count - 1); // 트리맵에서 해당 값의 개수 1개 줄여줌
					}
				}
			}
			
			// 트리맵이 비어있는 경우
			if (treeMap.isEmpty()) {
				sb.append("EMPTY").append("\n");
			}
			else {
				// 트리맵의 저장된 최대값과 최소값 뽑아내기
				int maxValue = treeMap.lastKey();
				int minValue = treeMap.firstKey();
				
				sb.append(maxValue).append(" ").append(minValue).append("\n");
			}
		}
		
		System.out.print(sb);
	}

}