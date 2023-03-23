import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());
			list.add(height);
		}
		
		Collections.sort(list);
		int temp = list.get(0);
		List<Integer> diffList = new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			int diff = list.get(i) - temp;
			diffList.add(diff);
			temp = list.get(i);
		}
		Collections.sort(diffList);
		int sum = 0;
		
		for(int i=0; i<N-K; i++) {
			sum+=diffList.get(i);
		}
		System.out.println(sum);
		
	}

}