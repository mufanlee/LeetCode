# Search Insert Position

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/search-insert-position/?tab=Description)_

## Approach
>二分查找

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

/*class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        if(nums.empty()) return 0;
        for(int i = 0; i < nums.size(); i++)
            if(target <= nums[i]) return i;
        return nums.size();
    }
};*/

class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while(l <= r)
        {
            int mid = l + (r - l) / 2;
            if(target < nums[mid])
                r = mid - 1;
            else if(target > nums[mid])
                l = mid + 1;
            else
                return mid;
        }
        return l;
    }
};

int main()
{
    vector<int> nums;
    nums.push_back(1);
    nums.push_back(3);
    nums.push_back(5);
    nums.push_back(6);
    Solution s;
    cout << s.searchInsert(nums, 1) <<endl;
    return 0;
}
```

## Error
1. [1] 1，找到直接返回mid。