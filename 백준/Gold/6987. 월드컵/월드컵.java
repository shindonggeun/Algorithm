import java.util.*;
import java.io.*;

public class Main {

	static int nationCount = 6;	// 나라 개수
	static int[][] worldCup;
	static boolean endGame;
	static int[][] matchGame;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 네가지 결과에 대해서 확인하기
		for(int tc=0; tc<4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			worldCup = new int[6][3];	// 6개의 나라의 나라별로 승, 무, 패를 저장한 2차원 배열
			boolean resultOk = true;	// 각 결과에 대해서 나올수 있는 결과인지 판단해주는 변수
			endGame = false;	// 모든 나라 경기 제대로 다 마친경우를 나타내는 변수
			
			// 최대 경기 가능한 수 구하기 
			int totalGameCount = 0;
			for(int i=1; i<nationCount; i++) {
				totalGameCount += i;
			}
			
			matchGame = new int[totalGameCount][2];	// 경기 매치별 게임 수
			int idx = 0;	// 게임 매치별 인덱스 (15게임 경기별 인덱스)
			
			// i, j => 0번 = A, ... , 5번 = F 
			for(int i=0; i<nationCount-1; i++) {
				for(int j=i+1; j<nationCount; j++) {
					matchGame[idx][0] = i;	
					matchGame[idx][1] = j;	
					idx++;
				}
			}
			
			// 6개국의 결과 나라별로 승, 무, 패 순서로 입력받기
			for(int i=0; i<nationCount; i++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				worldCup[i][0] = win;
				worldCup[i][1] = draw;
				worldCup[i][2] = lose;
				
				// 승 + 무 + 패의 수가 5가 아닌 경우(즉, 경기수 5번 진행 안했으면)
				if(win + draw + lose != 5) {
					resultOk = false;
				}
			}
			
			// 각 결과에 대해서 나올 수 없는 경우면
			if(!resultOk) {
				sb.append(0).append(" ");
			}
			// 각 결과에 대해서 나올 수 있는 경우면
			else {
				backTracking(0, totalGameCount);	// 백트래킹 실시
				
				// 모든 나라 경기 제대로 다 마친 경우
				if(endGame) {
					sb.append(1).append(" ");
				}
				// 모든 나라 경기 제대로 못 마친 경우
				else {
					sb.append(0).append(" ");
				}
			}
		}
		System.out.println(sb);

	}
	
	public static void backTracking(int matchCount, int totalGameCount) {
		// 모든 나라 다 경기했을 때 나올 수 있는 수 (경기 조합 수)가 totalGameCount인 경우(즉, 최대 경기수 도달한 경우 -> 종료조건)
		if(matchCount== totalGameCount) {
			endGame = true;	// 모든 나라 경기 제대로 다 마쳤음
			return;	// 메서드 종료
		}
		
		int homeTeam = matchGame[matchCount][0];	// 홈팀의 번호 뽑기 (0 = A, ..., 5 = F)
		int awayTeam = matchGame[matchCount][1];	// 어웨이팀의 번호 뽑기 (0 = A, ..., 5 = F)
		
		// 홈팀 승, 어웨이팀 패
		if(worldCup[homeTeam][0] > 0 && worldCup[awayTeam][2] > 0) {
			worldCup[homeTeam][0]--;	// 홈팀의 승리 수 줄여주기
			worldCup[awayTeam][2]--;	// 어웨이팀의 패배 수 줄여주기
			backTracking(matchCount+1, totalGameCount);	// 백트래킹 메서드 호출(경기수는 증가)
			worldCup[homeTeam][0]++;	// 홈팀의 승리 수 다시 원상 복귀
			worldCup[awayTeam][2]++;	// 어웨이팀의 패배 수 다시 원상 복귀
		}
		// 홈팀, 어웨이팀 둘다 무승부
		if(worldCup[homeTeam][1] > 0 && worldCup[awayTeam][1] > 0) {
			worldCup[homeTeam][1]--;	// 홈팀의 무승부 수 줄여주기
			worldCup[awayTeam][1]--;	// 어웨이팀의 무승부 수 줄여주기
			backTracking(matchCount+1, totalGameCount);	// 백트래킹 메서드 호출(경기수는 증가)
			worldCup[homeTeam][1]++;	// 홈팀의 무승부 수 다시 원상 복귀
			worldCup[awayTeam][1]++;	// 어웨이팀의 무승부 수 다시 원상 복귀
		}
		// 홈팀 패, 어웨이팀 승
		if(worldCup[homeTeam][2] > 0 && worldCup[awayTeam][0] > 0) {
			worldCup[homeTeam][2]--;	// 홈팀의 패배 수 줄여주기
			worldCup[awayTeam][0]--;	// 어웨이팀의 승리 수 줄여주기
			backTracking(matchCount+1, totalGameCount);	// 백트래킹 메서드 호출(경기수는 증가)
			worldCup[homeTeam][2]++;	// 홈팀의 패배 수 다시 원상 복귀
			worldCup[awayTeam][0]++;	// 어웨이팀의 승리 수 다시 원상 복귀
		}
		
		
	}

}
