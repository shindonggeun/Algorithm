class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int MaxLength = 1;
        
        for (int i=0; i<s.length(); i++) {
            int len1 = countPalindrome(s, i, i);
            int len2 = countPalindrome(s, i, i+1);
            int temp = Math.max(len1, len2);
            MaxLength = Math.max(MaxLength, temp);
        }
        
        answer = MaxLength;

        return answer;
    }
    
    public int countPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        return right - left - 1;
    }
}