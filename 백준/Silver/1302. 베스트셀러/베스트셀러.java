import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();	// key: 책 이름, value: 책이 팔린 횟수
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();	// 책 이름 입력받기	
			map.put(input, map.getOrDefault(input, 0) + 1);
		}
		
		List<Integer> sellList = new ArrayList<>(map.values());	// 책 판 횟수를 저장한 리스트(value값 리스트화)
		Collections.sort(sellList, Collections.reverseOrder());	// 책 판 횟수 내림차순 정렬
		List<String> bookList = new ArrayList<>();	// 책 가장 많이 팔린것들 저장할 리스트
		
		// map에 저장된 key값(책 이름) 탐색하기
		for(String book: map.keySet()) {
			// 해당 책이 가장 많이 팔렸으면
			if(map.get(book) == sellList.get(0)) {
				bookList.add(book);	// 책 가장 많이 팔린 리스트에 추가해줌
			}
		}
		
		Collections.sort(bookList);	// 사전순으로 정렬
		System.out.println(bookList.get(0));
	}

}
