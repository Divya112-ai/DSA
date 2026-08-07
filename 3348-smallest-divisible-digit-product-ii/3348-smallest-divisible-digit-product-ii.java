class Solution {

    // GCD using Euclidean algorithm
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String smallestNumber(String num, long t) {

        // ------------------------------------------------
        // STEP 1:
        // Check whether t can be represented using
        // digits 1 to 9.
        // ------------------------------------------------

        long remainingfactor = t;

        for (int factor = 2; factor <= 9; factor++) {

            while (remainingfactor % factor == 0) {
                remainingfactor /= factor;
            }
        }

        // Prime factor other than 2,3,5,7
        if (remainingfactor > 1) {
            return "-1";
        }

        int len = num.length();

        char[] digits = num.toCharArray();

        // ------------------------------------------------
        // STEP 2:
        // requiredfactor[i] = factor still required
        // after first i digits.
        // ------------------------------------------------

        long[] requiredfactor = new long[len + 1];

        requiredfactor[0] = t;

        int firstZeroIndex = len;

        for (int i = 0; i < len; i++) {

            int digit = digits[i] - '0';

            // We cannot use 0 as a factor.
            if (digit == 0) {
                firstZeroIndex = i;
                break;
            }

            requiredfactor[i + 1] =
                requiredfactor[i]
                / gcd(requiredfactor[i], digit);
        }

        // ------------------------------------------------
        // STEP 3:
        // If num itself works.
        // ------------------------------------------------

        if (firstZeroIndex == len &&
            requiredfactor[len] == 1) {

            return num;
        }

        // ------------------------------------------------
        // STEP 4:
        // Try increasing a digit from RIGHT to LEFT.
        // ------------------------------------------------

        int start = Math.min(firstZeroIndex, len - 1);

        for (int i = start; i >= 0; i--) {

            int originalDigit = digits[i] - '0';

            // Try every larger digit.
            for (int newDigit = originalDigit + 1;
                 newDigit <= 9;
                 newDigit++) {

                // Factors required before position i.
                long remaining = requiredfactor[i];

                // New digit satisfies some factors.
                remaining =
                    remaining /
                    gcd(remaining, newDigit);

                int suffixLength = len - i - 1;

                // Build smallest possible suffix.
                String suffix =
                    buildSuffix(suffixLength, remaining);

                if (suffix != null) {

                    StringBuilder answer =
                        new StringBuilder();

                    // Original prefix
                    answer.append(num.substring(0, i));

                    // Increased digit
                    answer.append(
                        (char) ('0' + newDigit)
                    );

                    // Smallest suffix
                    answer.append(suffix);

                    return answer.toString();
                }
            }
        }

        // ------------------------------------------------
        // STEP 5:
        // No answer with same length.
        //
        // Find the minimum number of digits needed
        // to represent t.
        // ------------------------------------------------

        int requiredDigits = minimumDigits(t);

        /*
         * Answer must have MORE digits than num.
         *
         * But if t itself needs more digits,
         * use that number of digits.
         */
        int newLength =
            Math.max(len + 1, requiredDigits);

        String answer =
            buildSuffix(newLength, t);

        if (answer != null) {
            return answer;
        }

        return "-1";
    }


    // ====================================================
    // Find minimum number of digits needed to represent
    // t using digits 1...9.
    // ====================================================

    private int minimumDigits(long value) {

        int count2 = 0;
        int count3 = 0;
        int count5 = 0;
        int count7 = 0;

        while (value % 2 == 0) {
            count2++;
            value /= 2;
        }

        while (value % 3 == 0) {
            count3++;
            value /= 3;
        }

        while (value % 5 == 0) {
            count5++;
            value /= 5;
        }

        while (value % 7 == 0) {
            count7++;
            value /= 7;
        }

        if (value > 1) {
            return Integer.MAX_VALUE;
        }

        int count = 0;

        // 5 and 7 cannot combine with other factors.
        count += count5;
        count += count7;

        // ------------------------------------------------
        // Combine 2s and 3s optimally.
        // ------------------------------------------------

        // 2^3 = 8
        count += count2 / 3;
        count2 %= 3;

        // 3^2 = 9
        count += count3 / 2;
        count3 %= 2;

        // 2 * 3 = 6
        if (count2 > 0 && count3 > 0) {
            count++;
            count2--;
            count3--;
        }

        // 2^2 = 4
        if (count2 >= 2) {
            count++;
            count2 -= 2;
        }

        // Remaining 2
        count += count2;

        // Remaining 3
        count += count3;

        return count;
    }


    // ====================================================
    // Build smallest number of exactly 'length' digits
    // whose digit product is divisible by 'remaining'.
    // ====================================================

    private String buildSuffix(int length, long remaining) {

        // No factors required.
        if (remaining == 1) {

            StringBuilder result =
                new StringBuilder();

            for (int i = 0; i < length; i++) {
                result.append('1');
            }

            return result.toString();
        }

        // ------------------------------------------------
        // Factor remaining into 2,3,5,7.
        // ------------------------------------------------

        int count2 = 0;
        int count3 = 0;
        int count5 = 0;
        int count7 = 0;

        while (remaining % 2 == 0) {
            count2++;
            remaining /= 2;
        }

        while (remaining % 3 == 0) {
            count3++;
            remaining /= 3;
        }

        while (remaining % 5 == 0) {
            count5++;
            remaining /= 5;
        }

        while (remaining % 7 == 0) {
            count7++;
            remaining /= 7;
        }

        if (remaining > 1) {
            return null;
        }

        StringBuilder factorDigits =
            new StringBuilder();

        // ------------------------------------------------
        // Build digits.
        // ------------------------------------------------

        // 5
        while (count5 > 0) {
            factorDigits.append('5');
            count5--;
        }

        // 7
        while (count7 > 0) {
            factorDigits.append('7');
            count7--;
        }

        // 2^3 = 8
        while (count2 >= 3) {
            factorDigits.append('8');
            count2 -= 3;
        }

        // 3^2 = 9
        while (count3 >= 2) {
            factorDigits.append('9');
            count3 -= 2;
        }

        // 2 * 3 = 6
        while (count2 >= 1 && count3 >= 1) {
            factorDigits.append('6');
            count2--;
            count3--;
        }

        // 2^2 = 4
        while (count2 >= 2) {
            factorDigits.append('4');
            count2 -= 2;
        }

        // Remaining 2
        while (count2 > 0) {
            factorDigits.append('2');
            count2--;
        }

        // Remaining 3
        while (count3 > 0) {
            factorDigits.append('3');
            count3--;
        }

        // Not enough positions.
        if (factorDigits.length() > length) {
            return null;
        }

        // ------------------------------------------------
        // Sort factors to make the number smallest.
        // ------------------------------------------------

        char[] arr =
            factorDigits.toString().toCharArray();

        java.util.Arrays.sort(arr);

        StringBuilder result =
            new StringBuilder();

        // Extra positions are 1.
        for (int i = factorDigits.length();
             i < length;
             i++) {

            result.append('1');
        }

        result.append(arr);

        return result.toString();
    }
}