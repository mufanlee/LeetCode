# Median of Two Sorted Arrays

_**Difficulty: Hard**_

## _[Problem](https://leetcode.com/problems/median-of-two-sorted-arrays/)_

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

## Approach

>思路1：归并计数法
>从两个数组中按照从小到大取数据放到一个vector中，一共取(m+n)/2个数，在根据两个数组总共数据是奇数还是偶数计算中位数。
时间复杂度：O(n)
>思路2：分治法（二分搜索）
>假设两个有序序列共有n个元素，当n为奇数时，搜寻第(n/2+1)个元素，当n为偶数时，搜寻第(n/2)和(n/2+1)个元素，然后取它们的均值。我们可以把这题抽象为“搜索两个有序序列的第k个元素”。
>如何搜索两个有序序列中的第k个元素？假设序列都是从小到大排列，对于第一个序列中的前p个元素和第二个序列中的前q个元素，我们想要的结果是：p+q=k-1，且两个序列的第p个元素和第q个元素都小于总序列的第k个元素。因为总序列中，第k-1个元素小于等于第k个元素，则第p+1个元素或者第q+1个元素就是我们要找的第k个元素。
>我们可以通过二分法将问题规模缩小，假设p=k/2-1，则q=k-p-1。若第一个序列的第p个元素小于第二个序列的第q个元素，我们将第一个序列前p个元素全部抛弃，形成一个较短的新序列。然后用新序列代替原来的第一个序列，在找其中的第k-p个元素，依次递归。递归终止的条件有：
	- 较短序列所有元素都被抛弃，则返回较长序列的第k个元素。
	- 一序列第p个元素等于二序列第q个元素，此时总序列第p+q=k-1个元素的后一个元素，就是总序列的第k个元素。

## Solution

```c++
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /*double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2)
    {
        vector<int> vec;
        int p = 0, q = 0;
        int k = (nums1.size() + nums2.size()) / 2 + 1;
        int cnt = 0;
        while(p < nums1.size() || q < nums2.size())
        {
            if(cnt >= k) break;
            if(p == nums1.size())
            {
                vec.push_back(nums2[q++]);
                cnt ++;
                continue;
            }
            if(q == nums2.size())
            {
                vec.push_back(nums1[p++]);
                cnt ++;
                continue;
            }
            if(nums1[p] > nums2[q])
                vec.push_back(nums2[q++]);
            else
                vec.push_back(nums1[p++]);
            cnt ++;
        }
        if((nums1.size() + nums2.size()) % 2 != 0)
            return vec[vec.size() - 1];
        else
            return (vec[vec.size() - 1] + vec[vec.size() - 2])  / 2.0;
    }*/

    double findKth(int* nums1, int m,int* nums2, int n, int k)
    {
        if(m > n)
            return findKth(nums2, n, nums1, m, k);
        if(m == 0)
            return nums2[k - 1];
        if(k == 1)
            return min(nums1[0], nums2[0]);
        int pa = min(k / 2, m), pb = k - pa;
        if(nums1[pa - 1] < nums2[pb - 1])
            return findKth(nums1 + pa, m - pa, nums2, n, k - pa);
        else if(nums1[pa - 1] > nums2[pb - 1])
            return findKth(nums1, m, nums2 + pb, n - pb, k - pb);
        else
            return nums1[pa - 1];
    }
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2)
    {
        int n = nums1.size() + nums2.size();
        if(n & 0x1)
            return findKth(nums1.data(), nums1.size(), nums2.data(), nums2.size(), n / 2 + 1);
        else
            return (findKth(nums1.data(), nums1.size(), nums2.data(), nums2.size(), n / 2) + findKth(nums1.data(), nums1.size(), nums2.data(), nums2.size(), n / 2 + 1)) / 2;
    }
};

int main()
{
    vector<int> a;
    vector<int> b;
    a.push_back(1);
    a.push_back(2);
    b.push_back(3);
    b.push_back(4);
    Solution s;
    cout << s.findMedianSortedArrays(a,b) << endl;
    return 0;
}
```