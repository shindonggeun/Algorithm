import java.util.*;
import java.io.*;

public class Main {
	
	static int inning;
	static int[][] playerStat;	// 입력받는 선수의 이닝 별 타구능력(안타인지 2루타인지 등)
								// 안타 = 1, 2루타 = 2, 3루타 = 3, 홈런 = 4
	
	static boolean[] isPlayerChecked;	// 조합에서 사용할 체크 배열
	static int[] lineUp;	// 선수 순서 조합한 배열
	static int outCount;	// 아웃 카운트
	static int maxScore = Integer.MIN_VALUE;	// 최대 점수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		inning = Integer.parseInt(st.nextToken());	// 이닝수 입력
		playerStat = new int[inning][10];	// 입력받는 선수의 이닝 별 타구능력(안타인지 2루타인지 등) (0번방은 사용 X)
		
		for(int i=0; i<inning; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				playerStat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isPlayerChecked = new boolean[10];	// 선수 순서 정할 체크 배열 (1번부터 9번까지 쓸거임) (0번방은 사용 X)
		lineUp = new int[10];				// 1번타자 ~ 9번타자까지 (0번방 즉 0번타자는 사용 X)
		
		lineUp[4] = 1;		// 4번타자로 1번선수를 배치함
		isPlayerChecked[4] = true;	// 4번타자 자리 미리 넣었으므로 체크 처리함
		
		playerSeq(2);	// 1번 선수의 자리는 이미 있으므로 2번 선수부터 시작
		
		System.out.println(maxScore);	// 최대 점수 출력
		
		

	}
	
	// 선수 순서 정해줄 메서드
	static void playerSeq(int num) {
		// 9명 다 채워졌으면 종료함
		if(num == 10) {
			int score = play();
			maxScore = Math.max(maxScore, score);
			
			return;
		}
		
		// 현재 선수를 1번 자리부터 9번 자리까지 넣어보며 재귀 호출함
		// 백트래킹 알고리즘 이용
		for(int i=1; i<=9; i++) {
			if(!isPlayerChecked[i]) {
				isPlayerChecked[i] = true;
				
				lineUp[i] = num;
				playerSeq(num + 1);
				isPlayerChecked[i] = false;	
			}
		}
	}
	
	static int play() {
		int sum = 0;	// 총 게임 점수(여러 이닝 거쳐서 나온 획득 점수)
		
		int idx = 1;	// 1번 타자부터 시작
		for(int i=0; i<inning; i++) {
			int inningScore = 0;	// 현재 이닝에서 얻는 점수
			outCount = 0;	// 현재 이닝에서 아웃카운트 수
			boolean[] base = new boolean[4];	// true: 해당 베이스에 선수가 있음, false: 해당 베이스에 선수가 없음
			
			// 아웃카운트 3개 되기전까지 반복
			while(outCount < 3) {
				// 타자가 아웃당한 경우
				if(playerStat[i][lineUp[idx]] == 0) {
					outCount++;	// 아웃카운트 증가
				}
				// 타자가 안타친 경우(1루타)
				else if(playerStat[i][lineUp[idx]] == 1) {
					// 3루 베이스에 주자가 있는 경우
					if(base[3]) {
						inningScore++;		// 3루베이스에 있는 주자 들어왔으므로 점수 획득
						base[3] = false;	// 주자 다시 없음
					}
					// 2루 베이스에 주자 있는 경우
					if(base[2]) {
						base[3] = true;		// 2루 베이스 주자 3루로 이동
						base[2] = false;	// 2루 베이스 비워짐
					}
					// 1루 베이스에 주자 있는 경우
					if(base[1]) {
						base[2] = true;		// 1루 베이스 주자 2루로 이동
					}
					base[1] = true;	// 타자가 안타 쳤으므로 1루 베이스로 이동
				}
				// 타자가 2루타 친 경우
				else if(playerStat[i][lineUp[idx]] == 2) {
					// 3루 베이스에 주자가 있는 경우
					if(base[3]) {
						inningScore++;		// 3루베이스에 있는 주자 들어왔으므로 점수 획득
						base[3] = false;	// 주자 다시 없음
					}
					// 2루 베이스에 주자 있는 경우
					if(base[2]) {
						inningScore++;		// 2루베이스에 있는 주자 들어왔으므로 점수 획득
					}
					// 1루 베이스에 주자 있는 경우
					if(base[1]) {
						base[3] = true;		// 1루 베이스 주자 3루로 이동
						base[1] = false;	// 1루 베이스 비워짐
					}
					base[2] = true;	// 타자가 2루타 쳤으므로 2루 베이스로 이동
				}
				// 타자가 3루타 친 경우
				else if(playerStat[i][lineUp[idx]] == 3) {
					// 3루 베이스에 주자가 있는 경우
					if(base[3]) {
						inningScore++;		// 3루베이스에 있는 주자 들어왔으므로 점수 획득
					}
					// 2루 베이스에 주자 있는 경우
					if(base[2]) {
						inningScore++;		// 2루베이스에 있는 주자 들어왔으므로 점수 획득
						base[2] = false;	// 2루 베이스 비워짐
					}
					// 1루 베이스에 주자 있는 경우
					if(base[1]) {
						inningScore++;		// 1루베이스에 있는 주자 들어왔으므로 점수 획득
						base[1] = false;	// 1루 베이스 비워짐
					}
					base[3] = true;	// 타자가 3루타 쳤으므로 3루 베이스로 이동
				}
				// 타자가 홈런 친 경우
				else if(playerStat[i][lineUp[idx]] == 4) {
					// 3루 베이스에 주자가 있는 경우
					if(base[3]) {
						inningScore++;		// 3루베이스에 있는 주자 들어왔으므로 점수 획득
						base[3] = false;	// 3루 베이스 비워짐
					}
					// 2루 베이스에 주자 있는 경우
					if(base[2]) {
						inningScore++;		// 2루베이스에 있는 주자 들어왔으므로 점수 획득
						base[2] = false;	// 2루 베이스 비워짐
					}
					// 1루 베이스에 주자 있는 경우
					if(base[1]) {
						inningScore++;		// 1루베이스에 있는 주자 들어왔으므로 점수 획득
						base[1] = false;	// 1루 베이스 비워짐
					}
					inningScore++;		// 타자가 홈런 쳤으므로 점수 획득
				}
				
				idx++;	// 다음 타자로 이동
				// 만약 10번 타자가 되면(10번타자는 없으므로 1번 타자로 다시)
				if(idx >= 10) {
					idx = 1;
				}
			}
			
			// 해당 이닝에서 얻은 점수를 총 게임 점수에 더해줌
			sum += inningScore;
			
		}
		// 이번 게임에서 얻은 점수를 반환
		return sum;	
	}

}