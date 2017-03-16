# Container With Most Water

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/container-with-most-water/?tab=Description)_
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

>翻译：

>给定n个非负整数a1,a2,...,an，其中每个代表一个点坐标（i,ai）。

>n个垂直线段例如线段的两个端点在（i,ai）和（i,0）。

>找到两个线段，与x轴形成一个容器，使其包含最多的水。

>备注：你不必倾倒容器。

## Approach
> 贪心算法。设置两个指针一个在头一个在尾，计算当前容器的面积与最大值比较，如果大于则替换；然后比较两个指针
指向的值，移动值小的指针。

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int maxArea(vector<int>& height) {
        int i = 0, j = height.size()-1;
        int maxv = 0;
        while(j > i)
        {
            maxv = max(maxv, min(height[i],height[j]) * (j - i));
            if(height[i] > height[j])
                j--;
            else
                i++;
        }
        return maxv;
    }
};

int main()
{
    vector<int> h;
    h.push_back(4);
    h.push_back(6);
    h.push_back(2);
    h.push_back(6);
    h.push_back(7);
    h.push_back(11);
    h.push_back(2);
    Solution s;
    cout << s.maxArea(h) << endl;
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/container-most-water/)