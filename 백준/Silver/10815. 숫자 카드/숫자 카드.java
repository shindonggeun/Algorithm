import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 상근이가 가지고 있는 숫자 카드의 개수
	static int M; // 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 개수
	static Set<Integer> cardSet; // 상근이가 가지고 있는 숫자 카드들을 저장할 해시셋

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		cardSet = new HashSet<>(); // 숫자 카드들을 저장할 해시셋 초기화
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int cardNum = Integer.parseInt(st.nextToken());
			cardSet.add(cardNum); // 해시셋에 상근이가 가지고 있는 숫자 카드 저장
		}
		
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(); // 결과값들을 저장할 StringBuilder
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken()); // 숫자 입력
			
			// 해당 숫자가 상근이가 가지고 있는 숫자 카드인 경우
			if (cardSet.contains(num)) {
				sb.append(1);
			}
			// 해당 숫자가 상근이가 가지고 있는 숫자 카드가 아닌 경우
			else {
				sb.append(0);
			}
			
			sb.append(" "); // 공백까지 같이 StringBuilder에 저장
		}
		
		System.out.println(sb); // 최종 결과값 출력

	}

}