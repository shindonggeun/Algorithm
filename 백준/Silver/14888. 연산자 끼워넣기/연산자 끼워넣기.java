import java.util.*;
import java.io.*;

public class Main {

	static int N; // 수열의 개수
	static int[] numArr; // 해당 수열을 담을 배열
	static int[] operationArr; // 연산자들의 개수를 담을 배열 (+, -, *, / 순으로 개수 담음)
	static int maxValue; // 연산자들을 이용해서 만들 수 있는 최대값
	static int minValue; // 연산자들을 이용해서 만들 수 있는 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N]; // [0] ~ [N-1]
		operationArr = new int[4]; 
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			operationArr[i] = Integer.parseInt(st.nextToken());
		}
		
		maxValue = Integer.MIN_VALUE; // 연산자들을 이용해서 만들 수 있는 최대값 일단 최소값으로 초기화
		minValue = Integer.MAX_VALUE; // 연산자들을 이용해서 만들 수 있는 최소값 일단 최대값으로 초기화
		
		calculate(1, numArr[0]); // 연산 수행 (첫 번째 값부터 시작)
		
		System.out.println(maxValue);
		System.out.println(minValue);
	}
	
	// 깊이 우선 탐색 (백트래킹) 알고리즘을 이용해 가능한 모든 연산을 수행하는 메서드
	// depth: 현재 사용한 숫자의 개수(인덱스)
	// value: 현재까지 연산자들을 이용해 계산된 값
	public static void calculate(int depth, int value) {
		// 현재 사용한 숫자의 개수가 N개가 된 경우 (즉, 모든 수를 사용한 경우 - 기저조건)
		if (depth == N) {
			maxValue = Math.max(maxValue, value); // 최대값 갱신
			minValue = Math.min(minValue, value); // 최소값 갱신
			return; // 메서드 종료
		}
		
		// 4가지의 연산자를 사용하도록 순회 시작
		for (int i=0; i<4; i++) {
			// 해당 연산자의 개수가 0보다 큰 경우 (즉, 사용 가능한 경우)
			if (operationArr[i] > 0) {
				operationArr[i]--; // 해당 연산자의 개수 줄여줌
				
				// 각 연산자에 따른 계산 수행
				// 해당 연산자의 번호가 0인 경우 (즉, 해당 연산자가 +인 경우)
				if (i == 0) {
					calculate(depth+1, value + numArr[depth]); // 덧셈 계산
				}
				// 해당 연산자의 번호가 1인 경우 (즉, 해당 연산자가 -인 경우)
				else if (i == 1) {
					calculate(depth+1, value - numArr[depth]); // 뺄셈 계산
				}
				// 해당 연산자의 번호가 2인 경우 (즉, 해당 연산자가 *인 경우)
				else if (i == 2) {
					calculate(depth+1, value * numArr[depth]); // 곱셈 계산
				}
				// 해당 연산자의 번호가 3인 경우 (즉, 해당 연산자가 /인 경우)
				else {
					calculate(depth+1, value / numArr[depth]); // 나눗셈 계산
				}
				
				operationArr[i]++; // 해당 연산자의 개수 다시 증가 (백트래킹)
			}
		}
	}

}