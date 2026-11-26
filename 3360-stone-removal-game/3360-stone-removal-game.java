class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }

        // The player who cannot make a move loses, so the other player wins.
        return !isAliceTurn;
    }
}