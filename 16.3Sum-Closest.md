# 3Sum Closest

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/3sum-closest/?tab=Description)_
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
```
    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

## Approach
> 首先对给定的数组进行排序，然后从0到数组元素长度减2开始循环，对于每层循环，定义两个指针一个i指向k+1，
一个j指向数组尾部，判断k、i、j指向的元素之和是够更接近给定的元素，若更接近则更新，再根据3个元素之和与
目标元素的大小修改i和j，若小于目标元素则i++，否则j--。

## Solution
```c++
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int ans = 32767;
        sort(nums.begin(), nums.end());
        for(int k = 0; k < (int)nums.size() - 2; k++)
        {
            int i = k + 1, j = (int)nums.size() - 1;
            while(i < j)
            {
                int sum = nums[k] + nums[i] + nums[j];
                if(sum == target)
                    return target;
                if(abs(target - sum) < abs(target - ans))
                    ans = sum;

                if(sum < target)
                    i++;
                else
                    j--;
            }
        }
        return ans;
    }
};

int main()
{
    Solution s;
    vector<int> nums;
    nums.push_back(-2);
    nums.push_back(0);
    nums.push_back(1);
    nums.push_back(3);
    cout << s.threeSumClosest(nums, 1) << endl;
    return 0;
}
```