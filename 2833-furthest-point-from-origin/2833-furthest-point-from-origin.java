class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int countL = 0;
        int countR = 0;
        int countBlank = 0;

        // Single pass to count all character types
        for (int i = 0; i < moves.length(); i++) {
            char ch = moves.charAt(i);
            if (ch == 'L') {
                countL++;
            } else if (ch == 'R') {
                countR++;
            } else {
                countBlank++;
            }
        }

        // Net distance without wildcards + adding all wildcards to the dominant side
        return Math.abs(countL - countR) + countBlank;
    }
}
