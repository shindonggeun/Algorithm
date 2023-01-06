import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        //Integer[] B_copy = Arrays.stream(B).boxed().toArray(Integer[]::new);
        
        Arrays.sort(A); // A배열은 오름차순
        //Arrays.sort(B_copy, Collections.reverseOrder()); // B_copy배열은 내림차순
        Arrays.sort(B);
        
        for(int i=0; i<A.length; i++) {
            answer+=A[i] * B[B.length - i - 1];
        }

        return answer;
    }
}