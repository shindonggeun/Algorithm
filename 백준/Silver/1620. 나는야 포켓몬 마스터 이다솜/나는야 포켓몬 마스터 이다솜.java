import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 순서 보장하는 HashMap
		Map<String, Integer> map_name = new LinkedHashMap<>();	// key: 포켓몬 이름, value: 번호
		Map<Integer, String> map_number = new LinkedHashMap<>();	// key: 번호, value: 포켓몬 이름
		
		for(int i=1;i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			//map.put(st.nextToken(), Integer.toString(i));
			String name = st.nextToken();
			map_name.put(name, i);
			map_number.put(i, name);
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			// 입력값이(첫글자가) 숫자인 경우 -> map_number 이용해서 포켓몬 이름 추출
			if(isNumber(str.charAt(0))) {
				// 입력값인 숫자(key)를 이용해서 value값인 포켓몬 이름 추출
				sb.append(map_number.get(Integer.parseInt(str))).append("\n");
			}
			// 입력값이(첫글자가) 문자열인 경우(포켓몬 이름인 경우) -> map_name 이용해서 포켓몬 숫자 추출
			else {
				// 입력값인 문자열(key)를 이용해서 value값인 포켓몬 숫자 추출
				sb.append(map_name.get(str)).append("\n");
			}
			
			// 아래 방식대로 하면 시간 초과남
			//String keyOrValue = st.nextToken();
			
			/*if(map.containsKey(keyOrValue)) {
				sb.append(map.get(keyOrValue)).append("\n");
			}
			else if(map.containsValue(keyOrValue)) {
				sb.append(getKey(map, keyOrValue)).append("\n");
			}*/
		}
		System.out.println(sb);

	}
	
	public static boolean isNumber(char ch) {
		boolean check = false;
		
		// 숫자인경우
		if(Character.isDigit(ch)) {
			check = true;
		}
		// 숫자 아닌경우
		else {
			check = false;
		}
		
		return check;
	}

	// hashmap에 value 로 key 찾기
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}
	

}