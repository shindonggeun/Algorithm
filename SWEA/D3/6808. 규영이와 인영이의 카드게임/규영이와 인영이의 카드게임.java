import java.util.*;
import java.io.*;

public class Solution {
	
	static List<Integer> listK;	// 규영이의 가진 패들을 저장한 리스트
	static List<Integer> listI;	// 인영이가 가진 패들을 저장한 리스트
	static boolean[] visited;	// 방문배열
	static int[] output;	// 인영의 카드 내는 것들을 저장한 배열
	static int winK;	// 규영이가 게임에서 이긴 횟수
	static int loseK;	// 규영이가 게임에서 진 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			listK = new ArrayList<>();	// 규영이가 가진 패들을 저장할 리스트 생성
			listI = new ArrayList<>();	// 인영이가 가진 패들을 저장할 리스트 생성
			winK = 0;	// 규영이가 게임에서 이긴 횟수 초기화
			loseK = 0;	// 규영이가 게임에서 진 횟수 초기화
			
			// 규영이가 가진 패들 리스트에 저장하는 과정
			for(int i=0; i<9; i++) {
				int cardNum = Integer.parseInt(st.nextToken());
				listK.add(cardNum);	// 규영이가 가진 패들 입력받아서 저장
			}
			
			// 인영이가 가진 패 리스트에 저장하는 과정
			for(int i=1; i<=18; i++) {
				// 규영이가 가진 패에 없는 카드번호이면
				if(!listK.contains(i)) {
					listI.add(i);	// 인영이가 가진 패임
				}
			}
			
			output = new int[9];	
			visited = new boolean[9];	// 방문배열 초기화
			backTracking(0);
			
			sb.append("#").append(tc).append(" ").append(winK).append(" ").append(loseK).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void backTracking(int idx) {
		// 카드 9장 다 냈으면
		if(idx == 9) {
			int scoreK = 0;	// 규영이 점수
			int scoreI = 0;	// 인영이 점수
			
			// 9개 카드 다 까보기 (9라운드)
			for(int i=0; i<9; i++) {
				int cardNumK = listK.get(i);	// 규영이가 가진 카드 뽑기
				int cardNumI = output[i];	// 인영이가 가진 카드 뽑기
				
				// 규영이가 가진 카드 번호가 인영이가 가진 카드 번호보다 높은 경우 (즉, 규영이꺼가 더 높음)
				if(cardNumK > cardNumI) {
					scoreK += (cardNumK + cardNumI);
				}
				
				// 규영이가 가진 카드번호가 인영이가 가진 카드번호보다 낮은 경우 (즉, 인영이꺼가 더 높음)
				if(cardNumK < cardNumI) {
					scoreI += (cardNumK + cardNumI);
				}
			}
			
			// 위의 9장 카드 다 까본 다음 (9라운드 다 진행하고 나면)
			// 규영이의 점수가 더 높은 경우
			if(scoreK > scoreI) {
				winK++;	// 규영이 이긴 횟수 증가
			}
			// 인영의 점수가 더 높은 경우
			else if(scoreK < scoreI) {
				loseK++;	// 규영이 진 횟수 증가
			}
			
			return;	// 메서드 종료
		}
		
		
		for(int i=0; i<9; i++) {
			// 방문안한 것인 경우(즉, 선택을 안한 카드인 경우)
			if(!visited[i]) {
				visited[i] = true;	// 선택(방문)처리
				output[idx] = listI.get(i);	// 인영이가 가진 패들 결과배열에 저장
				backTracking(idx+1);	// 재귀 호출 다시 실시
				visited[i] = false;	// 선택(방문) 해제
			}
		}
	}

}
