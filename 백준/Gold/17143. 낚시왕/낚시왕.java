import java.util.*;
import java.io.*;

public class Main {
	
	static class Shark {
		int x;	
		int y;	
		int speed;	// 속력
		int direction;	// 방향
		int size;	// 크기
		
		public Shark(int x, int y, int speed, int direction, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

//		@Override
//		public String toString() {
//			return "Shark [x=" + x + ", y=" + y + ", speed=" + speed + ", direction=" + direction + ", size=" + size
//					+ "]";
//		}
	}
	
	static int R;	// 행의 개수
	static int C;	// 열의 개수
	static int M;	// 상어의 수
	static Shark[][] map;
	static int result;	// 낚시왕이 잡은 상어 크기의 합
	
	// 4가지 방향 배열 (상, 하, 우, 좌) (이동표시가 1부터 이므로 0번방은 사용 x)
	static int[] dx = {0, -1, 1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, 0, 1, -1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C];
		result = 0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;	 
			int c = Integer.parseInt(st.nextToken()) - 1;	
			int s = Integer.parseInt(st.nextToken());	// 속력 입력
			int d = Integer.parseInt(st.nextToken());	// 상어 이동방향 입력
			int z = Integer.parseInt(st.nextToken());	// 크기 입력
			
			Shark shark = new Shark(r, c, s, d ,z);
			map[r][c] = shark;
		}
		
		// 낚시왕 이동시키는 작업 (열 이동 ->0열, 1열, 2열, 3열, ...)
		for(int position=0; position<C; position++) {
			// 낚시왕이 열 이동할때마다 행 탐색하기 (상어 있는지 없는지)
			for(int i=0; i<R; i++) {
				// 탐색한 좌표에 상어가 있는 경우 (땅(0번행)과 가장 가까운 상어 잡음)
				if(map[i][position] != null) {
					result += map[i][position].size;	// 해당 좌표에 있는 상어의 크기 더해줌
					
//					System.out.println("잡아먹힌 상어: " + " " + map[i][position].toString());
					
					map[i][position] = null;	// 상어 잡았으므로 해당 좌표에 있는 상어 null 처리
					break;	// 상어 한마리 잡았으므로 다음 열 탐색
				}
			}
			
			sharkBehavior();	// 상어들 이동 및 잡아먹게끔 해줌
			
//			System.out.println("==========이동한뒤 상어 좌표들===============");
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					if(map[i][j] != null) {
//						System.out.println(map[i][j].toString());
//					}
//				}
//			}
			
		}
		System.out.println(result);
	}
	
	// 상어들 이동 및 상어끼리 잡아먹는 메서드
	public static void sharkBehavior() {
		List<Shark> sharkList = new ArrayList<>();
		
		// 맵 좌표들 완전탐색
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 상어가 있는 좌표인 경우
				if(map[i][j] != null) {
					sharkList.add(map[i][j]);	// 리스트에 상어 추가해줌
					map[i][j] = null;	// 상어 없음 표시
				}
			}
		}
		
		// 상어 담은 리스트 순회 
		for(Shark shark: sharkList) {
			// 상어 속도만큼 반복
			for(int i=0; i<shark.speed; i++) {
				int nextX = shark.x + dx[shark.direction];
				int nextY = shark.y + dy[shark.direction];
				
				// 탐색한 좌표가 벽을 만난 경우 (즉, 범위 넘어간 경우)
				if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					// 범위 넘어갔으니 탐색하기 전 좌표로 돌아올수 있게끔
					nextX = shark.x - dx[shark.direction];
					nextY = shark.y - dy[shark.direction];
					
					// 반대방향으로 이동시켜줄수 있게끔 해주는 과정
					// 현재 이동방향이 위인 경우
					if(shark.direction == 1) {
						// 반대방향인 아래로 이동시킬 수 있게끔
						shark.direction = 2;
					}
					// 현재 이동방향이 아래인 경우
					else if(shark.direction == 2) {
						// 반대방향인 위로 이동시킬 수 있게끔
						shark.direction= 1;
					}
					// 현재 이동방향이 오른쪽인 경우
					else if(shark.direction == 3) {
						// 반대방향인 왼쪽으로 이동시킬 수 있게끔
						shark.direction = 4;
					}
					// 현재 이동방향이 왼쪽인 경우
					else if(shark.direction == 4) {
						// 반대방향인 오른쪽으로 이동시킬 수 있게끔
						shark.direction = 3;
					}
				}
				
				// 탐색한 좌표 다시 저장
				shark.x = nextX;	
				shark.y = nextY;
			}
			
			// 상어 이동시킨 뒤 다시 맵에 상어 있게끔 처리
			// 또한 겹친 상어들 있는지 확인하는 작업
			if(map[shark.x][shark.y] == null) {
				map[shark.x][shark.y] = shark;	
			}
			// 만약 해당 맵 좌표에 상어가 있는 경우 (즉, 상어 겹친 경우)
			else {
				// 상어 크기 서로 비교해서 큰 상어가 작은 상어 잡아먹게끔 처리
				if(map[shark.x][shark.y].size < shark.size) {
					map[shark.x][shark.y] = shark;
				}
			}
		}
	}

}
