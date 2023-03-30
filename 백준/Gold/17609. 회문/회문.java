import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			// 투포인터 알고리즘 이용
			int left = 0;	// 왼쪽에서부터 시작하는 포인터
			int right = input.length()-1;	// 오른쪽에서부터 시작하는 포인터
			int find = 0;	// 회문인지 여부를 나타내는 변수
			
			while(left<=right) {
				// 두 포인터가 가리키는 문자가 같은 경우
				if(input.charAt(left) == input.charAt(right)) {
					left++;		// 왼쪽 포인터 증가
					right--;	// 오른쪽 포인터 감소
				}
				// 두 포인터가 가리키는 문자가 같지 않은 경우 (임시포인터를 이용해 비교한 뒤 반복문 빠져나옴)
				else {
					// 두 포인터가 가리키는 문자가 다르므로 일단 회문은 아니다
					// 그러므로 변수값에 1 할당  
					find = 1;	 
					
					int leftTmp = left + 1;		// 임시 왼쪽 포인터
					int rightTmp = right;	// 임시 오른쪽 포인터
					
					// 1. 왼쪽을 이동해서 체크함 (임시 왼쪽 포인터가 가리키는 문자 1개 지웠다고 생각하면 됨)
					while(leftTmp <= rightTmp) {
						// 두 임시포인터가 가리키는 문자가 같은 경우
						if(input.charAt(leftTmp) == input.charAt(rightTmp)) {
							leftTmp++;	// 임시 왼쪽 포인터 증가
							rightTmp--;	// 임시 오른쪽 포인터 감소
						}
						// 두 임시포인터가 가리키는 문자가 다른 경우
						else {
							find++;		// 다른경우 찾았으므로 유사회문인지 아닌지 나타내는 변수 증가시킴
							break;		// while문 빠져나옴
						}
					}
					
					leftTmp = left;			// 임시 왼쪽 포인터 재할당
					rightTmp = right - 1;	// 임시 오른쪽 포인터 재할당
					
					// 2. 임시 오른쪽 포인터를 이동해서 체크함 (임시 오른쪽 포인터가 가리키는 문자 1개를 지웠다고 생각하면 됨)
					while(leftTmp <= rightTmp) {
						// 두 임시포인터가 가리키는 문자가 같은 경우
						if(input.charAt(leftTmp) == input.charAt(rightTmp)) {
							leftTmp++;	// 임시 왼쪽 포인터 증가
							rightTmp--;	// 임시 오른쪽 포인터 감소
						}
						// 두 임시포인터가 가리키는 문자가 다른 경우
						else {
							find++;		// 다른경우 찾았으므로 유사회문인지 아닌지 나타내는 변수 증가시킴
							break;		// while문 빠져나옴
						}
					}
					
					// 위 과정을 거쳐 왼쪽이나 오른쪽 둘중 하나만 다른 경우 find는 2, 왼쪽과 오른쪽 둘다 다른 경우 find는 3이 나오게 됨
					break;	// 왼쪽, 오른쪽 포인터 비교하는 반복문 빠져나옴 
				}
			}
			
			if(find>=2) {
				sb.append(find-1).append("\n");
			}
			else {
				sb.append(find).append("\n");
			}
			
		}
		System.out.print(sb);

	}

}