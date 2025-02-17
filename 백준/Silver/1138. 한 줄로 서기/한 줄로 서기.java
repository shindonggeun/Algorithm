import java.util.*;
import java.io.*;

public class Main {

	static int N; // 사람 수
	static int[] leftCounts; // 각 사람(키가 1인 사람부터 N인 사람까지)이 기억하는 왼쪽의 큰 사람의 수를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		leftCounts = new int[N]; // [0] ~ [N-1]
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			leftCounts[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[N]; // 최종저긍로 줄을 선 순서를 저장할 배열
		
		// 키가 1인 사람부터 N인 사람까지 줄을 세우는 과정
		for (int height=1; height<=N; height++) {
			int leftCount = leftCounts[height - 1]; // 현재 키를 가진 사람이 기억하는 왼쪽의 큰 사람의 수
			int count = 0; // 현재까지 확인한 빈 자리 개수
			
			// 줄을 서 있는 배열을 순회하면서 적절한 자리를 찾는 과정
			for (int i=0; i<N; i++) {
				// 빈 자리 (아직 배치되지 않은 자리)인 경우
				if (result[i] == 0) {
					// 왼쪽에 필요한 큰 사람 수만큼 빈 자리를 지나온 경우 (자리 배치)
					if (count == leftCount) {
						result[i] = height; // 해당 위치에 현재 키(=height)를 가진 사람 배치
						break; // 배치 완료했으므로 다음 키를 가진 사람 탐색하게끔 해당 과정 빠져나옴
					}
					count++; // 아직 배치할 위치가 아니므로 빈 자리 개수 증가
				}
			}
		}
		
		// 결과 출력하는 과정
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);

	}

}