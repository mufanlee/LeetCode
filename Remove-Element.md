# Remove Element

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/remove-element/?tab=Description)_
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

/*class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int n = 0;
        for(int i = 0; i < nums.size(); i++)
        {
            if(nums[i] != val)
            {
                nums[n++] = nums[i];
            }
        }
        return n;
    }
};*/

class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int n = nums.size();
        for(int i = 0; i < n;)
        {
            if(nums[i] == val)
            {
                nums[i] = nums[n-1];
                n--;
            }
            else
                i++;
        }
        return n;
    }
};

int main()
{
    vector<int> nums;
    nums.push_back(3);
    nums.push_back(2);
    nums.push_back(2);
    nums.push_back(3);
    Solution s;
    cout << s.removeElement(nums, 3) << endl;
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/remove-element/)