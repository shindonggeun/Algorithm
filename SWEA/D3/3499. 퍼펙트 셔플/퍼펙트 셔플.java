import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 테스트 케이스 순회
		for(int tc=1; tc<=testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());	// 카드 개수 입력
			String[] cardArr = new String[N];	// 카드 이름들 저장할 배열
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 카드 이름들 입력
			for(int i=0; i<N; i++) {
				cardArr[i] = st.nextToken();
			}
			int halfStartIdx = 0;	// 카드 덱 절반으로 나눌 때 시작 인덱스 (카드 나눈것 중 두번째 덱에서 사용할 인덱스)
			
			// 카드 개수가 1개인 경우 (나눌 필요도 없음)
			if(N == 1) {
				sb.append(cardArr[0]);	// 카드 한장 출력
			}
			// 카드 개수가 짝수인 경우
			else if(N % 2 == 0) {
				halfStartIdx = N / 2;	// 카드 나눈것 중 두번째 덱에서 사용할 인덱스 카드 개수 절반 나온것 저장 
				// 카드 절반 나눈것 탐색
				for(int i=0; i<halfStartIdx; i++) {
					String card = cardArr[i];	// 카드 나눈것 중 첫번째 덱
					String card2 = cardArr[halfStartIdx + i];	// 카드 나눈것 중  두번째 덱
					sb.append(card).append(" ").append(card2).append(" ");	// 카드 첫번째 덱, 두번째 덱 StringBuilder에 저장
				}
			}
			// 카드 개수가 홀수인 경우
			else if(N % 2 == 1) {
				halfStartIdx = N / 2 + 1;	// 카드 나눈것 중 두번째 덱에서 사용할 인덱스 카드 개수 절반 나온것에 +1 한것 저장
				// 카드 절반 나눈 것 탐색
				for(int i=0; i<halfStartIdx; i++) {
					String card = cardArr[i];	// 카드 나눈것 중 첫번째 덱
					// 인덱스가 카드 개수 넘어가지 않는 경우
					if(halfStartIdx + i < N) {
						String card2 = cardArr[halfStartIdx + i];
						sb.append(card).append(" ").append(card2).append(" ");	// 카드 첫번째 덱, 두번째 덱 StringBuilder에 저장
					}
					// 인덱스가 카드 개수 넘어간 경우
					else {
						sb.append(card);	// 카드 나눈것 중 첫번째 덱만 StringBuilder에 저장
					}
				}
			}
			sb.append("\n");	// 개행
		}
		System.out.print(sb);

	}

}
