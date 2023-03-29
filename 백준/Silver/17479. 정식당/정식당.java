import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());	// 일반 메뉴 수
		int B = Integer.parseInt(st.nextToken());	// 특별 메뉴 수
		int C = Integer.parseInt(st.nextToken());	// 서비스 메뉴 수
		
		HashMap<String, Integer> menu = new HashMap<>();	// 해당 메뉴에 따른 가격 (key: 메뉴 이름, value: 메뉴 가격)
		HashMap<String, String> kindOfMenu = new HashMap<>();	// 메뉴 이름에 따른 타입(key: 메뉴 이름, value: 메뉴 타입)
		
		// 일반 메뉴 입력
		for(int i=0; i<A; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int price = Integer.parseInt(st.nextToken());
			
			menu.put(name, price);	
			kindOfMenu.put(name, "general");
		}
		
		// 특별 메뉴 입력
		for(int i=0; i<B; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int price = Integer.parseInt(st.nextToken());
			menu.put(name, price);
			kindOfMenu.put(name, "special");
		}
		
		// 서비스 메뉴 입력
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			menu.put(name, 0);
			kindOfMenu.put(name, "service");
		}
		
		long generalPrice = 0;	// 주문한 일반 메뉴 가격 총합
		long specialPrice = 0;	// 주문한 특별 메뉴 가격 총합
		int specialMenuCount = 0;	// 주문한 특별 메뉴 수
		int serviceMenuCount = 0;	// 주문한 서비스 메뉴 수
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String orderMenu = st.nextToken();				// 메뉴 이름 입력
			String menuKind = kindOfMenu.get(orderMenu);	// 메뉴의 타입
			
			// 일반 메뉴인 경우
			if(menuKind.equals("general")) {
				generalPrice += menu.get(orderMenu);
			}
			// 특별 메뉴인 경우
			else if(menuKind.endsWith("special")) {
				specialPrice += menu.get(orderMenu);
				specialMenuCount++;
			}
			// 서비스 메뉴인 경우
			else {
				serviceMenuCount++;	
			}
		}
		
		// 옳지 못한 주문의 경우 (1 ~ 3번 경우 중 하나라도 속하면)
		// 1. 일반 메뉴 가격 총합이 20000원 미만인데 특별메뉴 주문한 경우
		// 2. 일반메뉴와 특별메뉴의 총 주문한 가격이 50000원 미만인데 서비스 메뉴 주문한 경우
		// 3. 서비스메뉴 주문한게 2개 이상이 된 경우 (단 하나만 주문 가능)
		if((generalPrice < 20000 && specialMenuCount > 0) || (generalPrice + specialPrice < 50000 && serviceMenuCount > 0)
				|| serviceMenuCount > 1) {
			System.out.print("No");
		}
		else {
			System.out.println("Okay");
		}

	}

}