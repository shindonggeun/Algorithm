import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String word = st.nextToken();
		int result = 0;
		
		for(int i=0; i<word.length(); i++) {
			switch(word.charAt(i)) {
				case 'A': case 'B': case 'C':	// 다이얼 2번 입력시
					result+=3;
					break;
				case 'D': case 'E': case 'F':	//다이얼 3번 입력시
					result+=4;
					break;
				case 'G': case 'H': case 'I':	// 다이얼 4번 입력시
					result+=5;
					break;
				case 'J': case 'K': case 'L':	// 다이얼 5번 입력시
					result+=6;
					break;
				case 'M': case 'N': case 'O':	// 다이얼 6번 입력시
					result+=7;
					break;
				case 'P': case 'Q': case 'R': case 'S':	// 다이얼 4번 입력시
					result+=8;
					break;
				case 'T': case 'U': case 'V':	// 다이얼 5번 입력시
					result+=9;
					break;
				case 'W': case 'X': case 'Y': case 'Z':	// 다이얼 6번 입력시
					result+=10;
					break;
			}
		}
		System.out.println(result);
    }
}