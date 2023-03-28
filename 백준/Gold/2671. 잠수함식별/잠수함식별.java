import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String input = st.nextToken();
		
		// +는 앞의 문자가 1개 이상 있다는 것
        // 문제에서  특정한 소리의 반복은 ~로 표시하는데 이것이 한 번 이상 반복되는 것이므로 +를 사용
		// 문자열의 시작을 나타내는 ^, 종료를 나타내는 $를 사용 (이거 안쓸경우 문제 틀림!) 
		// 반례) input: 10011001 , answer: SUBMARINE
		input = input.replaceAll("^(100+1+|01)+$", "a");	// 문자열 패턴으로 시작되면서 종료되는 문자열인 경우 "a로 치환

		// 치환한 문자열이 "a"인 경우 잠수함의 엔진소리임
		if(input.equals("a")) {
			System.out.println("SUBMARINE");
		}
		// 치환한 문자열이 "a"가 아닌경우 물속잡음임
		else {
			System.out.println("NOISE");
		}

	}

}
