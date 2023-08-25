import java.util.*;
import java.io.*;

public class Solution {
	
	// 섬들의 좌표값들을 저장해줄 수 있도록 내부 클래스 선언
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 정점 정보를 저장하는 내부 클래스 선언
	static class Vertex implements Comparable<Vertex> {
		int num;	// 정점(노드) 번호 
		double weight;	// 가중치
		
		public Vertex(int num, double weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			List<Position> islandList = new ArrayList<>();
			
			// 섬들의 x좌표 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int inputX = Integer.parseInt(st.nextToken());
				islandList.add(new Position(inputX, 0));
			}
			
			// 섬들의 y좌표 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int inputY = Integer.parseInt(st.nextToken());
				islandList.get(i).y = inputY; 
			}
			
			double E = Double.parseDouble(br.readLine());	// 세율 입력
			
			// 그래프 인접행렬로 표현하기 (prim 알고리즘 이용하기 위해)
			double[][] adjMatrix = new double[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i == j) {
						continue;
					}
					
					long distX = Math.abs(islandList.get(i).x - islandList.get(j).x);
					long distY = Math.abs(islandList.get(i).y - islandList.get(j).y);
					
					long result = distX * distX + distY * distY;	// L^2
					adjMatrix[i][j] = E * result;	// 환경부담금 인접행렬에 저장
				}
			}
			// prim 메서드 호출해서 최소신장트리 비용 저장 (최소환경부담금)
			double minCharge = prim(N, adjMatrix);
			
			sb.append("#").append(tc).append(" ").append(Math.round(minCharge)).append("\n");
		}
		System.out.print(sb);
	}
	
	// prim 메서드
	public static double prim(int N, double[][] adjMatrix) {
		boolean[] visited = new boolean[N];	// 방문정점 (트리정점표시)
		double[] minEdge = new double[N];	// 자신과 트리의 정점들 간 최소 간선 비용
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		
		Arrays.fill(minEdge, Double.MAX_VALUE);	// 최소값 갱신 위해 큰 값으로 세팅
		minEdge[0] = 0.0;	//임의의 0 정점을 트리 구성의 시작으로 하기 위해 세팅
		pq.add(new Vertex(0, minEdge[0]));
		
		double mstCost = 0;	// 최소신장트리 비용
		double min = 0;
		int minVertex = 0;
		int count = 0;
		
		while(!pq.isEmpty()) {
			// step1: 미방문(비트리) 정점 중 최소간선비용의 정점을 선택
			Vertex cur = pq.poll();
			minVertex = cur.num;
			min = cur.weight;
			if(visited[minVertex]) {
				continue;
			}
			
			// step2: 방문(트리) 정점에 추가
			visited[minVertex] = true;	// 방문 처리
			mstCost += min;	// 신장트리 비용 누적
			if(count == N) {
				break;
			}
			count++;
			
			// step3: 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려 (a.k.a 영업타임)
			for(int i=0; i<N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
					pq.add(new Vertex(i, minEdge[i]));
				}
			}
			
		}
		// 최소신장트리비용 반환
		return mstCost;
	}

}
