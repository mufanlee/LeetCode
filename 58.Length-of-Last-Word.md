# Length of Last Word

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/length-of-last-word/?tab=Description)_
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 

Given s = "Hello World",

return 5.

## Solution
```c++
#include <iostream>

using namespace std;

class Solution {
public:
    int lengthOfLastWord(string s) {
        int t = (int)s.length() - 1;
        while(t >= 0 && s[t] == ' ') t--;
        int k = t;
        while(t >= 0 && s[t] != ' ') t--;
        return k - t;
    }
};

int main()
{
    string s = "Hello World";
    //string s = "Hello World ";
    Solution sol;
    cout << sol.lengthOfLastWord(s) << endl;
    return 0;
}
```