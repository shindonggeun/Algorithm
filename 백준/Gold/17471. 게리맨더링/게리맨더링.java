import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 구역의 개수
	static int[] parents;	// 각 구역의 부모 (대표 노드)를 저장하는 배열
	static int[] population;	// 각 구역 번호당 인구수를 저장한 배열
	// 각 구역의 부분집합을 만들어주기 위한 선택배열 (true: A선거구, false: B선거구)
	// 즉, 해당 구역이 어떤 선거구에 포함되어 있는지 체크하는 선택 배열
	static boolean[] isSelected;	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();	// 각 구역과 인접한 구역의 정보를 저장하는 그래프
	static int minPopulationDiff;	// 두 선거구의 인구 차이의 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];	// [1] ~ [N]
		parents = new int[N+1];
		isSelected = new boolean[N+1];
		minPopulationDiff = Integer.MAX_VALUE;	// 두 선거구의 인구 차이의 최소값 일단 최대값으로 설정
		
		// 각 구역의 인구수 입력하는 과정
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프 초기화 과정
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int areaCount = Integer.parseInt(st.nextToken());	// 인접한 구역의 수 입력
			
			// 각 구역의 번호와 인접한 구역 연결하는 과정
			for(int c=0; c<areaCount; c++) {
				int fromVertex = i;
				int toVertex = Integer.parseInt(st.nextToken());
				graph.get(fromVertex).add(toVertex);
			}
		}
		
		generateSubset(1);	// 모든 가능한 선거구 조합 (부분집합) 생성하는 메서드 호출
		System.out.println(minPopulationDiff == Integer.MAX_VALUE ? -1 : minPopulationDiff);
	}
	
	// 부분집합 메서드 
	public static void generateSubset(int depth) {
		// 해당 깊이(선택 횟수)가 N+1이 된 경우 (다 선택한 경우, 1부터 시작했으므로 N+1) (즉, 종료조건)
		if(depth == N+1) {
			populationDiffCalculate();	// 두 선거구의 인구 차이 계산하는 메서드 호출
			return;	// 메서드 종료
		}
		
		// 부분집합 만드는 과정
		isSelected[depth] = true;	// 해당 구역을 선거구 A에 포함 
		generateSubset(depth+1);
		isSelected[depth] = false;	// 해당 구역을 선거구 B에 포함
		generateSubset(depth+1);
	}
	
	// 해당 구역의 대표자(root)를 찾는 메서드 (유니온 파인드 알고리즘에서 파인드 부분) 
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 두 구역을 연결하는 메서드 (유니온 파인드 알고리즘에서 유니온 부분)
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
	
	public static void populationDiffCalculate() {
		List<Integer> groupA = new ArrayList<>();	// A선거구의 지역 번호를 저장한 리스트
		List<Integer> groupB = new ArrayList<>();	// B선거구의 지역 번호를 저장한 리스트
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기 각 구역의 부모노드는 자기 자신으로 설정
			
			// true: A선거구, false: B선거구
			// 선택된 구역인 경우
			if(isSelected[i]) {
				groupA.add(i);	// 선택된 구역을 A 선거구에 추가
			}
			// 선택되지 않은 구역인 경우
			else {
				groupB.add(i);	// 선택되지 않은 구역을 B 선거구에 추가
			}
		}
		
		// 가지치기: A, B선거구에 구역이 없는 경우 (종료조건)
		if(groupA.size() == 0 || groupB.size() == 0) {
			return;	// 메서드 종료
		}
		
		
		for(int i=1; i<=N; i++) {
			// 해당 구역과 인접한 구역 탐색하기 (그래프 탐색)
			for(int vertex: graph.get(i)) {
				// 해당 구역과 인접한 구역이 같은 선거구인 경우 
				if(isSelected[i] == isSelected[vertex]) {
					union(i, vertex);	// 해당 구역과 인접한 구역 합치기 (연결하기)
				}
			}
		}
		
		int rootA = find(groupA.get(0));	// A 선거구의 대표 지역 (대표 노드) 추출
		int rootB = find(groupB.get(0));	// B 선거구의 대표 지역 (대표 노드) 추출
		
		// A 선거구의 지역번호 탐색
		for(int areaA: groupA) {
			// A 선거구의 각 지역 번호들이 연결되어 있지 않은 경우
			if(find(areaA) != rootA) {
				return;	// 메서드 종료
			}
		}
		
		// B 선거구의 지역번호 탐색
		for(int areaB: groupB) {
			// B 선거구의 각 지역 번호들이 연결되어 있지 않은 경우
			if(find(areaB) != rootB) {
				return;	// 메서드 종료
			}
		}
		
		int populationSumA = 0;	// A 선거구의 인구 총합
		int populationSumB = 0;	// B 선거구의 인구 총합
		
		// 선거구 A의 인구 총합 구하기
		for(int areaA: groupA) {
			populationSumA += population[areaA];
		}
		
		// 선거구 B의 인구 총합 구하기
		for(int areaB: groupB) {
			populationSumB += population[areaB];
		}
		
		// 두 선거구의 인구 차이의 최소값 갱신
		minPopulationDiff = Math.min(minPopulationDiff, Math.abs(populationSumA - populationSumB));
	}

}