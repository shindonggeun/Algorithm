import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			String input = st.nextToken();
			// 입력받은 문자열에서 마지막글자가 "Cheese"로 끝나는 문자열인 경우
			if(input.endsWith("Cheese")) {
				set.add(input);	
			}
		}
		// 서로 다른 치즈가 4가지 종류 이상인 경우
		if(set.size() >= 4) {
			System.out.println("yummy");
		}
		else {
			System.out.println("sad");
		}
		
	}

}
