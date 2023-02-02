import java.util.*;
import java.io.*;

public class Main {

	static class Coordinate {
		int x = 0;
		int y = 0;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Coordinate> xyList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			xyList.add(new Coordinate(x, y));
		}
		
		Collections.sort(xyList, new Comparator<Coordinate>() {

			@Override
			public int compare(Coordinate c1, Coordinate c2) {
				// x좌표가 같으면 y좌표 증가하는 순으로 (y좌표 오름차순)
				if(c1.x == c2.x) {
					return c1.y - c2.y;
				}
				// x좌표가 같지 않으면 x좌표 증가하는 순으로(오름차순)
				else {
					return c1.x - c2.x;
				}
			}			
		});
		
		StringBuilder sb = new StringBuilder();
		for(Coordinate coor: xyList) {
			sb.append(coor.x + " " + coor.y).append("\n");
		}
		System.out.print(sb);

	}

}