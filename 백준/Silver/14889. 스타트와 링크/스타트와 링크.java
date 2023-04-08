import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] map;
	static boolean[] visit;
	static int count = 0;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(min);

	}
	
	public static void dfs(int idx, int count) {
		// 팀 조합 완성된 경우 (즉 팀의 구성원이 다 충족된 경우)
		if(count == N / 2) {
			difference();	// 각 팀의 능력치를 계산해서 능력치의 차 구하기
			return;
		}
		
		for(int i=idx; i<N; i++) {
			// 방문하지 않은 경우
			if(!visit[i]) {
				visit[i] = true;	// 방문
				dfs(i+1, count+1);	// 그다음 선수 탐색 (조합 만들기 위해)
				visit[i] = false;	// 재귀 종료시 방문한거 다시 방문하지않은걸로 만들어주기
			}
		}
	}
	
	public static void difference() {
		int startTeam = 0;
		int linkTeam = 0;
		
		// 브루트포스 알고리즘 이용
		for(int i=0; i<N-1; i++) {
			for(int j=i; j<N; j++) {
				if(visit[i] == true && visit[j] == true) {
					startTeam+=map[i][j];
					startTeam+=map[j][i];
				}
				else if(visit[i] == false && visit[j] == false) {
					linkTeam+=map[i][j];
					linkTeam+=map[j][i];
				}
			}
		}
		
		int val = Math.abs(startTeam - linkTeam);
		min = Math.min(min, val);
	}

}