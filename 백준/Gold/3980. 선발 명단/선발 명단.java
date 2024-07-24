import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] abilityArr; // 해당 선수에 따른 포지션별 능력치를 저장한 2차원 배열 [선수번호][포지션번호]
	static boolean[] visited; // 해당 포지션을 방문했는지 여부를 나타내는 방문 배열
	static int maxSumAbility; // 능력치의 합의 최대값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			
			abilityArr = new int[11][11]; // [0][0] ~ [10][10] -> [선수번호][포지션번호]
			visited = new boolean[11]; // [0] ~ [10] -> 포지션별
			
			for (int i=0; i<11; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<11; j++) {
					// 해당 선수의 포지션별 능력치 입력 및 저장
					abilityArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxSumAbility = Integer.MIN_VALUE; // 능력치의 합의 최대값 최소값으로 초기화
			findPosition(0, 0); // 각 선수마다 최적의 포지션 배치를 찾는 메서드 호출
			
			sb.append(maxSumAbility).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 각 선수마다 최적의 포지션 배치를 찾는 메서드 (백트래킹)
	// player: 현재 탐색할 선수 번호
	// sum: 현재까지의 선수들의 능력치의 합
	public static void findPosition(int player, int sum) {
		// 현재 탐색한 선수 번호가 11인 경우 (즉, 11명 다 탐색 완료된 경우)
		// 모든 선수들을 다 배치한 경우 (기저조건)
		if (player == 11) {
			maxSumAbility = Math.max(maxSumAbility, sum); // 능력치의 합의 최대값 갱신
			return; // 메서드 종료
		}
		
		// 모든 포지션에 대해서 반복
		for (int position=0; position<11; position++) {
			// 해당 포지션이 방문되지 않았으면서(즉, 해당 포지션이 배치되지 않았으면서) 동시에 해당 선수의 포지션이 0보다 큰 경우
			if (!visited[position] && abilityArr[player][position] > 0) {
				visited[position] = true; // 해당 포지션 방문 처리 (해당 포지션 배치함)
				findPosition(player + 1, sum + abilityArr[player][position]); // 다음 선수 탐색하도록 메서드 호출
				visited[position] = false; // 해당 포지션 방문 처리 해제 (백트래킹)
			}
		}
	}

}