class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int walletMin = Math.min(wallet[0], wallet[1]);
        int walletMax = Math.max(wallet[0], wallet[1]);
        
        int billWidth = bill[0];
        int billHeight = bill[1];
        
        while (true) {
            int billMin = Math.min(billWidth, billHeight);
            int billMax = Math.max(billWidth, billHeight);
            
            if (billMin <= walletMin && billMax <= walletMax) {
                break;
            }
            
            if (billWidth >= billHeight) {
                billWidth /= 2;
            } 
            else {
                billHeight /= 2;
            }
            
            answer++;
        }
        
        return answer;
    }
}