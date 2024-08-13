import java.util.*;
import java.io.*;

public class Main {
	
	static int K; // 이미 가지고 있는 랜선의 개수
	static int N; // 필요한 랜선의 개수
	static int[] lineArr; // 가지고 있는 랜선들의 길이들을 저장한 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lineArr = new int[K];
		
		for (int i=0; i<K; i++) {
			lineArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lineArr);
		
		long start = 1;
		long end = lineArr[K-1];
		long result = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long count = 0;
			
			for (int line: lineArr) {
				count += line / mid;
			}
			
			if (count >= N) {
				start = mid + 1;
				result = mid;
			}
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(result);
	}

}
