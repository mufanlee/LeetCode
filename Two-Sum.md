# Two Sum



Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.

```c++
#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;

//class Solution {
//public:
//    vector<int> twoSum(vector<int>& nums, int target) {
//        vector<int> vec = nums;
//        sort(vec.begin(), vec.end());
//        int l = 0, h = vec.size() - 1;
//        while(l < h)
//        {
//            if((vec[l] + vec[h]) == target) break;
//            else if((vec[l] + vec[h]) > target) h--;
//            else l++;
//        }
//        vector<int> result;
//        int n = 2;
//        for(int i = 0; i < (int)nums.size(); i++)
//            if((vec[l] == nums[i]) || (vec[h] == nums[i]))
//            {
//                result.push_back(i);
//                if(--n == 0) break;
//            }
//        return result;
//    }
//};

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> result;
        map<int,int> m;
        for(int i = 0; i < (int)nums.size(); i++)
        {
            if(m.find(target - nums[i]) != m.end())
            {
                result.push_back(m[target - nums[i]]);
                result.push_back(i);
                break;
            }
            m.insert(pair<int,int>(nums[i],i));
        }
        return result;
    }
};
int main()
{
    vector<int> nums;
    nums.push_back(7);
    nums.push_back(2);
    nums.push_back(4);
    nums.push_back(15);
    int target = 9;
    Solution s;
    vector<int> r = s.twoSum(nums, target);
    for(int i = 0; i < (int)r.size(); i++)
        cout << r[i] << " ";
    return 0;
}
```
[Editorial Solution](https://leetcode.com/articles/two-sum/)
