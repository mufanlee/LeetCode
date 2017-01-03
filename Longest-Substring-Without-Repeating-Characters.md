# Longest Substring Without Repeating Characters

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/longest-substring-without-repeating-characters/)_

Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

## Approach

>用两个指针（位置），一个指向当前字符串头，一个指向当前正在扫描的字符，当前字符指针不断向后扫描，判断当前字符是否在前面的当前字符串中存在，若存在，则将头指针指向当前字符串中与当前字符相同的字符的下一个字符，否则继续扫描。

## Solution

```c++
#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s)
    {
        if(s.empty()) return 0;
        int font = 0, curr = 1;
        int maxlen = 1;
        while(curr < s.length())
        {
            for(int i = font; i < curr; i++)
            {
                if(s[curr] == s[i])
                {
                    maxlen = (curr - font) > maxlen ? (curr - font) : maxlen;
                    font = i + 1;
                    break;
                }
            }
            curr ++;
        }
        return (curr - font) > maxlen ? (curr - font) : maxlen;
    }
};
int main()
{
    Solution s;
    cout << s.lengthOfLongestSubstring("a") << endl;
    cout << s.lengthOfLongestSubstring("aa") << endl;
    cout << s.lengthOfLongestSubstring("aba") << endl;
    cout << s.lengthOfLongestSubstring("eceba") << endl;
    return 0;
}
```
[Editorial Solution](https://leetcode.com/articles/longest-substring-without-repeating-characters/)

```
#include <set>
int lengthOfLongestSubstring(string s)
{
    int n = s.length();
    set<char> dict;
    int ans = 0, i = 0, j = 0;
    while(i < n && j < n)
    {
        if(dict.find(s[j]) == dict.end())
        {
            dict.insert(s[j++]);
            ans = max(ans, j - i);
        }
        else
            dict.erase(s[i++]);
    }
    return ans;
}
```

```
#include <map>
int lengthOfLongestSubstring(string s)
{
    int n = s.length(), ans = 0;
    map<char, int> m;
    for(int j = 0, i = 0; j < n; j++)
    {
        if(m.find(s[j]) != m.end())
            i = max(m[s[j]], i);
        ans = max(ans, j - i + 1);
        m[s[j]] = j + 1;
    }
    return ans;
}
```