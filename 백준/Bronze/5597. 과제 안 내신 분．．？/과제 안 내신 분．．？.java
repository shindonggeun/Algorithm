import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] homework = new boolean[31];	// 1 ~ 30번 까지 출석번호 중 과제 제출한 사람 저장하는 배열
		
		// 28명만 과제 제출했으므로 입력 28번 받음
		for(int i=0; i<28; i++) {
			int num = Integer.parseInt(br.readLine());
			homework[num] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		// 1번부터 30번까지 순차적으로 탐색
		for(int i=1; i<=30; i++) {
			// 숙제 안낸 사람인 경우 해당 번호 출력
			if(!homework[i]) {
				sb.append(i).append("\n");
			}
		}
		System.out.print(sb);

	}

}
