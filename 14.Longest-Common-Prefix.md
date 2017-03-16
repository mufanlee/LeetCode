# Longest Common Prefix

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/longest-common-prefix/)_
Write a function to find the longest common prefix string amongst an array of strings.

## Solution
```c++
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
//Horizontal scanning
/*class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int n = strs.size();
        if(n == 0) return "";
        int l = strs[0].length();
        for(int i = 1; i < n; i++)
        {
            int j = 0;
            for(;j < min(l,(int)strs[i].length()); j++)
            {
                if(strs[i][j] != strs[0][j])
                {
                    break;
                }
            }
            l = j;
        }
        return (l == 0 ? "" : strs[0].substr(0,l));
    }
};*/
//Vertical scanning
/*class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.size() == 0) return "";
        for(int i = 0; i < strs[0].length(); i++)
        {
            for(int j = 1; j < strs.size(); j++)
            {
                if(i == strs[j].length() || strs[j][i] != strs[0][i])
                    return strs[0].substr(0,i);
            }
        }
        return strs[0];
    }
};*/
//Divide and conquer
/*class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.size() == 0) return "";
        return longestCommonPrefix(strs,0,strs.size()-1);
    }

    string longestCommonPrefix(vector<string>& strs, int l, int r)
    {
        if(l == r)
            return strs[l];
        int mid = (l+r)/2;
        string lcpleft = longestCommonPrefix(strs,l,mid);
        string lcpright = longestCommonPrefix(strs,mid+1,r);
        return commonPrefix(lcpleft,lcpright);
    }

    string commonPrefix(string left, string right)
    {
        int n = min(left.length(),right.length());
        for(int i = 0; i < n; i++)
        {
            if(left[i] != right[i])
                return left.substr(0,i);
        }
        return left.substr(0,n);
    }
};*/
//Binary search
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.size() == 0) return "";
        int minLen = strs[0].length();
        for(int i = 1; i < strs.size(); i++)
            minLen = min(minLen, (int)strs[i].length());
        int low = 1;
        int high = minLen;
        while(low <= high)
        {
            int mid = (low + high) / 2;
            if(isCommonPrefix(strs, mid))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return strs[0].substr(0,(low + high) / 2);
    }

    bool isCommonPrefix(vector<string>& strs, int len)
    {
        string str = strs[0].substr(0,len);
        for(int i = 1; i < strs.size(); i++)
            if(strs[i].substr(0,len).compare(str) != 0)
                return false;
        return true;
    }
};

int main()
{
    vector<string> vecs;
    vecs.push_back("abcdefg");
    vecs.push_back("abc");
    //vecs.push_back("");
    Solution s;
    cout << s.longestCommonPrefix(vecs) <<endl;
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/longest-common-prefix/)