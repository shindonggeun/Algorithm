import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 용액들의 값들을 저장한 배열 오름차순 정렬
		// 오름차순 정렬시 왼쪽에는 매우 낮은 음수값들이, 오른쪽에는 매우 큰 양수값들이 분포
		// 그러므로 가운데로 갈수록 절대값은 점점 작아진다
		// 투 포인터 알고리즘 사용하기 용이해짐
		Arrays.sort(arr);	
		
		// 투 포인터 알고리즘 이용
		int left = 0;
		int right = arr.length - 1;
		
		int min = Integer.MAX_VALUE;	// 두 용액을 더했을 때 최소값
		int sum = 0;		// 두 용액을 합한 값
		int result1 = 0;	// 두 용액중 작은 값
		int result2 = 0;	// 두 용액중 큰값
		
		while(left < right) {
			sum = arr[left] + arr[right];	// 두 용액 더해줌
			int abs = Math.abs(sum);
			// 두 용액을 더한 절대값이 최소값보다 작은 경우 최소값 재할당 시켜줌
			if(abs < min) {
				min = abs;
				result1 = arr[left];
				result2 = arr[right];
			}
			
			// 두 포인터가 가리키는 것들의 합이 0보다 큰 경우 
			// 0에 한없이 가깝게 만들기 위해 오른쪽 포인터를 왼쪽으로 옮겨줌
			if(sum > 0) {
				right--;
			}
			// 두 포인터가 가리키는 것들의 합이 0보다 작거나 같은 경우 
			// 0에 한없이 가깝게 만들기 위해 왼쪽 포인터를 오른쪽으로 옮겨줌 
			else {
				left++;
			}
		}
		System.out.println(result1 + " " + result2);

	}

}