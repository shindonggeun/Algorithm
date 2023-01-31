import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, String> map = new HashMap<>();
		List<String> nameList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String str = st.nextToken();
			String[] str_arr = str.split(" ");
			map.put(str_arr[0], str_arr[1]);
		}
		
		
		for(String key: map.keySet()) {
			if(map.get(key).equals("enter")) {
				nameList.add(key);
			}
		}
		
		Collections.sort(nameList, Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		
		for(String name: nameList) {
			sb.append(name).append("\n");
		}
		System.out.println(sb);
    }
	
}