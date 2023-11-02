import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 지역의 개수
	static int M;	// 쿼리의 개수
	static int[] parents;	// 각 지역의 부모(루트노드)를 저장하는 배열
	static int[] groupDrain;	// 각 지역의 배수로 용량을 저장하는 배열
	static int[] groupRainfall;	// 각 지역의 강우량을 저장하는 배열
	static int[] sumAreaCount;	// 각 그룹(루트)에 속한 지역의 개수를 저장하는 배열 (공사했을 때 합치고 난 뒤 속한 지역의 개수) 
	static int resultFloodAreaCount = 0;	// 홍수 상태인 지역의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		groupDrain = new int[N+1];
		groupRainfall = new int[N+1];
		sumAreaCount = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기에는 모든 지역이 자신을 부모노드로 갖음 (자기 자신이 곧 루트노드)
			groupDrain[i] = Integer.parseInt(st.nextToken());
			sumAreaCount[i] = 1;	// 초기에는 각 지역의 개수는 1개
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			groupRainfall[i] = Integer.parseInt(st.nextToken());
			// 배수로 용량보다 강우량이 많은 경우 (홍수상태) 
			if(groupDrain[i] < groupRainfall[i]) {
				resultFloodAreaCount++;	// 홍수상태인 지역 개수 증가
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());	// 쿼리 입력받기
			
			// 쿼리가 1인 경우 지역 연결
			if(query == 1) {
				int area1 = Integer.parseInt(st.nextToken());
				int area2 = Integer.parseInt(st.nextToken());
				
				union(area1, area2);	// 두 지역을 연결하는 union 메서드 호출
			}
			// 쿼리가 2인 경우 현재 홍수 상태인 지역 개수 출력
			else {
				sb.append(resultFloodAreaCount).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	// 지역 a의 루트(대표 지역)를 찾는 메서드
	public static int find(int a) {
		// 자기 자신이 루트노드인 경우
		if(a == parents[a]) {
			return a;
		}
		// 자기 자신이 루트노드가 아니라면 루트를 찾아서 부모에 업데이트 해줌
		return parents[a] = find(parents[a]);
	}
	
	// 두 지역을 연결하는 메서드
	public static void union(int a, int b) {
		int aRoot = find(a);	// 지역 a의 루트 노드 찾기
		int bRoot = find(b);	// 지역 b의 루트 노드 찾기
		
		// 두 지역의 루트가 같은 경우 -> 이미 연결되어 있는 경우임
		if(aRoot == bRoot) {
			return;	// 메서드 종료
		}
		// 지역 a의 루트가 지역 b의 루트보다 큰 경우
		else if(aRoot > bRoot) {
			// b그룹의 배수 용량보다 강우량이 더 큰 경우 (즉, b그룹이 홍수 상태인 경우)
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				// union 연산 후에 홍수상태가 변할 수 있기 때문에 해당 구역수를 빼줌
				resultFloodAreaCount -= sumAreaCount[bRoot];	// 현재 홍수 지역 개수에서 b그룹의 개수를 빼줌  (홍수 지역 개수 감소)
			}
			
			// a그룹의 배수 용량보다 강우량이 더 큰 경우 (즉, a그룹이 홍수 상태인 경우)
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				// union 연산 후에 홍수상태가 변할 수 있기 때문에 해당 구역수를 빼줌
				resultFloodAreaCount -= sumAreaCount[aRoot];	// 현재 홍수 지역 개수에서 a그룹의 개수를 빼줌  (홍수 지역 개수 감소)
			}
			
			parents[aRoot] = bRoot;	// a그룹의 부모를 bRoot로 설정
			
			// b그룹의 배수 용량, 강우량, 지역 개수 업데이트 하는 과정
			groupDrain[bRoot] += groupDrain[aRoot];	
			groupRainfall[bRoot] += groupRainfall[aRoot];
			sumAreaCount[bRoot] += sumAreaCount[aRoot];
			
			// 합친 후 b그룹의 배수 용량보다 강우량이 더 많은 경우 (즉, b그룹이 홍수상태인 경우)
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				resultFloodAreaCount += sumAreaCount[bRoot];	// 현재 홍수 지역 개수에서 b그룹의 개수를 더해줌 (홍수 지역 개수 증가)
			}
		}
		// 지역 b의 루트가 지역 a의 루트보다 큰 경우
		else {
			// a그룹의 배수 용량보다 강우량이 더 큰 경우 (즉, a그룹이 홍수 상태인 경우)
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				// union 연산 후에 홍수상태가 변할 수 있기 때문에 해당 구역수를 빼줌
				resultFloodAreaCount -= sumAreaCount[aRoot];	// 현재 홍수 지역 개수에서 a그룹의 개수를 빼줌  (홍수 지역 개수 감소)
			}
			
			// b그룹의 배수 용량보다 강우량이 더 큰 경우 (즉, b그룹이 홍수 상태인 경우)
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				// union 연산 후에 홍수상태가 변할 수 있기 때문에 해당 구역수를 빼줌
				resultFloodAreaCount -= sumAreaCount[bRoot];	// 현재 홍수 지역 개수에서 b그룹의 개수를 빼줌  (홍수 지역 개수 감소)
			}
			
			parents[bRoot] = aRoot;	// b그룹의 부모를 aRoot로 설정
			
			// a그룹의 배수 용량, 강우량, 지역 개수 업데이트 하는 과정
			groupDrain[aRoot] += groupDrain[bRoot];
			groupRainfall[aRoot] += groupRainfall[bRoot];
			sumAreaCount[aRoot] += sumAreaCount[bRoot];
			
			// 합친 후 a그룹의 배수 용량보다 강우량이 더 많은 경우 (즉, a그룹이 홍수상태인 경우)
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				resultFloodAreaCount += sumAreaCount[aRoot];	// 현재 홍수 지역 개수에서 a그룹의 개수를 더해줌 (홍수 지역 개수 증가)
			}
		}
	}
}
