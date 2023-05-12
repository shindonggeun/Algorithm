import java.util.*;
import java.io.*;

public class Main {
	
	static int L;
	static int C;
	static char[] arr;
	static char[] output;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		output = new char[L];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		// 입력받은 문자들을 오름차순 정렬해줌 
		// 백트래킹 이용해서 암호 만들 때 사전순으로 암호 만들기 위해
		Arrays.sort(arr);	
		
		backTracking(0, 0);
		System.out.print(sb);
	}
	
	// 조합 메서드 이용
	public static void backTracking(int depth, int idx) {
		if(depth == L) {
			if(isValid()) {
				String str = new String(output);
				sb.append(str).append("\n");
			}
			return;
		}
		
		for(int i=idx; i<C; i++) {
			output[depth] = arr[i];
			backTracking(depth+1, i+1);
		}
	}
	
	// 최소 모음 1개 이상 자음 2개 이상으로 이루어졌는지 확인하는 메서드
	public static boolean isValid() {
		int moCount = 0;	// 모음개수
		int jaCount = 0;	// 자음개수
		
		for(char ch: output) {
			// 모음인경우
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				moCount++;
			}
			// 자음인경우
			else {
				jaCount++;
			}
		}
		
		// 모음개수가 1개이상이면서 자음개수가 2개이상인 경우 true 반환 
		if(moCount >= 1 && jaCount >= 2) {
			return true;
		}
		
		// 그 이외의 경우는 false 반환
		return false;
	}

}
