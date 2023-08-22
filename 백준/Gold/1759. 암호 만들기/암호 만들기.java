import java.util.*;
import java.io.*;

public class Main {
	
	static int L;
	static int C;
	static char[] input;	// 입력받은 알파벳을 저장할 배열
	static char[] output;	// 조합으로 뽑아낸 알파벳(암호)을  저장할 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new char[C];
		output = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		// 입력받은 문자들을 오름차순 정렬
		// 백트래킹 이용해서 암호 만들 때 사전순으로 암호 만들기 위해
		Arrays.sort(input);
		
		backTracking(0, 0);	// 백트래킹 메서드 호출 (암호 조합 만들기)
		System.out.print(sb);
	}
	
	// 백트래킹 메서드 (조합 메서드)
	public static void backTracking(int depth, int idx) {
		// 해당 깊이(조합 선택 횟수)가 L이 된 경우 (종료조건)
		if(depth == L) {
			if(isValid()) {
				String str = new String(output);	// 해당 문자들 문자열로 만들어 준 뒤 (암호문)
				sb.append(str).append("\n");	// StringBuilder에 해당 문자열 저장 한 뒤
			}
			return;	// 메서드 종료
		}
		
		// 조합 뽑기
		for(int i=idx; i<C; i++) {
			output[depth] = input[i];
			backTracking(depth+1, i+1);
		}
		
	}
	
	// 최소 모음 1개 이상 자음 2개 이상으로 이루어졌는지 확인해주는 메서드
	public static boolean isValid() {
		int moCount = 0;	// 모음 개수
		int jaCount = 0;	// 자음 개수
		
		// 문자들의 조합 저장한 것들 탐색하기
		for(char ch: output) {
			// 모음인 경우
			if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				moCount++;
			}
			// 자음인 경우
			else {
				jaCount++;
			}
		}
		
		// 모음이 1개 이상이면서 동시에 자음이 2개 이상인 경우
		if(moCount >= 1 && jaCount >= 2) {
			return true;	// true 반환
		}
		// 그 이외의 경우는 false 반환
		return false;
	}

}
