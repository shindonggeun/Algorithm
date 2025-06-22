import java.util.*;
import java.io.*;

public class Main {

    // 좌표 정보를 저장하는 내부 클래스
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N; // 보드 세로 길이
    static int M; // 보드 가로 길이
    static char[][] map;
    static boolean[][] visited; // 각 좌표의 방문 여부를 나타내는 배열
    static int minTurnCount; // 최소 이동 횟수 (방향 전환한 최소 횟수)
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        String str;
        StringBuilder sb = new StringBuilder();
        int testCase = 1; // 테스트 케이스 번호 1번부터 시작

        // 입력이 더 이상 없을 때까지 반복
        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M]; // [0][0] ~ [N-1][M-1]
            int emptyCount = 0; // 빈 칸 개수

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    // 해당 칸이 빈 칸('.')인 경우 빈 칸 개수 증가
                    if (map[i][j] == '.') {
                        emptyCount++;
                    }
                }
            }

            minTurnCount = Integer.MAX_VALUE; // 최소 방향 전환 횟수 최대값으로 초기화

            // 모든 좌표 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 해당 좌표가 빈 칸('.')인 경우 깊이 우선 탐색 실시
                    if (map[i][j] == '.') {
                        visited = new boolean[N][M]; // 방문 배열 초기화, [0][0] ~ [N-1][M-1]
                        visited[i][j] = true; // 해당 좌표 방문 처리
                        dfs(i, j, 1, 0, emptyCount); // 해당 좌표에서부터 깊이 우선 탐색 실시
                    }
                }
            }

            sb.append("Case ").append(testCase++).append(": ");
            sb.append(minTurnCount == Integer.MAX_VALUE ? -1 : minTurnCount).append("\n");
        }

        System.out.print(sb);
    }

    // 깊이 우선 탐색 알고리즘을 이용하여 모든 빈 칸을 방문할 수 있는 경로를 찾는 메서드
    public static void dfs(int x, int y, int depth, int turnCount, int emptyCount) {
        // 모든 빈 칸을 방문한 경우 최소 방향 전환 횟수 갱신 (기저 조건 1)
        if (depth == emptyCount) {
            minTurnCount = Math.min(minTurnCount, turnCount);
            return; // 메서드 종료
        }

        // 이미 방향 전환 횟수가 최소 이동 횟수보다 많은 경우 더 이상 탐색 할 필요 없음 (기저 조건 2)
        if (turnCount >= minTurnCount) {
            return; // 메서드 종료
        }

        // 4가지 방향 탐색
        for (int d = 0; d < 4; d++) {
            int nextX = x;
            int nextY = y;
            List<Position> path = new ArrayList<>(); // 현재 방향으로 이동한 경로를 저장하는 리스트

            // 한 방향으로 계속 이동
            while (true) {
                nextX += dx[d];
                nextY += dy[d];

                // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표를 탐색한 경우 (범위 벗어난 경우)
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    break; // 다음 방향 탐색
                }

                // 탐색한 좌표가 장애물이 있는 칸('*')이거나 또는 이미 방문한 좌표인 경우
                if (map[nextX][nextY] == '*' || visited[nextX][nextY]) {
                    break; // 다음 방향 탐색
                }

                visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
                path.add(new Position(nextX, nextY)); // 탐색한 좌표 이동경로에 저장
            }

            // 이동 경로가 있는 경우
            if (!path.isEmpty()) {
                // 마지막 유효 좌표를 전달
                Position last = path.get(path.size() - 1);
                // 마지막 유효 좌표에서부터 다시 깊이 우선 탐색 실시 (방향 전환 횟수 + 1)
                dfs(last.x, last.y, depth + path.size(), turnCount + 1, emptyCount);

                // 백트래킹 과정
                for (Position pos : path) {
                    visited[pos.x][pos.y] = false; // 방문 처리 해제
                }
            }
        }
    }
}