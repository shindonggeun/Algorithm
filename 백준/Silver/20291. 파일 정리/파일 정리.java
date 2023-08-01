import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();	// key: 파일 확장자명, value: 해당 파일 확장자명 개수
		for(int i=0; i<N; i++) {
			String fileName = br.readLine();
			// split() 메서드는 정규표현식을 사용해도 되고 안해도 된다
			// 근데 .은 정규표현식을 사용하지 않으면 -> 모든 글자로 인식하기 때문에 잘랐을 경우 빈 문자열이 리턴되게 된다
			// 정규표현식 []안에 "."을 넣어서 "." 기준으로 문자열 잘라서 문자열 배열로 저장
			// 즉, [1] -> 파일 확장자명이 저장됨
			String[] fileNameSplit = fileName.split("[.]");	
			map.put(fileNameSplit[1], map.getOrDefault(fileNameSplit[1], 0) + 1);
		}
		
		List<String> fileNameList = new ArrayList<>(map.keySet());	// map에 저장된 key값들 list화 
		
		Collections.sort(fileNameList);	// 파일 확장자명이 저장된 리스트 오름차순 정렬 (이름 사전순으로 정렬)
		
		StringBuilder sb = new StringBuilder();
		// 파일 확장자명을 저장한 리스트들 탐색
		for(String fileName: fileNameList) {
			// 파일확장자명과 파일 확장자명의 개수를 StringBuilder에 담아줌
			sb.append(fileName).append(" ").append(map.get(fileName)).append("\n");
		}
		
		System.out.print(sb);

	}

}