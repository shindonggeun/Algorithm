class Solution {
    public int solution(int[] cookie) {
        int answer = -1;
        
        int n = cookie.length;
        int maxCookie = 0;
        
        int[] sumArr = new int[n+1];
        
        for (int i=0; i<n; i++) {
            sumArr[i+1] = sumArr[i] + cookie[i];
        }
        
        for (int i=0; i<n-1; i++) {
            int left = i;
            int right = i+1;
            
            while (left >= 0 && right < n) {
                int leftSum = sumArr[i+1] - sumArr[left];
                int rightSum = sumArr[right+1] - sumArr[i+1];
                
                if (leftSum == rightSum) {
                    maxCookie = Math.max(maxCookie, leftSum);
                }
                
                if (leftSum <= rightSum && left > 0) {
                    left--;
                }
                else if (right < n-1) {
                    right++;
                }
                else {
                    break;
                }
            }
        }
        
        answer = maxCookie;
        return answer;
    }
}