import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			String[] cardArr = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				cardArr[i] = st.nextToken();
			}
			int halfStartIdx = 0;
			
			if(N == 1) {
				sb.append(cardArr[0]);
			}
			// 카드 개수가 짝수인 경우
			else if(N % 2 == 0) {
				halfStartIdx = N / 2;
				for(int i=0; i<halfStartIdx; i++) {
					String card = cardArr[i];
					String card2 = cardArr[halfStartIdx + i];
					sb.append(card).append(" ").append(card2).append(" ");
				}
			}
			// 카드 개수가 홀수인 경우
			else if(N % 2 == 1) {
				halfStartIdx = N / 2 + 1;
				for(int i=0; i<halfStartIdx; i++) {
					String card = cardArr[i];
					if(halfStartIdx + i < N) {
						String card2 = cardArr[halfStartIdx + i];
						sb.append(card).append(" ").append(card2).append(" ");
					}
					else {
						sb.append(card);
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}

}
