import java.io.*;
import java.util.*;

public class Main {

	static class Information {
		int nation = 0;
		int studentNum = 0;
		int score = 0;
		
		public Information(int nation, int studentNum, int score) {
			this.nation = nation;
			this.studentNum = studentNum;
			this.score = score;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		List<Information> list = new ArrayList<>();	// 참가국 학생의 정보를 담은 리스트
		Map<Integer, Integer> map = new HashMap<>();	// key: 해당 국가 번호, value: 메달 수
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int nation = Integer.parseInt(st.nextToken());
			int studentNum = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			
			list.add(new Information(nation, studentNum, score));
			map.put(nation, 0);
		}
		
		Collections.sort(list, new Comparator<Information>() {
			@Override
			public int compare(Information i1, Information i2) {
				return i2.score - i1.score;	// 각 국가 상관없이 점수로 내림차순 정렬
			}
		});
		
		int count = 0;
		
		for(Information info: list) {
			// 해당 국가의 메달 총 개수가 2개인 경우 (최대 2개까지 가능이므로)
			if(map.get(info.nation) == 2) {
				continue;
			}
			// 해당 국가 메달 총 개수가 2개가 안되는 경우
			else {
				sb.append(info.nation).append(" ").append(info.studentNum).append("\n");
				count++;
				map.put(info.nation, map.getOrDefault(info.nation, 0) + 1);	// 해당 국가의 메달 수 증가
			}
			
			// 3등까지 출력한 뒤 빠져나오도록 break
			if(count == 3) {
				break;
			}
		}
		System.out.print(sb);
		
	}

}