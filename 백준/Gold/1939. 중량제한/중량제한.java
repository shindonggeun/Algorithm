import java.util.*;
import java.io.*;

public class Main {
	
	static class Bridge implements Comparable<Bridge> {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Bridge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Bridge o) {
			// 다리 중량 제한 내림차순 정렬
			return o.weight - this.weight;
		}
	}
	
	static int N;
	static int M;
	static int[] parents;
	static List<Bridge> bridgeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			bridgeList.add(new Bridge(fromVertex, toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int startVertex = Integer.parseInt(st.nextToken());
		int endVertex = Integer.parseInt(st.nextToken());
		
		// 다리 정보를 저장한 리스트 커스텀 정렬 (중량 제한 내림차순 정렬)
		Collections.sort(bridgeList);	
		
		int result = 0;
		// 다리 정보 저장한 리스트 순회하기
		for(Bridge bridge: bridgeList) {
			union(bridge.fromVertex, bridge.toVertex);	
			// 두 섬이 연결 된 경우 (즉, 시작 정점과 끝 정점이 연결되었으면)
			if(find(startVertex) == find(endVertex)) {
				result = bridge.weight;	// 결과값에 다리 중량의 최대값 저장
				break;	// 더 탐색할 필요없이 반복문 빠져나옴
			}
		}
		
		System.out.println(result);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		}
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
		
	}

}
