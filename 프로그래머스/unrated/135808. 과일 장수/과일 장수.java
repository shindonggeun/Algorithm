import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        //ArrayList<Integer> list = Arrays.asList(score);
        Integer[] score_copy = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(score_copy, Collections.reverseOrder());
        ArrayList<Integer> appleBox = new ArrayList<>();
        
        for(int i=0; i<score_copy.length; i++) {
            appleBox.add(score_copy[i]);
            
            if(appleBox.size() == m) {
                //System.out.println(appleBox);
                Collections.sort(appleBox);
                answer+=(appleBox.get(0) * m);
                appleBox.clear();
            }
        }
        
        return answer;
    }
}