import java.util.*;
import java.io.*;

public class Solution {
	
	static int H;
	static int W;
	static char[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];	// (0, 0) ~ (W-1, H-1) -> [0][0] ~ [H-1][W-1]
			int startX = 0;
			int startY = 0;
			
			for(int i=0; i<H; i++) {
				String input = br.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = input.charAt(j);
					// 전차가 있는 좌표인 경우
					if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						startX = i;
						startY = j;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String command = br.readLine();
			int idx = stateIdx(map[startX][startY]);
			
			for(int i=0; i<N; i++) {
				char value = command.charAt(i);
				
				switch(value) {
					// 명령어가 'D'인 경우
					// 전차가 바라보는 방향 아래로 바꾸고('v'), 한칸 아래의 칸이 평지라면 아래로 이동 
					case 'D': {
						idx = 0;
						map[startX][startY] = 'v';
						int nextX = startX + dx[0];
						int nextY = startY + dy[0];
					
						if(range(nextX, nextY) && map[nextX][nextY] == '.') {
							map[nextX][nextY] = 'v';
							map[startX][startY] = '.';
							// 시작위치 조정
							startX = nextX;
							startY = nextY;
						}
						break;
					}
					// 명령어가 'U'인 경우
					// 전차가 바라보는 방향 위쪽으로 바꾸고('^'), 한칸 위의 칸이 평지라면 위로 이동 
					case 'U': {
						idx = 1;
						map[startX][startY] = '^';
						// 2차원배열에서 좌표 위쪽으로(상) 이동할 수 있도록
						int nextX = startX + dx[1];
						int nextY = startY + dy[1];
						
						if(range(nextX, nextY) && map[nextX][nextY] == '.') {
							map[nextX][nextY] = '^';
							map[startX][startY] = '.';
							// 시작위치 조정
							startX = nextX;
							startY = nextY;
						}
						break;
					}
					// 명령어가 'L'인 경우
					// 전차가 바라보는 방향 왼쪽으로 바꾸고('<'), 한칸 왼쪽의 칸이 평지라면 왼쪽으로 이동 
					case 'L': {
						idx = 2;
						map[startX][startY] = '<';
						int nextX = startX + dx[2];
						int nextY = startY + dy[2];
						
						if(range(nextX, nextY) && map[nextX][nextY] == '.') {
							map[nextX][nextY] = '<';
							map[startX][startY] = '.';
							// 시작위치 조정
							startX = nextX;
							startY = nextY;
						}
						break;
					}
					// 명령어가 'R'인 경우
					// 전차가 바라보는 방향 오른쪽으로 바꾸고('>'), 한칸 오른쪽의 칸이 평지라면 오른쪽으로 이동 
					case 'R': {
						idx = 3;
						map[startX][startY] = '>';
						int nextX = startX + dx[3];
						int nextY = startY + dy[3];
						
						if(range(nextX, nextY) && map[nextX][nextY] == '.') {
							map[nextX][nextY] = '>';
							map[startX][startY] = '.';
							// 시작위치 조정
							startX = nextX;
							startY = nextY;
						}
						break;
					}
					// 명령어가 'S'인 경우
					// 전차가 바라보는 방향으로 포탄을 발사
					case 'S': {
						int tempX = startX;
						int tempY = startY;
						
						// 2차원 배열 범위 안에 있는 경우 반복문 실행 -> 범위 벗어나면 while문 빠져나옴
						while(range(tempX, tempY)) {
							tempX += dx[idx];
							tempY += dy[idx];
							
							// 2차원 배열 범위 안에 있는 경우
							if(range(tempX, tempY)) {
								// 벽돌로 만든 벽('*')인 경우
								if(map[tempX][tempY] == '*') {
									map[tempX][tempY] = '.';	// 벽 부순 뒤
									break;	// while문 빠져나옴
								}
								// 강철로 만든 벽('#')인 경우
								else if(map[tempX][tempY] == '#') {
									break;	// while문 빠져나옴
								}
								// 평지('.')거나 또는 물('_')인 경우
								else if(map[tempX][tempY] == '.' || map[tempX][tempY] == '_') {
									continue;	// 다음 좌표 탐색하게끔
								}
							}
							// 2차원 배열 범위 벗어난 경우
							else {
								break;	// while문 빠져나옴
							}
						}
					}
					default: {
						break;
					}
						
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);

	}
	
	public static int stateIdx(char value) {
		if(value == 'v') {
			return 0;
		}
		else if(value == '^') {
			return 1;
		}
		else if(value == '<') {
			return 2;
		}
		else if(value == '>') {
			return 3;
		}
		return -1;
	}
	
	// 좌표 범위안에 있는지 확인하는 메서드
	public static boolean range(int nextX, int nextY) {
		// (0, 0) ~ (W-1, H-1) -> 즉, [0][0] ~ [H-1][W-1] 범위안에 있는 경우
		if(nextX >= 0 && nextX < H && nextY >= 0 && nextY < W) {
			return true;	
		}
		// 위의 범위안에 없는경우는 false 반환
		return false;
	}

}
