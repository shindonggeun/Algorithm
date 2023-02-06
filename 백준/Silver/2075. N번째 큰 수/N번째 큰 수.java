import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Collections.sort(list, Collections.reverseOrder());	// list 내림차순 정렬
		System.out.println(list.get(N-1));
	}

}