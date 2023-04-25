import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		int answerX = 0;
		int answerY = 0;
		
		// 브루트포스 알고리즘 이용 => 범위가 -999 <= (a,b,c,d,e,f) <= 999 이러므로 브루트포스 알고리즘 이용하는게 좋다
		// 모든 x와 y를 탐색해서 맞는 값 나오면 반복문 빠져나옴
		for(int x=-999; x<=999; x++) {
			for(int y=-999; y<=999; y++) {
				if((a*x+b*y==c) && (d*x+e*y==f)) {
					answerX = x;
					answerY = y;
					break;
				}
			}
		}
		
		System.out.println(answerX + " " + answerY);

	}

}
