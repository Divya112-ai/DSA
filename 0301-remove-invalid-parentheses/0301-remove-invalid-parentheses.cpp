class Solution {
    void dfs(string& s, int index, int currentRemoved,
             string& n, unordered_set<string>& ans,
             int& len, int open) {

        if (currentRemoved > len)
            return;

        if (open < 0)
            return;

        if (index == s.length()) {
            if (open == 0) {
                if (currentRemoved < len) {
                    ans.clear();
                    len = currentRemoved;
                }

                if (currentRemoved == len)
                    ans.insert(n);
            }
            return;
        }

        // Keep current character
        n += s[index];

        dfs(s, index + 1, currentRemoved, n, ans, len,
            open + (s[index] == '(' ? 1 :
                    (s[index] == ')' ? -1 : 0)));

        n.pop_back();

        // Remove current character
        dfs(s, index + 1, currentRemoved + 1, n, ans, len, open);
    }

public:
    vector<string> removeInvalidParentheses(string s) {

        unordered_set<string> group;

        int len = 0;
        int open = 0;

        // Find minimum number of parentheses to remove
        for (int i = 0; i < s.length(); ++i) {

            if (s[i] == '(') {
                open++;
            }
            else if (s[i] == ')') {
                open--;

                if (open < 0) {
                    len++;
                    open = 0;
                }
            }
        }

        len += open;

        string str;
        dfs(s, 0, 0, str, group, len, 0);

        vector<string> ans;
        ans.reserve(group.size());

        for (auto& str : group)
            ans.push_back(str);

        return ans;
    }
};