# 3Sum Smaller

_**Difficulty: Medium**_

## _Problem_
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

```
[-2, 0, 1]
[-2, 0, 3]
```
Follow up: 
Could you solve it in O(n2) runtime?

## Approach
> 思路同3Sum，只是在找到一个小于target的三元组时，ans要加上j-i，因为i和j之间的元素必然小于nums[j]，因而加上nums[k]+nums[i]也小于target。

## Solution
```c++
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        int ans = 0;
        sort(nums.begin(), nums.end());
        for(int k = 0; k < (int)nums.size() - 2; k++)
        {
            int i = k + 1, j = (int)nums.size() - 1;
            while(i < j)
            {
                if((nums[k] + nums[i] + nums[j]) < target)
                {
                    //由于nums[i]只会判断一次，若小于则i++，而在i到j之间的数由于其小于nums[j]，
                    //所以加上nums[k]+nums[i]也小于target。
                    ans += j - i;
                    i++;
                }
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
    cout << s.threeSumSmaller(nums, 2) << endl;
    return 0;
}
```