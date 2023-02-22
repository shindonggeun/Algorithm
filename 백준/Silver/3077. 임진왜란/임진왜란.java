import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();		// key: 해전 이름, value: 일어난 순서(0부터 시작)
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			String warName = st.nextToken();
			map.put(warName, i);
		}
		st = new StringTokenizer(br.readLine(), "\n");
		String[] input = st.nextToken().split(" ");		// 답안 입력값 공백단위로 쪼갬
		
		int count = 0;
		// 브루트포스 알고리즘 이용
		// ex) alpha = 0 , beta = 1, gamma = 2
		// (0, 1) (0, 2) (0, 3) 이런식으로 뒤의 값이 앞에값보다 큰 경우 정답 맞은것임 -> count = 3
		for(int i=0; i<input.length-1; i++) {
			for(int j=i+1; j<input.length; j++) {
				if(map.get(input[i]) < map.get(input[j])) {
					count++;
				}
			}
		}
		System.out.println(count + "/" + (N*(N-1)/2));

	}

}