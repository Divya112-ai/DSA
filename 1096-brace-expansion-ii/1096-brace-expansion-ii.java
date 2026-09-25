class Solution {
    public List<String> braceExpansionII(String expression) {
          TreeSet<String> set = dfs(expression);
        return new ArrayList<>(set);
    }

    private TreeSet<String> dfs(String exp) {
        TreeSet<String> res = new TreeSet<>();
        
        // Step 1: Split the expression by top-level commas (union operation)
        List<String> groups = splitByTopLevelCommas(exp);
        
        // If there are multiple comma-separated groups at the current top level, 
        // process each group independently and merge their results (union)
        if (groups.size() > 1) {
            for (String group : groups) {
                res.addAll(dfs(group));
            }
            return res;
        }

        // Step 2: Handle an individual group (Check for Concatenation)
        // If the expression is surrounded entirely by matching outer braces, strip them.
        // Example: "{a,b}" -> "a,b"
        if (exp.startsWith("{") && getClosingBraceIndex(exp, 0) == exp.length() - 1) {
            return dfs(exp.substring(1, exp.length() - 1));
        }

        // Find the boundary of the first independent block in this group
        int firstBlockEnd = 0;
        if (exp.startsWith("{")) {
            // If it starts with a brace, the first block ends at its matching closing brace
            firstBlockEnd = getClosingBraceIndex(exp, 0);
        } else {
            // If it starts with plain lowercase letters, the first block ends right before 
            // the next opening brace '{' or character boundary.
            while (firstBlockEnd < exp.length() && Character.isLowerCase(exp.charAt(firstBlockEnd))) {
                firstBlockEnd++;
            }
            firstBlockEnd--; // Adjust index to point to the end of the lowercase string
        }

        // Split the group into the first block and whatever remains
        String firstBlock = exp.substring(0, firstBlockEnd + 1);
        String remainingBlock = exp.substring(firstBlockEnd + 1);

        // Recursively evaluate the first block
        TreeSet<String> leftSet;
        if (firstBlock.startsWith("{")) {
            leftSet = dfs(firstBlock.substring(1, firstBlock.length() - 1));
        } else {
            leftSet = new TreeSet<>();
            leftSet.add(firstBlock); // Base case: it's just a raw string like "a"
        }

        // If nothing remains, we just return the left block's results
        if (remainingBlock.isEmpty()) {
            return leftSet;
        }

        // Otherwise, evaluate the remaining block and perform a Cartesian Product (concatenation)
        TreeSet<String> rightSet = dfs(remainingBlock);
        for (String l : leftSet) {
            for (String r : rightSet) {
                res.add(l + r);
            }
        }

        return res;
    }

    // Helper method to split strings ONLY by commas that reside at the current brace depth level (level 0)
    private List<String> splitByTopLevelCommas(String exp) {
        List<String> groups = new ArrayList<>();
        int level = 0;
        int start = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '{') level++;
            else if (c == '}') level--;
            else if (c == ',' && level == 0) {
                groups.add(exp.substring(start, i));
                start = i + 1;
            }
        }
        groups.add(exp.substring(start)); // Add the final remaining piece
        return groups;
    }

    // Helper method to find the matching closing brace index for an opening brace at `startIndex`
    private int getClosingBraceIndex(String exp, int startIndex) {
        int level = 0;
        for (int i = startIndex; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '{') level++;
            else if (c == '}') {
                level--;
                if (level == 0) return i;
            }
        }
        return -1;
    }
}