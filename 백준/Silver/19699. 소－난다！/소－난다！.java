import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] output;
	static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		ArrayList<Integer> list = new ArrayList<>(set);
		
		if(list.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		
		for(int i: list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
		
	}
	// 조합 메서드
	public static void dfs(int depth, int idx) {
		if(depth == M) {
			int sum = 0;
			for(int i=0; i<M; i++) {
				sum+=output[i];
			}
			if(isPrime(sum)) {
				set.add(sum);
			}
			return;
		}
		
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];
			dfs(depth+1, i+1);
		}
	}
	// 소수인지 판별해주는 메서드
	public static boolean isPrime(int num) {
		boolean find = true;
		if(num == 1) {
			find = false;
			return find;
		}
		
		for(int i=2; i<=num/2; i++) {
			// 나눠지면 소수아님
			if(num % i == 0) {
				find = false;
				break;
			}
		}
		return find;
	}

}
