# Single Number

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/single-number/?tab=Description)_
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

## Solution
```c++
#include <iostream>
#include <vector>
#include <map>
using namespace std;

/*class Solution {
public:
    int singleNumber(vector<int>& nums) {
        if((int)nums.size() == 0) return 0;
        map<int, int> m;
        for(int i = 0; i < (int)nums.size(); i++)
            if(m.find(nums[i]) == m.end())
                m[nums[i]] = 1;
            else
                m[nums[i]]++;
        for(map<int, int>::iterator it = m.begin(); it != m.end(); it++)
            if(it->second == 1) return it->first;
    }
};*/

class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ret = 0;
        for(int i = 0; i < nums.size(); i++)
            ret ^= nums[i];
        return ret;
    }
};
int main()
{
    int n[] = {1};
    vector<int> nums(n,n+1);
    Solution s;
    cout << s.singleNumber(nums) << endl;
    return 0;
}
```