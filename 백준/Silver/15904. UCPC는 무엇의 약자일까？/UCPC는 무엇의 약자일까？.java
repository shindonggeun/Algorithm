import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "\n");
		
		String str = st.nextToken();
		
		String ucpc = "UCPC";
		int index = 0;
		boolean check = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ucpc.charAt(index)) {
				index++;
				if (index == 4) {
					check = true;
					break;
				}
			}
		}
		
		if(check == true) {
			System.out.println("I love UCPC");
		}
		else {
			System.out.println("I hate UCPC");
		}
		
		
		// 정규표현식에서 []안에 ^ -> not을 의미
		/*str = str.replaceAll("[^UCP]", "");	// 대문자 U, C, P가 아닌것들은 전부 빈문자열로 치환
		System.out.println(str);	// 반례) UCAPC -> 이것도 UCPC로 나오기때문에 틀린 접근이다
		
		if(str.equals("UCPC")) {
			System.out.println("I love UCPC");
		}
		else {
			System.out.println("I hate UCPC");
		}*/
	}

}