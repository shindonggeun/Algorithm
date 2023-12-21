import java.util.*;
import java.io.*;

public class Main {

	static class Enemy {
		int x;
		int y;
		int r;
		
		public Enemy(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	
	static int N;
	static Enemy[] enemyArr;
	static int[] parents;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N+1];
			enemyArr = new Enemy[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				enemyArr[i] = new Enemy(x, y, r);
				parents[i] = i;
			}
			
			for(int i=1; i<=N-1; i++) {
				for(int j=i+1; j<=N; j++) {
					Enemy e1 = enemyArr[i];
					Enemy e2 = enemyArr[j];
					
					if(isPossibleCommunication(e1, e2)) {
						union(i, j);
					}
				}
			}
			
			int count = 0;
			for(int i=1; i<=N; i++) {
				if(find(i) == i) {
					count++;
				}
			}
			
			sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static boolean isPossibleCommunication(Enemy e1, Enemy e2) {
		// 유클리디안 거리 공식 이용
		int distanceSquared = (e1.x - e2.x) * (e1.x - e2.x) + (e1.y - e2.y) * (e1.y - e2.y);
		int rangeSquared = (e1.r + e2.r) * (e1.r + e2.r);
		
		// 두 진영이 통신 가능한 경우 (즉, 두 진영의 좌표가 유클리디안 거리 공식에 의해 통신 범위 안에 들어온 경우)
		// true 반환 그렇지 않은 경우 false 반환
		return distanceSquared <= rangeSquared;
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
