import java.util.*;
import java.io.*;

public class Main {
	
    static class Position {
        int x;
        int y;
        int distance;
        int crash;
        boolean isNight;

        public Position(int x, int y, int distance, int crash, boolean isNight) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.crash = crash;
            this.isNight = isNight;
        }
    }

    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int minDistance = bfs(0, 0);
        System.out.println(minDistance);
    }

    public static int bfs(int startX, int startY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 1, 0, false));
        visited[0][startX][startY] = true;

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowDistance = now.distance;
            int nowCrash = now.crash;
            boolean isNight = now.isNight;

            // 목적지 도달 확인
            if (nowX == N-1 && nowY == M-1) {
                return nowDistance;
            }

            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

				// 빈 칸으로 이동
				if (map[nextX][nextY] == 0 && !visited[nowCrash][nextX][nextY]) {
					visited[nowCrash][nextX][nextY] = true;
					queue.add(new Position(nextX, nextY, nowDistance + 1, nowCrash, !isNight));
				}
				// 벽을 만난 경우
				else if (map[nextX][nextY] == 1) {
					if (!isNight && nowCrash < K && !visited[nowCrash + 1][nextX][nextY]) {
						// 낮에 벽을 부수고 이동
						visited[nowCrash + 1][nextX][nextY] = true;
						queue.add(new Position(nextX, nextY, nowDistance + 1, nowCrash + 1, !isNight));
					} 
					else if (isNight) {
                        queue.add(new Position(nowX, nowY, nowDistance + 1, nowCrash, !isNight));
					}
				}

                
            }
        }

        return -1; // 도달할 수 없는 경우
    }
}