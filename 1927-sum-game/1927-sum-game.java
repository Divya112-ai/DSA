class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < mid; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQ++;
            } else {
                leftSum += ch - '0';
            }
        }

        for (int i = mid; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQ++;
            } else {
                rightSum += ch - '0';
            }
        }

        // Odd number of '?' -> Alice always wins
        if ((leftQ + rightQ) % 2 != 0) {
            return true;
        }

        int sumDiff = leftSum - rightSum;
        int qDiff = rightQ - leftQ;

        // Bob wins only if the difference can be perfectly balanced
        return sumDiff != 9 * qDiff / 2;
    }
}