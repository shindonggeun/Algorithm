import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> map = new HashMap<>();	// key: 꿀벌이 하는 일, value: 해당 일을 몇번 했는지
		String[] workArr = {"Re", "Pt", "Cc", "Ea", "Tb", "Cm", "Ex"};
		
		// 일단 꿀벌이 하는 일맵에 저장해줌
		for(String work: workArr) {
			map.put(work, 0);
		}
		
		String input = "";
		double total = 0;
		// 한줄 입력이 주어지지 않을 때 까지 한줄입력 반복해서 받기
		while((input = br.readLine()) != null) {
			String[] inputArr = input.split(" ");	// 한줄 입력 받은 것 공백단위로 쪼개주기
			total += inputArr.length;				// 한줄 입력 당 일한것들 총 개수 더해주기
			for(String str: inputArr) {
				map.put(str, map.getOrDefault(str, 0) + 1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(String work: workArr) {
			String rate = String.format("%.2f", (double)map.get(work) / total);	// 해당 일한 비율 소수점 둘째자리까지 출력
			sb.append(work).append(" ").append(map.get(work)).append(" ").append(rate).append("\n");
		}
		sb.append("Total").append(" ").append((int)total).append(" ").append("1.00");
		System.out.print(sb);
		
		
		
	}

}
