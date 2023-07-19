import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int total = 0;	// 각 역마다 남아있는 현재 사람의 수
		int max = 0;	// 최대 사람의 수
		
		// 10개의 역 입력받기
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			total-=out;	// 내린사람들은 빼주고
			total+=in;		// 탄 사람 더해주고
			max = Math.max(total, max);
		}
		System.out.println(max);

	}

}
