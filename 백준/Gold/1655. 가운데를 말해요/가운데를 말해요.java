import java.io.*;
import java.util.*;

public class Main {

    static int N; // 정수 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 왼쪽 절반 (최대 힙)
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());

        // 오른쪽 절반 (최소 힙)
        PriorityQueue<Integer> right = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            left.add(num);

            // 1. right가 비어있지 않으면서, left의 루트가 right의 루트보다 크면 교환
            if (!right.isEmpty() && left.peek() > right.peek()) {
                right.add(left.poll());
                left.add(right.poll());
            }

            // 2. 크기 균형 맞추기 (left는 right와 같거나 하나 더 많음)
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            } else if (right.size() > left.size()) {
                left.add(right.poll());
            }

            // 3. 현재 중앙값은 left의 루트
            sb.append(left.peek()).append("\n");
        }

        System.out.print(sb);
    }
}