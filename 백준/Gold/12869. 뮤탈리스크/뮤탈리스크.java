import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] scvArr;
	static boolean[][][] visited;
	static int minAttackCount;	// 뮤탈이 모든 scv들을 파괴하기 위한 최소 공격 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// SCV의 수
		scvArr = new int[3];	// scv 최대 3마리
		visited = new boolean[61][61][61];	// scv 3마리의 체력(60이하)을 방문(체크)할 배열
		minAttackCount = Integer.MAX_VALUE; // 뮤탈이 모든 scv들을 파괴하기 위한 최소 공격 횟수 일단 최대값으로 설정
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			scvArr[i] = Integer.parseInt(st.nextToken());
		}
		
		attackDFS(scvArr[0], scvArr[1], scvArr[2], 0);
		System.out.println(minAttackCount);
	}
	
	public static void attackDFS(int scv1, int scv2, int scv3, int attackCount) {
		// scv의 체력들이 음수인 경우 0으로 바꿔줌 
		scv1 = Math.max(scv1, 0);
		scv2 = Math.max(scv2, 0);
		scv3 = Math.max(scv3, 0);
		
		// 모든 scv들의 체력이 0인 경우 (다 죽었음) (종료조건)
		if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
			minAttackCount = Math.min(minAttackCount, attackCount);	// 최소 공격 횟수 갱신
			return;	// 메서드 종료
		}
		
		// 모든 SCV를 파괴하기 위한 최소 공격횟수보다 지금 뮤탈이 공격한 횟수가 큰 경우 
		// 더이상 탐색할 필요 없음 (가지치기)
		if(minAttackCount < attackCount) {
			return;	// 메서드 종료
		}
		
		Integer[] tempScvArr = {scv1, scv2, scv3};
		Arrays.sort(tempScvArr, Collections.reverseOrder());	// scv체력들 내림차순 정렬
		
		// 임시 scv들의 체력들 받아옴 (내림차순 순으로)
		int tempScv1 = tempScvArr[0];
		int tempScv2 = tempScvArr[1];
		int tempScv3 = tempScvArr[2];
		
		// 이미 scv들의 체력들 방문했으면 더이상 탐색할 필요 없으므로 종료 (가지치기)
		if(visited[tempScv1][tempScv2][tempScv3]) {
			return;	// 메서드 종료
		}
		
		visited[tempScv1][tempScv2][tempScv3] = true;	// scv들의 해당 체력들 방문(체크)처리
		
		// 뮤탈이 공격하는 경우 6가지 dfs 탐색 => scv들 공격했으므로 공격횟수는 1 증가시킴
		attackDFS(tempScv1-9, tempScv2-3, tempScv3-1, attackCount+1);
		attackDFS(tempScv1-9, tempScv2-1, tempScv3-3, attackCount+1);
		attackDFS(tempScv1-3, tempScv2-9, tempScv3-1, attackCount+1);
		attackDFS(tempScv1-3, tempScv2-1, tempScv3-9, attackCount+1);
		attackDFS(tempScv1-1, tempScv2-9, tempScv3-1, attackCount+1);
		attackDFS(tempScv1-1, tempScv2-3, tempScv3-9, attackCount+1);
	}

}
