import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 학회원 수
		long M = Integer.parseInt(st.nextToken());	// 최소 능력치
		
		st = new StringTokenizer(br.readLine());
		long[] power = new long[N];	// 각 회원의 능력치를 저장해주는 배열
		
		for(int i=0; i<N; i++) {
			power[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(power);	// 회원들의 능력치를 담은 배열 오름차순 정렬
		
		// 투포인터 알고리즘 이용
		int left = 0;
		int right = power.length - 1;
		int count = 0;	// 견학 보낼 수 있는 팀의 수 
		
		while(left < right) {
			long teamPower = power[left] + power[right];
			// 팀의 능력치가 최소 능력치 이상인 경우
			if(teamPower >= M) {
				count++;	
				left++;		
				right--;
			}
			else {
				left++;
			}
		}
		System.out.println(count);

	}

}
