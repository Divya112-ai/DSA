class Solution {
public:
    int ans = 0;

    // Returns {sum of subtree, number of nodes in subtree}
    pair<int, int> dfs(TreeNode* node) {
        if (node == nullptr)
            return {0, 0};

        auto left = dfs(node->left);
        auto right = dfs(node->right);

        int sum = node->val + left.first + right.first;
        int count = 1 + left.second + right.second;

        // Integer division automatically rounds down
        if (node->val == sum / count)
            ans++;

        return {sum, count};
    }

    int averageOfSubtree(TreeNode* root) {
        dfs(root);
        return ans;
    }
};
