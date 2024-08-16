import java.util.*;
import java.io.*;

public class Main {
	
	static class Product {
		int weight;
		int value;
		
		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	static int N;
	static int K;
	static Product[] productArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		productArr = new Product[N];
		dp = new int[K+1];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			productArr[i] = new Product(weight, value);
		}
		
		for (int i=0; i<N; i++) {
			int weight = productArr[i].weight;
			int value = productArr[i].value;
			
			for (int j=K; j>=1; j--) {
				if (weight <= j) {
					dp[j] = Math.max(dp[j], dp[j - weight] + value);
				}
			}
		}
		
		System.out.println(dp[K]);

	}

}