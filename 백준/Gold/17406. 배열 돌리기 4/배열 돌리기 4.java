import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 행의 개수(세로 길이)
	static int M;	// 열의 개수(가로 길이)
	static int K;	// 회전 연산의 개수
	static int[][] map;
	static int[][] rotationInfo;
	static int[] arr;
	static boolean[] visited;	// 방문여부를 나타내는 배열
	static int minA_result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotationInfo = new int[K][3];	// 각 회전 정보를 저장한 배열 [0] -> r, [1] -> c, [2] -> s
		arr = new int[K];
		visited = new boolean[K];
		minA_result = Integer.MAX_VALUE;
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());	
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			rotationInfo[i][0] = r;
			rotationInfo[i][1] = c;
			rotationInfo[i][2] = s;
		}
		
		backTracking(0);	// 백트래킹 시작
		System.out.println(minA_result);
	}
	
	// 백트래킹 메서드 (순열)
	public static void backTracking(int depth) {
		// 해당 깊이가 회전 연산의 개수와 같아지는 경우 (종료조건)
		if(depth == K) {
			setUp();
			return;
		}
		
		for(int i=0; i<K; i++) {
			// 방문안한 경우(즉, 선택 안한 경우)
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				backTracking(depth+1);
				visited[i] = false;
			}
		}
	}
	
	// 2차원 배열 깊은복사해서 넘겨주는 메서드
	public static int[][] copyMap() {
		int[][] copyMap = new int[N][M];
		
		// 2차원배열 깊은복사 
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i].clone();
		}
		
		return copyMap;
	}
	
	public static void setUp() {
		int[][] copyMap = copyMap();
		
		for(int i=0; i<K; i++) {
			int x1 = rotationInfo[arr[i]][0] - rotationInfo[arr[i]][2] - 1;
			int y1 = rotationInfo[arr[i]][1] - rotationInfo[arr[i]][2] - 1;
			int x2 = rotationInfo[arr[i]][0] + rotationInfo[arr[i]][2] - 1;
			int y2 = rotationInfo[arr[i]][1] + rotationInfo[arr[i]][2] - 1;
			
			rotate(x1, y1, x2, y2, copyMap);
		}
		
		minResult(copyMap);
	}
	
	public static void rotate(int x1, int y1, int x2, int y2, int[][] copyMap) {
		if(x1 == x2 && y1 == y2) {
			return;
		}
		
		int[] temp = new int[3];	// 방향별로 값을 옮길 경우 지워질 수 있는 좌표값이 있는데 
									// 그 값들을 저장할 수 있도록 해주는 배열
		temp[0] = copyMap[x1][y2];
		temp[1] = copyMap[x2][y2];
		temp[2] = copyMap[x2][y1];
		
		// 오른쪽으로 회전
		for(int i=y2; i>y1; i--) {
			copyMap[x1][i] = copyMap[x1][i-1];
		}
		
		// 아래로 회전
		for(int i=x2; i>x1; i--) {
			if(i == x1+1) {
				copyMap[i][y2] = temp[0];
			}
			else {
				copyMap[i][y2] = copyMap[i-1][y2];
			}
		}
		// 왼쪽으로 회전
		for(int i=y1; i<y2; i++) {
			if(i == y2-1) {
				copyMap[x2][i] = temp[1];
			}
			else {
				copyMap[x2][i] = copyMap[x2][i+1];
			}
		}
		// 위로 회전
		for(int i=x1; i<x2; i++) {
			if(i == x2-1) {
				copyMap[i][y1] = temp[2];
			}
			else {
				copyMap[i][y1] = copyMap[i+1][y1];
			}
		}
		
		rotate(x1+1, y1+1, x2-1, y2-1, copyMap);
	}
	
	public static void minResult(int[][] copyMap) {
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum += copyMap[i][j];
			}
			
			minA_result = Math.min(minA_result, sum);
		}
	}
	
	

}
