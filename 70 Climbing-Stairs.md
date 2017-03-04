# Climbing Stairs

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/climbing-stairs/?tab=Description)_
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

## Approach
>dp[i]表示到达i步一共有多少方式

>dp[i] = dp[i-1] + dp[i-2]

## Solution
```c++
#include <iostream>

using namespace std;

class Solution {
public:
    int climbStairs(int n) {
        if(n == 1) return 1;
        int k = 1;
        int ans = 2;
        for(int i = 3; i <= n; i++)
        {
            ans = ans + k;
            k = ans - k;
        }
        return ans;
    }
};

int main()
{
    Solution s;
    cout << s.climbStairs(44) << endl;
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/climbing-stairs/)