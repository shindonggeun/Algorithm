import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		List<Integer> nlist = new ArrayList<>();	// 상근이가 가지고있는 숫자카드 list
		
		for(int i=0; i<N; i++) {
			nlist.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Collections.sort(nlist); 
		StringBuilder sb = new StringBuilder();
		
		// 이분탐색 이용함 (시간복잡도 -> O(MlogN) )
		for(int i=0; i<M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			sb.append(binarySearch(nlist, N, temp) + " ");
		}
		System.out.println(sb);
		
		// 밑에 알고리즘 사용하면 시간초과난다!! (시간복잡도 -> O(MN) )
		/*List<Integer> mlist = new ArrayList<>(); 	// 상근이가 가지고있는 숫자카드인지 확인할 list
		
		for(int i=0; i<M; i++) {
			mlist.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(mlist);
		
		// mlist 사이즈만큼 반복문 돌리기
		for(int i=0; i<mlist.size(); i++) {
			// 상근이가 가지고있는 숫자카드 list에 있는 숫자이면
			if(nlist.contains(mlist.get(i))) {	
				nlist.remove(mlist.get(i));	// 상근이가 가지고 있는 숫자카드 list에서 삭제
				sb.append("1").append(" ");	// 그리고 가지고있다는 표시 1
			}
			// 상근이가 가지고있는 숫자카드 list에 없는 숫자면
			else {
				sb.append("0").append(" ");	// 가지고있지 않다는 표시 0
			}
		}
		System.out.println(sb);*/
		
    }
	// 이분탐색 메서드 (이분탐색하려면 탐색하려는 list나 배열이 오름차순 정렬 되어있어야됨!!) 시간복잡도 -> O(logN) 
	public static int binarySearch(List<Integer> nlist, int N, int search) {
		int first = 0;
		int last = N - 1;
		int mid = 0;
		
		while(first <= last) {
			mid = (first + last) / 2;	// 중간 인덱스
			
			// 중간값과 찾으려는 수가 같은 경우
			if(nlist.get(mid) == search) {	
				return 1;	// 찾았음
			}
			
			// 중간값이 찾으려는 수보다 작으면, 그 이하로는 볼 필요 없음
			if(nlist.get(mid) < search) {
				first = mid + 1;
			} 
			// 중간값이 찾으려는 수보다 크면, 그 이상으로는 볼 필요 없음
			else {
				last = mid - 1;
			}
		}
		return 0;	// 위의 결과 다 돌려도 못찾은 경우 0 리턴
	}
}