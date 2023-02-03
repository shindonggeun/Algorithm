import java.util.regex.*;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "\n");
		
		String str = st.nextToken();
		
		// 반드시 U로 시작해서(.) C앞에 문자 U가 하나 이상 있어야하고(*) 
		String regex = "U.*C.*P.*C";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
        	System.out.println("I love UCPC");
        } 
        else {
        	System.out.println("I hate UCPC");
        }
		
		
		
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