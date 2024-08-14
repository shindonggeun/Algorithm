import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] dollArr;
	static List<Integer> lionList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dollArr = new int[N];
		lionList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			dollArr[i] = Integer.parseInt(st.nextToken());
			if (dollArr[i] == 1) {
				lionList.add(i);
			}
		}
		
		if (lionList.size() < K) {
			System.out.println(-1);
		}
		else {
			int start = 0;
			int end = K-1;
			int minLength = Integer.MAX_VALUE;
			int length = 0;
			
			while (end < lionList.size()) {
				length = lionList.get(end) - lionList.get(start) + 1;
				minLength = Math.min(minLength, length);
				
				start++;
				end++;
			}
			
			System.out.println(minLength);
		}
		
		

	}

}