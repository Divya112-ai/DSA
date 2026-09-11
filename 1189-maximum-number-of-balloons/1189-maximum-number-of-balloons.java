class Solution {
    public int maxNumberOfBalloons(String text) {

        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;

        for (char ch : text.toCharArray()) {

            if (ch == 'b') b++;
            else if (ch == 'a') a++;
            else if (ch == 'l') l++;
            else if (ch == 'o') o++;
            else if (ch == 'n') n++;
        }

        l = l / 2;
        o = o / 2;

        return Math.min(
            Math.min(Math.min(b, a), Math.min(l, o)),
            n
        );
    }
}