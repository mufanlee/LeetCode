# Maximum Depth of Binary Tree

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/maximum-depth-of-binary-tree/?tab=Description)_
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Solution
```c++
#include <iostream>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    int maxDepth(TreeNode* root) {
        if(!root) return 0;
        return max(maxDepth(root->left),maxDepth(root->right)) + 1;
    }
};

int main()
{
    TreeNode *root = new TreeNode(1);
    root->left = new TreeNode(2);
    root->left->left = new TreeNode(4);
    root->right = new TreeNode(3);
    Solution s;
    cout << s.maxDepth(root) << endl;
    return 0;
}
```