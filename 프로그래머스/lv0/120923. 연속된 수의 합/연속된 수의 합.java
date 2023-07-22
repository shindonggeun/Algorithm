class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        
        int nSum = num * (num + 1) / 2; // 1부터 num까지의 자연수의 합을 구하는 공식
        int start = (total - nSum) / num;   // 원하는 합을 만들기 위해 시작하는 인덱스 찾는 공식

        for(int i=1; i<=num; i++){
                answer[i - 1] = i + start;
        }
        
        return answer;
        
    }
}