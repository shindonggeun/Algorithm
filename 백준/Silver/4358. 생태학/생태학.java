import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		Map<String, Integer> map = new HashMap<>();		// key: 나무 종의 이름, value: 나무 종이 나온 횟수 
		StringBuilder sb = new StringBuilder();
		int count = 0;	// 입력값 횟수를 나타내는 변수
		
		// 입력값이 없는 경우 while문 종료
		while ((s = br.readLine()) != null) {
			map.put(s, map.getOrDefault(s, 0) + 1);
			count++;
		}
		
		List<String> tree_name = new ArrayList<>(map.keySet());	// map의 key값인 나무 종의 이름들 리스트화
		Collections.sort(tree_name);	// 나무 종의 이름 오름차순 정렬
		
		for(String tree: tree_name) {
			double cal = (double) map.get(tree) * 100 / count ;
			// String.format()을 사용해서 반올림 사용하자 (Math.round와 동일함)
			// Math.round()와 차이점 -> 출력할때 소수점이 0인경우 Math.round()는 소수점 절삭해서 나옴
			// String.format()은 절삭하지않고 그대로 리턴
			sb.append(tree + " " + String.format("%.4f", cal)).append("\n");	
		}
		System.out.print(sb);
	}

}