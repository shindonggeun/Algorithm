import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();	// 숫자를 담은 자료구조 HashSet (중복허용 X)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			set.add(num);
		}
		
		List<Integer> list = new ArrayList<>(set);	// set을 list화
		Collections.sort(list);	// 리스트 오름차순 정렬(숫자 오름차순 정렬)
		
		StringBuilder sb = new StringBuilder();
		for(int num: list) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}

}
