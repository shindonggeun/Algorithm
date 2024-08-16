import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 인형들의 개수
	static int K; // 인형들 중 최소한 포함해야 하는 라이언 인형의 개수
	static int[] dollArr; // 인형들의 종류를 저장하는 배열 (1: 라이언 인형, 2: 어피치 인형)
	static List<Integer> lionList; // 인형들 중 라이언 인형의 인덱스를 저장할 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dollArr = new int[N]; // [0] ~ [N-1]
		lionList = new ArrayList<>(); // 리스트 초기화
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			dollArr[i] = Integer.parseInt(st.nextToken());
			// 현재 인형이 라이언 인형(1)인 경우
			if (dollArr[i] == 1) {
				lionList.add(i); // 라이언 인형의 인덱스를 리스트에 추가
			}
		}
		
		// 라이언 인형의 개수가 K보다 작은 경우
		if (lionList.size() < K) {
			System.out.println(-1); // K개 이상의 라이언 인형을 포함하는 집합을 만들 수 없으므로 -1 출력
		}
		// 라이언 인형의 개수가 K보다 크거나 같은 경우
		else {
			// 슬라이딩 알고리즘 이용
			int start = 0; // 슬라이딩 윈도우의 시작 포인터
			int end = K-1; // 슬라이딩 윈도우의 끝 포인터 (K개 라이언을 포함해야 하므로 K-1 인덱스까지 커버)
			int minLength = Integer.MAX_VALUE; // 가장 작은 연속된 집합의 크기를 저장할 변수 최대값으로 초기화
			int length = 0; // 현재 슬라이딩 윈도우의 길이를 저장할 변수
			
			// 슬라이딩 윈도우가 라이언 인형들의 인덱스를 저장한 리스트의 끝까지 도달할떄까지 슬라이딩 윈도우를 이동시킴
			while (end < lionList.size()) {
				// 현재 윈도우의 길이 계산 (즉, K개 이상의 라이언 인형을 포함하는 가장 작은 연속된 인형들의 집합의 크기 계산)
				length = lionList.get(end) - lionList.get(start) + 1;
				// 최소 길이 갱신 (즉, 가장 작은 연속된 집합의 크기 갱신)
				minLength = Math.min(minLength, length);
				
				// 슬라이딩 윈도우 한칸 이동
				start++; // 슬라이딩 윈도우 시작 포인터 증가
				end++; // 슬아디이 윈도우 끝 포인터 증가
			}
			
			System.out.println(minLength);
		}

	}

}