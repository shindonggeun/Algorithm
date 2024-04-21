class Solution {
    public long solution(int[] sequence) {
        return Math.max(maxSubarraySum(sequence, 1), maxSubarraySum(sequence, -1));
    }
    
    private long maxSubarraySum(int[] sequence, int start) {
        long maxSoFar = Long.MIN_VALUE;
        long maxEndingHere = 0;
        int sign = start;  // 1 또는 -1로 시작
        
        for (int num : sequence) {
            maxEndingHere += num * sign;
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
            }
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            }
            sign *= -1;  // 부호 전환
        }
        
        return maxSoFar;
    }
}
