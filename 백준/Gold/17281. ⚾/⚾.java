import java.util.*;
import java.io.*;

public class Main {
	
	static int inning;	// 이닝 수
	static int[][] playerAbility;	// 입력받는 선수의 이닝 별 타구 능력을 담을 배열 (안타인지, 2루타인지 등등)
									// 안타 = 1, 2루타 = 2, 3루타 = 3, 홈런 = 4, 아웃 = 0
	static int[] playerLineUp;	// 1번 선수부터 9번 선수까지 각 타순 저장할 배열 (순열)
	static boolean[] isPlayerSelected;	// 순열에서 사용할 선수들 선택 여부 체크할 배열
	static int maxScore;	// 아인타팀이 얻을 수 있는 최대 득점 점수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());	// 이닝 수 입력받기
		playerAbility = new int[inning][10];	// 입력받는 선수의 이닝 별 타구 능력 배열 (0번방은 사용 X)
		
		
		for(int i=0; i<inning; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 이닝별 선수들의 능력 입력받기
			for(int j=1; j<=9; j++) {
				playerAbility[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		playerLineUp = new int[10];	// 0번방 사용 X (1번 타자 선수 ~ 9번 타자 선수)
		isPlayerSelected = new boolean[10];	// 0번방 사용 X (1번 타자 선수 ~ 9번 타자 선수)
		maxScore = Integer.MIN_VALUE;
		
		// 문제조건 => 아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정
		playerLineUp[4] = 1;	// 4번타자에 1번 선수를 기용 
		isPlayerSelected[4] = true;	// 4번타자 자리 미리 넣었으므로 선택(방문) 처리
		playerSequence(2);	// 1번 선수의 자리는 이미 선정됐으므로(4번 타순) 2번 선수부터 시작
		
		System.out.println(maxScore);
	}
	
	// 백트래킹 메서드 (순열)
	// 선수 순서 정해줄 메서드	=> 4번 타자 자리는 1번 선수가 가지고 있으므로 8명의 순서를 정하면 됨 => 8! = 40320가지 경우의 수 
	public static void playerSequence(int playerNum) {
		// 선수 9명 다 채워진 경우 (종료조건)
		if(playerNum == 10) {
			int score = playGame();	// 게임 시작해서 득점한 점수 저장
			maxScore = Math.max(maxScore, score);	// 최대로 득점한 점수 갱신
			return;	// 메서드 종료
		}
		
		// 현재 선수(선수번호)를 1번타자 자리부터 9번타자 자리까지 넣어보며 재귀 호출함
		// 위에서 4번타자 자리는 이미 고정시켜놨기 때문에 4번타자 자리는 제외한다
		for(int i=1; i<=9; i++) {
			// 해당 타순 이미 선택된 경우 (방문처리 됐으면)
			if(!isPlayerSelected[i]) {
				isPlayerSelected[i] = true;	// 해당 타순 선택 처리 (방문처리)
				playerLineUp[i] = playerNum;	// 해당 타순에 배치할 선수 번호 넣어줌
				playerSequence(playerNum + 1);	// 타순 배치한 선수 명수 증가
				isPlayerSelected[i] = false;	// 해당 타순 선택 처리 (방문 처리) 해제
			}
		}
		
	}
	
	// 선수들 순서 정해졌으면 경기 시작하는 메서드 
	public static int playGame() {
		int totalScore = 0;	// 경기에서 획득한 점수 (이닝 전체 순회해서 얻은 점수)
		int startPlayer = 1;	// 1번 타자(1번 타순)부터 시작
		
		// 이닝수만큼 순회
		for(int i=0; i<inning; i++) {
			int inningScore = 0;	// 현재 이닝에서 획득한 점수
			int outCount = 0;	// 아웃카운트 (3개 되면 이닝 종료)
			boolean[] base = new boolean[4];	// 0번방 사용 X (true: 해당 방(베이스)에 선수 있음, false: 해당 방(베이스)에 선수 없음)
			
			// 아웃카운트 3개 되기전까지 반복
			while(outCount < 3) {
				int hitOrOut = playerAbility[i][playerLineUp[startPlayer]];	// 해당 타순의 타자가 안타(안타, 2루타, 3루타, 홈런) 쳤는지 또는 아웃인지 여부
				
				// 해당 타순의 타자가 아웃당한 경우
				if(hitOrOut == 0) {
					outCount++;	// 아웃카운트 증가
				}
				// 해당 타순의 타자가 안타친 경우
				else if(hitOrOut == 1) {
					// 3루 베이스에 선수 있는 경우
					if(base[3]) {
						// 3루베이스에 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[3] = false;	// 3루 주자 홈으로 들어왔으므로 3루 베이스에 사람 없음 
					}
					
					// 2루 베이스에 선수 있는 경우
					if(base[2]) {
						// 2루 베이스 주자 3루로 이동
						base[2] = false;	
						base[3] = true;		
					}
					
					// 1루 베이스에 선수 있는 경우
					if(base[1]) {
						//1루 베이스 주자 2루로 이동
						base[1] = false;
						base[2] = true;
					}
					base[1] = true;	// 타자가 안타 쳤으므로 1루 베이스로 이동
				}
				// 해당 타순의 타자가 2루타를 친 경우
				else if(hitOrOut == 2) {
					// 3루 베이스에 선수 있는 경우
					if(base[3]) {
						// 3루베이스에 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[3] = false;	// 3루 주자 홈으로 들어왔으므로 3루 베이스에 사람 없음 
					}
					
					// 2루 베이스에 선수 있는 경우
					if(base[2]) {
						// 2루 베이스 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[2] = false;	// 2루 주자 홈으로 들어왔으므로 2루 베이스에 사람 없음
					}
					
					// 1루 베이스에 선수 있는 경우
					if(base[1]) {
						//1루 베이스 주자 3루로 이동
						base[1] = false;
						base[3] = true;
					}
					
					base[2] = true;	// 타자가 2루타를 쳤으므로 2루 베이스로 이동
				}
				// 해당 타순의 타자가 3루타를 친 경우
				else if(hitOrOut == 3) {
					// 3루 베이스에 선수 있는 경우
					if(base[3]) {
						// 3루베이스에 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[3] = false;	// 3루 주자 홈으로 들어왔으므로 3루 베이스에 사람 없음 
					}
					
					// 2루 베이스에 선수 있는 경우
					if(base[2]) {
						// 2루 베이스 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[2] = false;	// 2루 주자 홈으로 들어왔으므로 2루 베이스에 사람 없음
					}
					
					// 1루 베이스에 선수 있는 경우
					if(base[1]) {
						//1루 베이스 주자 홈으로 이동
						inningScore++;	// 현재 이닝에서 점수 획득
						base[1] = false;	// 1루 주자 홈으로 들어왔으므로 1루 베이스에 사람 없음
					}
					
					base[3] = true;	// 타자가 3루타를 쳤으므로 3루 베이스로 이동
				}
				// 해당 타순의 타자가 홈런을 친 경우
				else if(hitOrOut == 4) {
					// 3루 베이스에 선수 있는 경우
					if(base[3]) {
						// 3루베이스에 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[3] = false;	// 3루 주자 홈으로 들어왔으므로 3루 베이스에 사람 없음 
					}
					
					// 2루 베이스에 선수 있는 경우
					if(base[2]) {
						// 2루 베이스 주자 홈으로 들어옴
						inningScore++;	// 현재 이닝에서 점수 획득
						base[2] = false;	// 2루 주자 홈으로 들어왔으므로 2루 베이스에 사람 없음
					}
					
					// 1루 베이스에 선수 있는 경우
					if(base[1]) {
						//1루 베이스 주자 홈으로 이동
						inningScore++;	// 현재 이닝에서 점수 획득
						base[1] = false;	// 1루 주자 홈으로 들어왔으므로 1루 베이스에 사람 없음
					}
					
					inningScore++;	// 타자가 홈런 쳤으므로 점수 획득
				}
				
				startPlayer++;	// 다음 타자로 이동
				// 만약 10번 타순이 되면 (10번 타순은 없으므로 1번 타순으로 다시 시작)
				if(startPlayer >= 10) {
					startPlayer = 1;	// 1번 타순으로 다시 재조정
				}
			}
			
			// 해당 이닝에서 얻은 점수를 총 게임 점수에 더해줌 (경기에서 득점한 점수)
			totalScore += inningScore;	
		}
		
		return totalScore;	// 경기에서 득점한 점수 반환
	}
	
	

}
