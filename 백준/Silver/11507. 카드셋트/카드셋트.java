import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine() ,"\n");
		
		Set<String> pCardSet = new HashSet<>();	// 가지고있는 스페이드 카드 목록 
		Set<String> kCardSet = new HashSet<>();	// 가지고있는 하트 카드 목록
		Set<String> hCardSet = new HashSet<>();	// 가지고있는 다이아몬드 카드 목록
		Set<String> tCardSet = new HashSet<>();	// 가지고있는 클럽 카드 목록
		String input = st.nextToken();	// 입력값 
		List<String> list = new ArrayList<>();	// 입력값에서 3자리씩 끊어서(카드) 넣어준 리스트
		boolean find = false;			// 똑같은 카드 찾았는지 판단하는 변수
		StringBuilder sb = new StringBuilder();
		
		// 입력값에서 카드 3자리씩 끊어서 넣어줌 -> 문자열 substring
		for(int i=0; i<input.length(); i+=3) {
			list.add(input.substring(i, i+3));
		}
		
		// 가지고있는 카드 리스트 순회
		for(String card: list) {
			// p(스페이드) 카드인 경우
			if(card.charAt(0) == 'P') {
				if(pCardSet.contains(card)) {
					find = true;
					break;
				}
				pCardSet.add(card);		// p 카드 세트에 넣어줌
			}
			// k(하트) 카드인 경우
			else if(card.charAt(0) == 'K') {
				if(kCardSet.contains(card)) {
					find = true;
					break;
				}
				kCardSet.add(card);		// k 카드 세트에 넣어줌
			}
			// H(다이아몬드) 카드인 경우
			else if(card.charAt(0) == 'H') {
				if(hCardSet.contains(card)) {
					find = true;
					break;
				}
				hCardSet.add(card);		// h 카드 세트에 넣어줌
			}
			// T(클럽) 카드인 경우
			else if(card.charAt(0) == 'T') {
				if(tCardSet.contains(card)) {
					find = true;
					break;
				}
				tCardSet.add(card);		// t 카드 세트에 넣어줌
			}
		}
		
		// 똑같은카드가 존재하는 경우
		if(find) {
			System.out.println("GRESKA");
		}
		// 똑같은카드 존재하지 않는 경우
		else {
			sb.append((13-pCardSet.size())).append(" ")
			.append((13-kCardSet.size())).append(" ")
			.append((13-hCardSet.size())).append(" ")
			.append((13-tCardSet.size()));
			System.out.println(sb);
		}
	}

}
