# Maximum Subarray

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/maximum-subarray/?tab=Description)_
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

More practice:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

## Approach
> 1.动态规划
> 设置两个变量，一个是如果以当前元素为最长子数组的最后一个元素所能达到的最大值sum，另一个是已知的最大值ans。

> maxSubArray(A[n]) = max( maxSubArray(A[n-1]), A[n], maxSubArray(A[n-1)+A[n] ); 

> 对于第i个数，有两种选择：把它加入在子数组里，不加入子数组（子数组到此结束）。
加不加入子数组，要比较它加入前后子数组的总和是变大了还是变小了，如果变大则加入，变小则不加入。
所以，我们需要记录以i-1结尾的子数组的总和，最后的结果在这些总和中取最大的那个。

> 2.分治法
> 将数组分成两半nums[i,mid]和nums[mid+1,j]，整体的最大子数组或者出现在nums[i,mid]中，或者出现在nums[mid+1,j]中，或者是横跨两个子数组。

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

//动态规划
/*class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        if((int)nums.size() == 0) return 0;
        int ans = nums[0];
        int sum = nums[0];
        for(int i = 1; i < (int)nums.size(); i++)
        {
            sum = max(sum + nums[i], nums[i]);
            ans = max(sum, ans);
        }
        return ans;
    }
};*/

//分治法
class Solution {
public:
    int maxSub(vector<int>& nums, int l, int r)
    {
        if(l == r) return nums[l];
        int mid = (l + r) / 2;
        int x = maxSub(nums, l, mid);
        int y = maxSub(nums, mid+1, r);

        int ls = nums[mid], sum = nums[mid];
        for(int i = mid-1; i >= l; i--)
        {
            sum += nums[i];
            if(sum > ls)
                ls = sum;
        }
        int rs = nums[mid+1];
        sum = nums[mid+1];
        for(int i = mid+2; i <= r; i++)
        {
            sum += nums[i];
            if(sum > rs)
                rs = sum;
        }
        return max(ls+rs, max(x,y));
    }
    int maxSubArray(vector<int>& nums) {
        if((int)nums.size() == 0) return 0;
        return maxSub(nums, 0, (int)nums.size() - 1);
    }
};

int main()
{
    int n[] = {-2,1,-3,4,-1,2,1,-5,4};
    vector<int> nums(n, n+9);
    Solution s;
    cout << s.maxSubArray(nums) << endl;
    return 0;
}
```

## Error
1. [-1]，正确为-1，输出0，这是在动态规划中出现的问题，主要是起始设置ans和sum为0导致的。
2. [-2,-1]，正确应该为-1，输出0，这是在分治法中出现的问题，主要是在求最大子数组跨mid时，ls和rs设置为0导致的。
