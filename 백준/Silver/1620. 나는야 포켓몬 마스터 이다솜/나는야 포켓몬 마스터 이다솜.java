import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, String> nameMap = new HashMap<>();	// key: 포켓몬 이름, value: 해당 포켓몬의 이름에 따른 번호(String)
		Map<String, String> numberMap = new HashMap<>();	// key: 포켓몬 번호(String), value: 해당 포켓몬의 번호에 따른 이름
		
		for(int i=1; i<=N; i++) {
			String name = br.readLine();
			nameMap.put(name, String.valueOf(i));
			numberMap.put(String.valueOf(i), name);
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String numberOrName = br.readLine();
			
			// 포켓몬 이름에 따른 번호를 저장한 맵에 해당 key값이 존재하면
			if(nameMap.containsKey(numberOrName)) {
				sb.append(nameMap.get(numberOrName)).append("\n");
			}
			// 포켓몬 번호에 따른 이름을 저장한 맵에 해당 key값이 존재하면
			else if(numberMap.containsKey(numberOrName)) {
				sb.append(numberMap.get(numberOrName)).append("\n");
			}
		}
		
		System.out.print(sb);
		
		
	}

}
