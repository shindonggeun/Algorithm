import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 얼응 양동이 개수
	static int K;	// 최적 위치로 부터 K만큼 떨어진 거리
	static int[] iceArr;	// 각 위치의 얼음 양을 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		iceArr = new int[1000001];	// [0] ~ [1000000]
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());	// 얼음 양
			int x = Integer.parseInt(st.nextToken());	// 해당 얼음 양ㅇ동이의 위치
			
			iceArr[x] = g;	// 해당 위치에 얼음 양을 저장
		}
		
		int sum = 0;	// 현재 윈도우 사이즈내의 얼음 양의 합을 저장하는 변수
		int max = 0;	// 최대 얼음 양의 합을 저장하는 변수
		int range = 2 * K + 1;	// 윈도우의 크기 (좌우로 K만큼 닿을 수 있으므로)
		
		// 0부터 최대 범위인 1000000까지의 좌표를 순회
		for (int i=0; i<=1000000; i++) {
			sum += iceArr[i];	// 현재 위치의 얼음 양을 합에 추가
			// 윈도우에서 벗어난 위치가 있을 경우
			if (i - range >= 0) {
				sum -= iceArr[i-range];	// 윈도우에서 벗어난 위치의 얼음 양을 합에서 빼줌
			}
			
			max = Math.max(max, sum);	// 현재 합이 최대값보다 크면 최대값 갱신
		}
		System.out.println(max);

	}

}