class Solution {
public:
    int DY[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int countDaysTogether(string aA, string lA, string aB, string lB) {
        int AA = days(aA);
        int LA = days(lA);
        int AB = days(aB);
        int LB = days(lB);
        
        // Find the overlap between the two intervals [AA, LA] and [AB, LB]
        return max(0, min(LA, LB) - max(AA, AB) + 1);
    }

    int days(string s) {
        int M = stoi(s.substr(0, 2));
        int D = stoi(s.substr(3, 2)); // Fixed length from 5 to 2
        
        int count = 0;
        // Sum up the days of all previous fully completed months
        for (int i = 0; i < M - 1; ++i) {
            count += DY[i];
        }
        
        // Add the days of the current month
        count += D; 
        return count;
    }
};
