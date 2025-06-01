import java.util.*;
import java.io.*;

public class Main {

    // 구슬들의 상태를 담는 내부 클래스
    static class State {
        int rx; // 빨간 구슬 x 위치
        int ry; // 빨간 구슬 y 위치
        int bx; // 파란 구슬 x 위치
        int by; // 파란 구슬 y 위치
        int count; // 기울인 횟수

        public State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }

    // 구슬을 굴린 뒤 새로운 위치와 이동거리를 저장하는 내부 클래스
    static class MoveResult {
        int x;
        int y;
        int dist;

        public MoveResult(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int N; // 세로 크기
    static int M; // 가로 크기
    static char[][] map;
    static boolean[][][][] visited; // 두 구슬 위치 조합을 탐색했는지 확인하는 방문 배열 ([rx][ry][bx][by])
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M]; // [0][0] ~ [N-1][M-1]
        visited = new boolean[N][M][N][M]; // [rx][ry][bx][by]

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        int result = bfs(rx, ry, bx, by); // 빨간 구슬과 파란 구슬 각각 동시에 이동시키게끔 너비 우선 탐색 실시

        System.out.println(result);
    }

    // 너비 우선 탐색 메서드 (빨간 구슬과 파란 구슬을 동시에 기울이며 최소 횟수를 찾는 메서드)
    public static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(rx, ry, bx, by, 1)); // 초기 상태 큐에 저장 (기울인 횟수는 초기 1로)
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State now = queue.poll(); // 큐에서 현재 상태 빼냄

            // 현재 기울인 횟수가 10번 초과한 경우 실패
            if (now.count > 10) {
                return -1;
            }

            // 4가지 방향 모두 시도
            for (int i = 0; i < 4; i++) {
                MoveResult redResult = move(now.rx, now.ry, dx[i], dy[i]); // 빨간 구슬 이동 결과
                MoveResult blueResult = move(now.bx, now.by, dx[i], dy[i]); // 파란 구슬 이동 결과

                // 구슬을 굴려 이동한 칸
                int nextRx = redResult.x;
                int nextRy = redResult.y;
                int nextBx = blueResult.x;
                int nextBy = blueResult.y;

                // 파란 구슬이 구멍에 빠진 경우 (빨간 구슬을 구멍에 빠지게 하되, 파란 구슬은 구멍에 빠지면 안됨)
                if (map[nextBx][nextBy] == 'O') {
                    continue; // 실패했으므로 다음 방향 탐색
                }

                // 빨간 구슬이 구멍에 빠진 경우 (파란 구슬 구멍 빠지지 않고, 빨간 구슬만 구멍에 빠진 상태)
                if (map[nextRx][nextRy] == 'O') {
                    return now.count; // 성공했으므로 굴린 횟수 반환
                }

                // 빨간 구슬과 파란 구슬이 같은 위치에 존재하는 경우 (겹친 경우)
                if (nextRx == nextBx && nextRy == nextBy) {
                    if (redResult.dist > blueResult.dist) {
                        // 빨간 구슬이 더 멀리 이동한 경우 한 칸 뒤로
                        nextRx -= dx[i];
                        nextRy -= dy[i];
                    } else {
                        // 파란 구슬이 더 멀리 이동한 경우 한 칸 뒤로
                        nextBx -= dx[i];
                        nextBy -= dy[i];
                    }
                }

                // 아직 방문하지 않은 경우
                if (!visited[nextRx][nextRy][nextBx][nextBy]) {
                    visited[nextRx][nextRy][nextBx][nextBy] = true; // 방문 처리
                    queue.add(new State(nextRx, nextRy, nextBx, nextBy, now.count + 1)); // 큐에 굴린 횟수 + 1 한뒤 추가
                }
            }
        }

        return -1; // 성공 못하고 큐가 비어있는 경우 실패
    }

    // 구슬을 특정 방향으로 굴려 최종 위치와 이동 거리를 반환하는 메서드
    public static MoveResult move(int nowX, int nowY, int dirX, int dirY) {
        int dist = 0; // 이동한 칸 수

        // 구슬이 이동할 수 없는 장애물('#')이 아니면서 동시에 구멍('O')이 아닌 경우
        while (map[nowX + dirX][nowY + dirY] != '#' && map[nowX][nowY] != 'O') {
            nowX += dirX; // 한 칸씩 이동
            nowY += dirY;
            dist++; // 이동 거리 증가
        }

        return new MoveResult(nowX, nowY, dist);
    }

}