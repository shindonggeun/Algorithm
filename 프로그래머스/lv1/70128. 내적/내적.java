class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        
        // 두 배열의 길이가 같음
        for(int i=0; i<a.length; i++) {
            answer+=a[i] * b[i];
        }
        
        return answer;
    }
}