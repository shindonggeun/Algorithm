import java.util.*;
import java.io.*;

public class Main {
	
	static int L;
	static int C;
	static char[] alpabet;
	static char[] combination;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		alpabet = new char[C];
		
		for (int i=0; i<C; i++) {
			alpabet[i] = st.nextToken().charAt(0);
		}
		
		// 입력받은 알파벳 문자들 오름차순 정렬
		// 백트래킹 알고리즘 이용해서 해당 알파벳 문자들을 조합해서 암호 만들 때 사전순으로 만들기 위해
		Arrays.sort(alpabet);
		
		combination = new char[L];
		sb = new StringBuilder();
		
		generateCodeCombination(0, 0);
		
		System.out.print(sb);
	}
	
	public static void generateCodeCombination(int depth, int idx) {
		if (depth == L) {
			if (codeValid()) {
				String code = new String(combination);
				sb.append(code).append("\n");
			}
			return;
		}
		
		for (int i=idx; i<C; i++) {
			combination[depth] = alpabet[i];
			generateCodeCombination(depth+1, i+1);
		}
	}
	
	public static boolean codeValid() {
		int moCount = 0;
		int jaCount = 0;
		
		for (int i=0; i<L; i++) {
			char ch = combination[i];
			
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				moCount++;
			}
			else {
				jaCount++;
			}
		}
		
		if (moCount >= 1 && jaCount >= 2) {
			return true;
		}
		
		return false;
	}

}