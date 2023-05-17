import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Set<Integer> set = new HashSet<>();
			
			for(int i=1; i<=N; i++) {
				set.add(i);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(set.contains(num)) {
					set.remove(num);
				}
			}
			
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list);
			
			sb.append("#" + tc + " ");
			for(int i: list) {
				sb.append(i + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}

}
