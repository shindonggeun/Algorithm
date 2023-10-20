import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                int seat = input.charAt(j) - '0';
                map[i][j] = seat;
            }
        }

        int count = 0;

        for(int i=0; i<N; i++) {
            int consecutiveEmptySeats = 0; // 연속된 빈 좌석 수를 저장하는 변수

            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    consecutiveEmptySeats++;
                    
                    if(consecutiveEmptySeats >= K) { // 연속된 빈 좌석 수가 K 이상이면
                        count++; // 경우의 수 증가
                    }
                } 
                else {
                    consecutiveEmptySeats = 0; // 연속된 빈 좌석 수 초기화
                }
            }
        }

        System.out.println(count);
    }
}
