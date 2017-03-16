# Merge Sorted Array

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/merge-sorted-array/?tab=Description)_
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

## Approach
>这道题目不难，但有些小的细节没考虑到，很容易出错。

>1.nums1和nums2中的元素可能比m和n多，应该在nums1中插入前将多余的元素清除，在代码刚开始清除比较好。

>2.insert(nums1.begin()+i, nums2[j++])函数是在i之前插入元素。

>3.插入元素后，i还应该++，因为i的位置被新元素占据。

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        nums1.erase(nums1.begin()+m, nums1.end());
        int i = 0, j = 0;
        while(i < (int)nums1.size() && j < n)
        {
            if(nums2[j] < nums1[i])
                nums1.insert(nums1.begin()+i, nums2[j++]);
            i++;
        }
        while(j < n)
            nums1.push_back(nums2[j++]);
    }
};

int main()
{
    int a[] = {4,0,0,0,0,0};
    int b[] = {2,3,6};
    vector<int> nums1(a, a+6);
    vector<int> nums2(b, b+3);
    Solution s;
    s.merge(nums1, 1, nums2, 3);
    for(int i = 0; i < nums1.size(); i++)
        cout << nums1[i] << " ";
    return 0;
}
```

## Error
1.[0] 0 [1] 1，输出[0,1]，正确[1]，没有注意nums1中可能超过m个元素，直接在其后push_back()，会把那些元素加进去。

2.[1,0] 1 [2] 1，输出[1,0,2]，正确[1,2]，同1。

3.[1] 1 [] 0，输出[]，正确[1]，错误调用erase()，将其写在最后若nums2为空，则i一直是0，那么会将nums1中的元素都清除。

4.[1,2,3,0,0,0] 3 [2,5,6] 3，输出[1,2,2,5,6,3]，正确[1,2,2,3,5,6]，当进入最后的while(j<n)，不应该在用insert的插入了，直接push_back()就行了。因为在最前面已经将nums1中的多余m的元素清除了。

5.[0] 0 [1] 1，Runtime Error，nums1.insert(nums1.begin()+(++i), nums2[j++]);，在nums1中没有元素的时候出错。

6.[4,0,0,0,0,0] 1 [1,2,3,5,6] 5，输出[1,4,2,3,5,6]，正确[1,2,3,4,5,6]，没有考虑到在nums1中插入元素后i的位置为新元素，i应该++。而且在第一个while()中i应该与nums1中元素个数比较，不应该再和n，
因为nums1中的元素在不断增加。