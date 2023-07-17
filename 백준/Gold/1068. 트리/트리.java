import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int root;	// 루트노드 번호
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int targetNode;	// 지울 노드 번호
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		// 0번노드부터 N-1번 노드 생성
		for(int i=0; i<N; i++) {
			tree.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 입력값이 -1인 경우 루트노드
			if(num == -1) {
				root = i;
			}
			else {
				tree.get(num).add(i);	// 해당 입력번호 노드(부모노드)에 자식노드(i) 추가
			}
		}
		
		targetNode = Integer.parseInt(br.readLine());
		dfs(root);
		System.out.println(count);
	}
	
	public static void dfs(int node) {
		// 삭제할 노드인 경우 깊이우선탐색 종료
		if(node == targetNode) {
			return;
		}
		
		// 해당 노드의 자식이 없는 경우 -> 리프노드이다
		if(tree.get(node).size() == 0) {
			count++;
			return;
		}
		
		for(int vertex: tree.get(node)) {
			// 해당 노드의 자식노드가 삭제할 노드면서 동시에 그 해당 노드가 한개의 자식노드를 가진 경우
			// 이 코드가 있어야지만 반례 통과
			if(vertex == targetNode && tree.get(node).size() == 1) {
				count++;
				return;
			}
			dfs(vertex);
		}
		
		
	}

}
