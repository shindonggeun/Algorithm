import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();	// 중복 허용안하는 자료구조
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> list = new ArrayList<>(set);	// set을 list화
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(int i: list) {
			sb.append(i + " ");
		}
		System.out.println(sb);

	}

}