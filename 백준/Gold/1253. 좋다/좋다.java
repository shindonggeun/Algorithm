import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] numArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numArr);	// 오름차순 정렬 (투포인터 알고리즘 사용하기 위해)
		
		int count = 0;
		for(int i=0; i<N; i++) {
			int target = numArr[i];
			int left = 0;
			int right = N-1;
			
			// 투포인터 알고리즘 이용
			while(left<right) {
				int sum = numArr[left] + numArr[right];
				
				// 두 포인터가 가리키는 수의 합이 해당 찾는 수와 같은 경우
				if(sum == target) {
					// 두 포인터가 가리키는 지점이 같지 않은 경우
					if(left != i && right != i) {
						count++;	// 좋은 수 찾았음
						break;		// while문 빠져나옴
					}
					// 왼쪽 포인터(시작 포인터)가 타켓넘버를 가리키면 왼쪽 포인터 증가(오른쪽으로 이동)
					else if(left == i) {
						left++;
					}
					// 오른쪽 포인터(끝 포인터)가 타겟넘버를 가리키면 오른쪽 포인터 감소(왼쪽으로 이동)
					else {
						right--;
					}
				}
				// 두 포인터가 가리키는 수의 합이 해당 찾는 수보다 작은 경우
				else if(sum < target) {
					left++;	// 왼쪽 포인터 증가(오른쪽으로 이동)
				}
				// 두 포인터가 가리키는 수의 합이 해당 찾는 수보다 큰 경우
				else {
					right--;	// 오른쪽 포인터 감소(왼쪽으로 이동)
				}
			}
		}
		System.out.println(count);
		
	}
	

}
