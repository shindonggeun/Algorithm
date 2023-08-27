import java.util.*;
import java.io.*;

public class Main {

	
	static int N;
	static int[] populationArr;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] isSelected;	// 부분집합을 만들어주기 위한 방문(선택 배열)
	static int minPopulationDiff;	// 두 선거구의 인구 차이의 최솟값
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		populationArr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			populationArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프에서 정점 생성하는 과정
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for(int k=0; k<count; k++) {
				int fromVertex = i;
				int toVertex = Integer.parseInt(st.nextToken());
				// 간선 연결해주는 작업
				graph.get(fromVertex).add(toVertex);
			}
		}
		
		isSelected = new boolean[N+1];	// 지역번호까지 방 만들어주기
										// A선거구는 true, B선거구는 false
		minPopulationDiff = Integer.MAX_VALUE;
		generateSubSet(1);	// 2개의 지역구 부분집합 구하는 메서드 호출
		
		// 두 선거구로 나눌 수 없는 경우 (나눌수 없으면 int의 최대값 나옴)
		if(minPopulationDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minPopulationDiff);
		}

	}
	
	// 백트래킹 (부분집합 메서드)
	public static void generateSubSet(int cnt) {	// cnt 1부터 시작
		// 지역구 부분집합 완성된 경우 (종료 조건)
		if(cnt == N+1) {
			// 너비우선 탐색 실시한 뒤 조건 충족되면
			if(bfs()) {
				// 각 지역구의 인구수 계산하기
				int populationSumA = 0;
				int populationSumB = 0;
//				List<Integer> listA = new ArrayList<>();
//				List<Integer> listB = new ArrayList<>();
				for(int i=1; i<=N; i++) {
					if(isSelected[i]) {
//						listA.add(i);
						populationSumA += populationArr[i];
					}
					else {
//						listB.add(i);
						populationSumB += populationArr[i];
					}
				}
//				System.out.println(listA + " " + populationSumA);
//				System.out.println(listB + " " + populationSumB);
//				System.out.println();
				
				// 두 지역구의 인구수 차이 최소값 갱신
				minPopulationDiff = Math.min(minPopulationDiff, Math.abs(populationSumA - populationSumB));
			}
			return; // 메서드 종료
		}
		
		// 부분집합에서 A선거구 지역 -> true, B선거구 지역 -> false
		// 이 구역을 A선거구로 선정
		isSelected[cnt] = true;
		generateSubSet(cnt+1);
		// 이 구역을 A선거구로 선정 안함 (즉, B선거구로 선정)
		isSelected[cnt] = false;
		generateSubSet(cnt+1);
	}
	
	// 지역 선거구 A와 B 너비우선탐색하는 메서드
	public static boolean bfs() {
		boolean[] visited = new boolean[N+1];
		int areaA = 0;
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) {
				areaA = i;
				break;
			}
		}
		
		int areaB = 0;
		for(int i=1; i<=N; i++) {
			if(!isSelected[i]) {
				areaB = i;
				break;
			}
		}
		
		// 두 지역 선거구에 포함된 지역번호(구역)이 없는 경우
		// 각 구역은 두 선거구중 반드시 하나에 포함되어야 한다
		if(areaA == 0 || areaB == 0) {
			return false;	// false 반환
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(areaA);	// A선거구의 시작정점(지역번호) 넣어주기
		visited[areaA] = true;	// 방문 처리
		
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			for(int i=0; i<graph.get(nowVertex).size(); i++) {
				int adjVertex = graph.get(nowVertex).get(i);
				// 이미 방문한 정점이면 
				if(visited[adjVertex]) {
					continue;	// 넘어감
				}
				
				// A선거구 => true, B선거구 => false
				// A선거구의 속하지 않은 정점(지역번호)이면 (즉, B선거구에 속한 정점(지역번호)인 경우)
				if(!isSelected[adjVertex]) {
					continue;	// 넘어감
				}
				
				queue.add(adjVertex);
				visited[adjVertex] = true;
			}
		}
		
		queue.add(areaB);	// B선거구의 시작정점(지역번호) 넣어주기
		visited[areaB] = true;	// 방문 처리
		
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			for(int i=0; i<graph.get(nowVertex).size(); i++) {
				int adjVertex = graph.get(nowVertex).get(i);
				// 이미 방문한 정점이면 
				if(visited[adjVertex]) {
					continue;	// 넘어감
				}
				
				// A선거구 => true, B선거구 => false
				// B선거구의 속하지 않은 정점(지역번호)이면 (즉, A선거구의 속한 정점(지역번호)인 경우)
				if(isSelected[adjVertex]) {
					continue;	// 넘어감
				}
				
				queue.add(adjVertex);
				visited[adjVertex] = true;
			}
		}
		
		// 위의 탐색을 했는데도 정점들이 다 방문처리 되지 않은 경우
		// 즉, 나누어진 선거구에서 한곳이라도 연결되지 않은 경우
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		
		// 위의 탐색 다 했는데 이상 없을 시 true 반환 (두 선거구 지역으로 나눠짐)
		return true;
	}

}
