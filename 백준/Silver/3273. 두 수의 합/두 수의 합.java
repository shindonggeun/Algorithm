import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 수열의 크기
	static int[] numArr;	// 수열에 포함되는 수를 저장한 배열
	static int X;	// 조건에 만족하는 자연수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];	// [0] ~ [N-1]
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 수열에 포함되는 수 입력받기
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		X = Integer.parseInt(br.readLine());
		
		Arrays.sort(numArr);	// 해당 수열 오름차순 정렬
		
		// 투포인터 알고리즘 이용
		int left = 0;	// 왼쪽 포인터
		int right = N-1;	// 오른쪽 포인터
		int count = 0;	// 조건에 만족하는 쌍의 개수
		
		// 왼쪽 포인터와 오른쪽 포인터가 만나게 되면 종료
		while (left < right) {
			int leftNum = numArr[left];	// 수열에서 왼쪽 포인터가 가리키는 수
			int rightNum = numArr[right];	// 수열에서 오른쪽 포인터가 가리키는 수
			
			// 포인터들이 가리키는 수를 더한 것(쌍)이 조건에 만족하는 수보다 작은 경우
			if (leftNum + rightNum < X) {
				left++;	// 왼쪽 포인터 증가
			}
			// 포인터들이 가리키는 수를 더한 것(쌍)이 조건에 만족하는 수보다 크거나 같은 경우
			else {
				right--;	// 오른쪽 포인터 감소
				
				// 조건에 만족하는 수와 같은 경우
				if (leftNum + rightNum == X) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}

}