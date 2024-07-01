import java.util.*;
import java.io.*;

public class Main {
	
	// 좌석(자리) 정보를 담고 있는 내부 클래스
	static class Seat {
		int x;
		int y;
		int emptyCount; // 비어있는 인접한 좌석의 개수
		int likeCount; // 좋아하는 학생이 인접한 좌석의 수
		
		public Seat(int x, int y, int emptyCount, int likeCount) {
			this.x = x;
			this.y = y;
			this.emptyCount = emptyCount;
			this.likeCount = likeCount;
		}
	}
	
	static int N; // 교실 크기
	static int[][] map;
	// 좋아하는 학생 목록을 담은 해시맵
	static Map<Integer, int[]> studentLikeMap; // key: 해당 학생의 번호, 해당 학생이 좋아하는 학생들의 번호
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		studentLikeMap = new HashMap<>(); // 좋아하는 학생 목록을 담은 해시맵 생성
		
		// 학생 번호 1번부터 N^2까지
		for (int i=1; i<=N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken()); // 해당 학생 번호 입력
			
			int[] likeFriends = new int[4]; // 좋아하는 학생 목록 4명 [0] ~ [3]
			// 해당 학생이 좋아하는 학생들 입력받기
			for (int j=0; j<4; j++) {
				likeFriends[j] = Integer.parseInt(st.nextToken());
			}
			
			studentLikeMap.put(student, likeFriends); // 해당 학생의 좋아하는 학생들 해시맵에 저장
			
			// 해당 학생 자리 배정하기
			placeStudentSeat(student);
		}
		
		// 전체 학생의 만족도 계산
		int result = calulateTotalSatisfaction();
		
		System.out.println(result);

	}
	
	// 해당 학생의 자리를 배정해주는 메서드
	public static void placeStudentSeat(int student) {
		int[] likeFriends = studentLikeMap.get(student); // 해당 학생의 좋아하는 학생 목록 뽑아내기
		List<Seat> seatList = new ArrayList<>(); // 가능한 자리 목록을 저장할 리스트
		
		// 교실의 모든 좌석에 대해 반복
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 이미 해당 자리에 학생이 앉아있는 경우
				if (map[i][j] != 0) {
					continue; // 다른 좌석 탐색하도록 넘어감
				}
				
				int emptyCount = 0; // 해당 자리에서부터 비어있는 인접한 좌석의 수
				int likeCount = 0; // 해당 자리에서부터 인접한 자리 중 좋아하는 학생의 수
				
				int nowX = i; // 현재 좌표 x값 (행)
				int nowY = j; // 현재 좌표 y값 (열)
				
				// 4가지 방향 탐색 (인접한 자리)
				for (int k=0; k<4; k++) {
					int nextX = nowX + dx[k];
					int nextY = nowY + dy[k];
					
					// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						continue; // 다음 방향 탐색하도록 넘어감
					}
					
					// 1. 비어있는 인접한 좌석 탐색
					// 탐색한 좌표가 비어있는 좌석인 경우
					if (map[nextX][nextY] == 0) {
						emptyCount++; // 비어있는 좌석의 수 증가
					}
					
					// 2. 인접한 자리 중 좋아하는 학생 있는지 탐색
					// 좋아하는 학생 목록 순회
					for (int like: likeFriends) {
						// 탐색한 좌표가 좋아하는 학생이 있는 자리인 경우
						if (map[nextX][nextY] == like) {
							likeCount++; // 인접한 자리 중 좋아하는 학생의 수
						}
					}
				}
				
				// 좌석 리스트에 현재 좌석 정보 저장
				seatList.add(new Seat(nowX, nowY, emptyCount, likeCount));
			}
		}
		
		// 좌석 리스트 커스텀 정렬
		Collections.sort(seatList, (a, b) -> {
			// 좋아하는 학생의 칸 수가 같은 경우
			if (a.likeCount == b.likeCount) {
				// 비어있는 칸의 개수가 같은 경우
				if (a.emptyCount == b.emptyCount) {
					// 행의 번호가 같은 경우
					if (a.x == b.x) {
						// 4순위인 열의 번호가 가장 작은순으로 자리 정할 수 있도록
						// 열의 번호 오름차순 정렬
						return a.y - b.y;
					}
					// 3순위인 행의 번호가 가장 작은 순으로 자리 정할 수 있도록
					// 행의 번호 오름차순 정렬
					return a.x - b.x;
				}
				// 2순위인 비어있는 칸이 많은 순으로 자리 정할 수 있도록
				// 비어있는 좌석의 수 내림차순 정렬
				return b.emptyCount - a.emptyCount;
			}
			// 1순위인 비어있는 칸 중 좋아하는 학생이 많은 칸으로 자리를 정할 수 있도록
			// 인접한 자리 중 좋아하는 학생의 수 내림차순 정렬 
			return b.likeCount - a.likeCount;
		});
		
		// 정렬한 좌석 리스트 순회
		for (Seat seat: seatList) {
			// 해당 좌석에 이미 다른 학생이 앉아있는 경우 (즉, 빈 좌석(0)이 아닌 경우)
			if (map[seat.x][seat.y] != 0) {
				continue; // 다른 좌석 탐색하도록 넘어감
			}
			
			// 해당 좌석에 해당 학생 배치
			map[seat.x][seat.y] = student;
			break; // 더이상 좌석 탐색할 필요없이 반복문 빠져나옴
		}
	}
	
	// 학생들의 만족도의 총합을 구하는 메서드
	public static int calulateTotalSatisfaction() {
		int total = 0; // 만족도의 총합
		
		// 모든 좌석 탐색
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌석에 앉아있는 학생의 만족도 구해서 만족도의 총합에 누적
				total += calculateSatisfaction(i, j, map[i][j]);
			}
		}
		
		// 만족도의 총합 반환
		return total;
	}
	
	// 해당 학생의 만족도를 구하는 메서드
	public static int calculateSatisfaction(int startX, int startY, int student) {
		int likeCount = 0; // 좋아하는 학생의 수
		int[] likeFriends = studentLikeMap.get(student); // 해당 학생의 좋아하는 학생 목록
		
		// 인접한 4가지 방향 탐색
		for (int i=0; i<4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			
			// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue; // 다음 방향 탐색하도록 넘어감
			}
			
			// 좋아하는 학생 목록 순회
			for (int like: likeFriends) {
				// 탐색한 좌표가 좋아하는 학생이 앉아있는 좌표인 경우
				if (map[nextX][nextY] == like) {
					likeCount++; // 좋아하는 학생의 수 증가
				}
			}
		}
		
		// 해당 학생의 만족도 구하기
		// 좋아하는 학생의 수가 0인 경우
		if (likeCount == 0) {
			return 0; // 만족도는 0
		}
		// 만족도 계산해서 반환
		return (int) Math.pow(10, likeCount-1);
	}

}