import java.util.*;
import java.io.*;

public class Main {

	static int k;
	static boolean[] visited;
	static String[] signs;
	static ArrayList<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		signs = new String[9];	// 부등호는 최대 9개
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<k; i++) {
			signs[i] = st.nextToken();
		}
		
		visited = new boolean[10];
		
		dfs(0, "");	// 백트래킹 탐색 시작
		Collections.sort(list);	// 오름차순 정렬
		System.out.println(list.get(list.size() - 1));	// 최대값
		System.out.println(list.get(0));				// 최소값
	}
	
	// 백트래킹 알고리즘 이용
	public static void dfs(int depth, String number) {
		// 재귀 종료조건
		if(depth == k+1) {
			list.add(number);
			return;
		}
		
		for(int i=0; i<10; i++) {
			// 이미 방문했으면 다시 넘어감(다시 위로)
			if(visited[i]) {
				continue;
			}
			
			// 처음 시작이거나 숫자 두개 체크했을 때 부등호대로 맞는 숫자면
			if(depth == 0 || check(Character.getNumericValue(number.charAt(depth-1)), i,  signs[depth - 1])) {
				visited[i] = true;	// 방문처리
				dfs(depth+1, number+Integer.toString(i));	// 재귀호출
				visited[i] = false;	// 다시 방문처리 해제
			}
		}
	}
	
	// 두 숫자와 부등호를 이용해서 부등호대로 그 숫자의 관계가 맞는지 판단하는 메서드
	public static boolean check(int num1, int num2, String str) {
		boolean check = false;
		
		if(str.equals(">")) {
			if(num1 > num2) {
				check = true;
			}
		}
		else if(str.equals("<")) {
			if(num1 < num2) {
				check = true;
			}
		}
		
		return check;
	}
	

}
