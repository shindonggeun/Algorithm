import java.util.*;
import java.io.*;

public class Solution {
	
	// 사용자의 이동한 좌표 정보를 저장할 때 사용할 내부 클래스
	// 문제에서 그림을 보면 x가 좌, 우 y가 상, 하
	static class Human {
		int x;	
		int y;
		
		public Human(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// 2차원 배열에서 숫자에 따른 이동방향을 결정해주는 메서드
		public void move(int moveNum) {
			// 숫자 0인 경우 이동하지 않음
			if(moveNum == 0) {
				return;
			}
			// 숫자 1인 경우 상 (위)
			else if(moveNum == 1) {
				this.y--;
			}
			// 숫자 2인 경우 우 (오른쪽)
			else if(moveNum == 2) {
				this.x++;
			}
			// 숫자 3인 경우 하 (아래)
			else if(moveNum == 3) {
				this.y++;
			}
			// 숫자 4인 경우 좌 (왼쪽)
			else if(moveNum == 4) {
				this.x--;
			}
		}
	}
	
	// 배터리의 정보들을 저장할 내부 클래스
	static class BC {
		// BC(AP 배터리)의 좌표 정보
		int x;
		int y;
		int range;	// 배터리 충전 범위
		int power;	// 처리량
		
		public BC(int x, int y, int range, int power) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
	}
	
	static int M;	// 총 이동 시간
	static int A; 	// BC의 개수
	static int[] userA_move;	// 사용자 A의 이동정보를 저장한 배열
	static int[] userB_move;	// 사용자 B의 이동정보를 저장한 배열
	static BC[] batteryArr;	// BC(AP 배터리)들의 정보를 담은 배열
	static int totalCharge;	// 모든 사용자가 충전한 양의 최대값 (배터리 충전 총합)
	static int start;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 총 이동시간 입력
			A = Integer.parseInt(st.nextToken());	// BC(AP 배터리) 개수
			
			userA_move = new int[M];
			userB_move = new int[M];
			
			// 사용자 A의 이동 정보 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userA_move[i] = Integer.parseInt(st.nextToken());
			}
			
			// 사용자 B의 이동 정보 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userB_move[i] = Integer.parseInt(st.nextToken());
			}
			
			batteryArr = new BC[A];
			
			// BC (AP 배터리 충전기)들의 정보들 입력받기
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				batteryArr[i] = new BC(x, y, range, power);
			}
			
			Human userA = new Human(1, 1);	// 사용자 A는 (1, 1)부터 시작
			Human userB = new Human(10, 10);	// 사용자 B는 (10, 10)부터 시작
			totalCharge = 0;	// 모든 사용자가 충전한 양의 최대값(충전량 합) 초기화
			
			start = 0;
			// 출발 위치에서 충전되는지 확인
			charge(userA, userB);
			
			// 총 이동시간에 따른 충전량 확인하는 작업
			for(int i=0; i<M; i++) {
				// 사용자의 이동정보에 따른 움직이기 작업
				userA.move(userA_move[i]);	
				userB.move(userB_move[i]);
				start++;
				charge(userA, userB);	// 이동하고 나서 해당 위치에 충전되는지 확인
			}
			
			sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
		}
		System.out.print(sb);
		
		
	}
	
	// 사용자 A, 사용자 B가 이동할때마다 배터리들 충전해주는 메서드
	public static void charge(Human userA, Human userB) {
		List<Integer> listA = new ArrayList<>();	// 사용자 A 입장에서의 해당 배터리의 인덱스(정보) 저장할 리스트 
		List<Integer> listB = new ArrayList<>();	// 사용자 B 입장에서의 해당 배터리의 인덱스(정보) 저장할 리스트
		
		// BC(AP 배터리)들의 개수만큼 충전범위 안에 있는지 확인하기 
		for(int i=0; i<A; i++) {
			// 사용자 A 입장
			// 거리 계산하기
			int distanceA = Math.abs(userA.x - batteryArr[i].x) + Math.abs(userA.y - batteryArr[i].y);
			
			// 해당 거리가 배터리 충전범위 안에 들어오는 경우
			if(distanceA <= batteryArr[i].range) {
				listA.add(i);	// 사용자 A 입장에서 해당 배터리의 인덱스(정보) 집어넣기
			}
			
			// 사용자 B 입장
			// 거리 계산하기
			int distanceB = Math.abs(userB.x - batteryArr[i].x) + Math.abs(userB.y - batteryArr[i].y);
			
			// 해당 거리가 배터리 충전 범위 안에 들어오는 경우
			if(distanceB <= batteryArr[i].range) {
				listB.add(i);
			}
		}
		
		int sumChargeMax = 0;	// 사용자 A와 사용자 B가 움직일때마다 충전한 양의 최대값
		
		// 사용자 A와 사용자 B 둘다 배터리들 충전 범위 안에 있는 경우
		// 즉, 각 사용자마다 범위안에 배터리들 1개 이상 있는 경우
		if(listA.size() > 0 && listB.size() > 0) {
			// 완전탐색 이용 (사용자 A 범위안에 있는 배터리들 정보와 사용자 B 범위 안에 있는 배터리들 정보 다 탐색)
			for(int idxA: listA) {
				for(int idxB: listB) {
					int tempChargeSum = 0;	// 사용자 입장에서 배터리 충전한 양 임시변수
					
					// 사용자 A와 사용자 B 입장에서 배터리의 인덱스(정보)가 둘다 같은 경우 (즉, 배터리 겹침)
					if(idxA == idxB) {
						tempChargeSum += batteryArr[idxA].power;	// 충전양 분배하지 않고 그대로 충전
					}
					// 사용자 A와 사용자 B 입장에서 배터리의 인덱스(정보)가 둘다 다른 경우 (즉, 배터리 겹치지 않음)
					else {
						tempChargeSum += batteryArr[idxA].power;	// 사용자 A가 접속한 배터리 충전양 더해주기
						tempChargeSum += batteryArr[idxB].power;	// 사용자 B가 접속한 배터리 충전양 더해주기
					}
					
					// 사용자 A와  사용자 B가 움직일 때 마다 충전한 양 최대값 갱신해주기
					sumChargeMax = Math.max(sumChargeMax, tempChargeSum);
				}
			}
		}
		// 사용자 A만 배터리 충전 범위 안에 있는 경우
		// 즉, 사용자 A 범위안에 배터리들이 1개 이상 있는 경우 (사용자 B는 충전할 배터리 없음)
		else if(listA.size() > 0 && listB.size() == 0) {
			// 사용자 A 범위 안에 있는 배터리 정보들 다 탐색
			for(int idxA: listA) {
				sumChargeMax = Math.max(sumChargeMax, batteryArr[idxA].power);
			}
		}
		// 사용자 B만 배터리 충전 범위 안에 있는 경우
		// 즉, 사용자 B 범위안에 배터리들이 1개 이상 있는 경우 (사용자 A는 충전할 배터리 없음)
		else if(listA.size() == 0 && listB.size() > 0) {
			// 사용자 B 범위 안에 있는 배터리 정보들 다 탐색
			for(int idxB: listB) {
				sumChargeMax = Math.max(sumChargeMax, batteryArr[idxB].power);
			}
		}
		
		totalCharge += sumChargeMax;	// 사용자들 이동할 때마다 충전량 최대값 더해주기
	}

}
