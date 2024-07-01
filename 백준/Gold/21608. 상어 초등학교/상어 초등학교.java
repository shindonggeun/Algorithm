import java.util.*;
import java.io.*;

public class Main {
	
	static class Seat {
		int x;
		int y;
		int emptyCount;
		int likeCount;
		
		public Seat(int x, int y, int emptyCount, int likeCount) {
			this.x = x;
			this.y = y;
			this.emptyCount = emptyCount;
			this.likeCount = likeCount;
		}
	}
	
	static int N;
	static int[][] map;
	static Map<Integer, int[]> studentLikeMap;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		studentLikeMap = new HashMap<>();
		
		for (int i=1; i<=N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			
			int[] likeFriends = new int[4];
			for (int j=0; j<4; j++) {
				likeFriends[j] = Integer.parseInt(st.nextToken());
			}
			
			studentLikeMap.put(student, likeFriends);
			
			placeStudentSeat(student);
		}
		
		int result = calulateTotalSatisfaction();
		
		System.out.println(result);

	}
	
	public static void placeStudentSeat(int student) {
		int[] likeFriends = studentLikeMap.get(student);
		List<Seat> seatList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int emptyCount = 0;
				int likeCount = 0;
				
				int nowX = i;
				int nowY = j;
				
				for (int k=0; k<4; k++) {
					int nextX = nowX + dx[k];
					int nextY = nowY + dy[k];
					
					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						continue;
					}
					
					
					if (map[nextX][nextY] == 0) {
						emptyCount++;
					}
					
					for (int like: likeFriends) {
						if (map[nextX][nextY] == like) {
							likeCount++;
						}
					}
				}
				
				seatList.add(new Seat(nowX, nowY, emptyCount, likeCount));
			}
		}
		
		Collections.sort(seatList, (a, b) -> {
			if (a.likeCount == b.likeCount) {
				if (a.emptyCount == b.emptyCount) {
					if (a.x == b.x) {
						return a.y - b.y;
					}
					return a.x - b.x;
				}
				return b.emptyCount - a.emptyCount;
			}
			return b.likeCount - a.likeCount;
		});
		
		for (Seat seat: seatList) {
			if (map[seat.x][seat.y] != 0) {
				continue;
			}
			
			map[seat.x][seat.y] = student;
			break;
		}
	}
	
	public static int calulateTotalSatisfaction() {
		int total = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				total += calculateSatisfaction(i, j, map[i][j]);
			}
		}
		
		return total;
	}
	
	
	public static int calculateSatisfaction(int startX, int startY, int student) {
		int count = 0;
		int[] likeFriends = studentLikeMap.get(student);
		
		for (int i=0; i<4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue;
			}
			
			for (int like: likeFriends) {
				if (map[nextX][nextY] == like) {
					count++;
				}
			}
		}
		
		if (count == 0) {
			return 0;
		}
		return (int) Math.pow(10, count-1);
	}

}