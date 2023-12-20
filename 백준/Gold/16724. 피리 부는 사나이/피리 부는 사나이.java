import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 행의 개수
	static int M;	// 열의 개수
	static char[][] map;	// 지도 정보
	static int[] parents;	// 유니온-파인드 알고리즘을 위한 부모 배열 (각 원소의 부모를 저장하는 배열)
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// [0][0] ~ [N-1][M-1]
		parents = new int[N * M];	// [0] ~ [N*M-1], 부모배열 초기화
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		
		for(int i=0; i<N * M; i++) {
			parents[i] = i;	// 각 원소의 초기 부모는 자기 자신으로 설정
		}
		
		// 지도의 모든 위치를 순회하면서 유니온-파인드 연산 수행
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				char dir = map[i][j];	// 현재 위치의 방향
				int d = 0;	// 방향배열의 인덱스
				// 방향에 따라 방향배열 인덱스 설정
				// 하
				if(dir == 'D') {
					d = 0;
				}
				// 상
				else if(dir == 'U') {
					d = 1;
				}
				// 좌
				else if(dir == 'L' ) {
					d = 2;
				}
				// 우
				else {
					d = 3;
				}
				
				// 현재 좌표에서 가리키는 방향의 좌표(다음위치) 계산
				int nextX = i + dx[d];
				int nextY = j + dy[d];
				
				// 유니온 연산 수행
				// 각 칸이 이동할 수 있는 다음 칸과 연결되어 있다면, 두 칸을 같은 그룹으로 묶음
				// 최종적으로 독립적인 그룹의 개수가 SAFE ZONE의 개수가 된다
				int root1 = find(i * M + j);	// 현재 위치(칸)의 루트
				int root2 = find(nextX * M + nextY);	// 현재 칸이 다음 방향을 가리키는 위치의 루트
				
				union(root1, root2);	// 두 루트를 합침
			}
		}
		
		// SAFE ZONE의 개수 계산
		int safeZone = 0;
		// 지도의 2차원 배열을 1차원 배열의 인덱스로 변환해서 탐색하게끔
		// [0] ~ [N*M-1]까지 탐색
		for(int i=0; i<N * M; i++) {
			// 루트가 자기 자신인 경우, SAFE ZONE으로 간주
			if(find(i) == i) {
				safeZone++;	// SAFE ZONE의 개수 증가
			}
		}
		
		System.out.println(safeZone);

	}
	
	// 유니온-파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉 루트인 경우
		if(a == parents[a]) {
			return a;	// 자기 자신 반환
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온-파인드의 union 연산 (두 원소의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		if(aRoot == bRoot) {
			return;
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
