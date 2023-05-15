import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int count = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		int number = 666;
		String numStr = "666";
		
		// 브루트포스 알고리즘 이용 (완전탐색)
		while(count != cnt) {
			numStr = String.valueOf(number);
			// 해당 수의 "666"이 포함되면 cnt 증가
			if(numStr.contains("666")) {
				cnt++;
			}
			number++;
		}
		System.out.println(numStr);
			

	}

}
