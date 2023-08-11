import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 과일 개수 입력
		int L = Integer.parseInt(st.nextToken());	// 스네이크 버드의 초기 길이 입력
		
		st = new StringTokenizer(br.readLine());
		int[] fruitHeight = new int[N];	// 과일들이 있는 높이를 저장한 배열
		for(int i=0; i<N; i++) {
			int tempHeight = Integer.parseInt(st.nextToken());
			fruitHeight[i] = tempHeight;
		}
		
		Arrays.sort(fruitHeight);	// 과일들이 있는 높이를 저장한 배열 오름차순 정렬
		
		// 과일들이 있는 높이가 저장된 배열 다 탐색하기
		for(int height: fruitHeight) {
			// 해당 높이보다 스네이크버드의 길이가 크거나 같은 경우
			if(L >= height) {	
				L++;	// 스네이크버드의 길이 증가
			}
		}
		System.out.println(L);

	}

}
