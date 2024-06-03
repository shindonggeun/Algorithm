class Solution {
    public int solution(String s) {
        int answer = 0;
        
        
        // 팰린드롬의 최소 길이는 1 (문자 하나인 경우)
        int MaxLength = 1; // 가장 긴 팰린드롬의 길이
        
        // 문자열 시작 인덱스부터 문자열 끝 인덱스까지 탐색
        for (int i=0; i<s.length(); i++) {
            int len1 = countPalindrome(s, i, i); // 팰린드롬이 홀수인 경우 해당 팰린드롬 길이 계산 (...aba...)
            int len2 = countPalindrome(s, i, i+1); // 팰린드롬이 짝수인 경우 해당 팰린드롬 길이 계산 (...aa...)
            int temp = Math.max(len1, len2); // 두 팰린드롬 중 가장 긴 길이 계산
            MaxLength = Math.max(MaxLength, temp); // 가장 긴 팰린드롬의 길이 갱신
        }
        
        answer = MaxLength;

        return answer;
    }
    
    // 팰린드롬의 길이를 계산해주는 메서드
    public int countPalindrome(String s, int left, int right) {
        // 양쪽 끝으로 점차 확장하면서 팰린드롬 검사
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // 왼쪽 인덱스 감소 (즉, 왼쪽으로 포인터 한칸 이동)
            right++; // 오른쪽 인덱스 감소 (즉, 오른쪽으로 포인터 한칸 이동)
        }

        // 팰린드롬의 실제 길이 반환
        return right - left - 1;
    }
}