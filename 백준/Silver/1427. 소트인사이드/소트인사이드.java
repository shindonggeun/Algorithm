import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<num.length(); i++) {
			list.add(num.charAt(i) - '0');
		}
		
		Collections.sort(list, Collections.reverseOrder());
		
		String result = "";
		for(int i=0; i<list.size(); i++) {
			result+=list.get(i);
		}
		System.out.println(result);
    }
}