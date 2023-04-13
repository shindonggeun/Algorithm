import java.util.*;
import java.io.*;

public class Main {
	
	static int[] numArr;	// 숫자 저장할 배열
	static int N;		// 숫자 개수
	static int[] operator = new int[4];	// 연산자 수 
	static int min = Integer.MAX_VALUE;	// 최소값
	static int max = Integer.MIN_VALUE;	// 최대값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		numArr = new int[N];
		
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(numArr[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	// 순열 메서드 이용하기
	public static void dfs(int num, int idx) {
		if(idx == N) {
			//System.out.println(num);
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operator[i] > 0) {
				operator[i]--;
				// 덧셈
				if(i == 0) {
					dfs(num + numArr[idx], idx+1);
				}
				// 뺄셈
				else if(i == 1) {
					dfs(num - numArr[idx], idx+1);
				}
				// 곱셈
				else if(i == 2) {
					dfs(num * numArr[idx], idx+1);
				}
				// 나눗셈
				else if(i == 3) {
					dfs(num / numArr[idx], idx+1);
				}
				
				operator[i]++;
			}
		}
	}

}
