# Add Binary

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/add-binary/?tab=Description)_
Given two binary strings, return their sum (also a binary string).

For example,

a = "11"

b = "1"

Return "100".

## Solution
```c++
#include <iostream>

using namespace std;

class Solution {
public:
    string addBinary(string a, string b) {
        if(a.length() < b.length())
            swap(a,b);
        int c = 0;
        int m = (int)a.length() - 1, n = (int)b.length() - 1;
        while(n >= 0)
        {
            int k = (a[m] - '0') + (b[n--] - '0') + c;
            c = k / 2;
            a[m--] = k % 2 + '0';
        }
        while(m >= 0)
        {
            int k = a[m] - '0' + c;
            c = k / 2;
            a[m--] = k % 2 + '0';
        }
        if(c) a.insert(a.begin(), c + '0');
        return a;
    }
};

int main()
{
    Solution s;
    cout << s.addBinary("11", "1") << endl;
    cout << s.addBinary("101", "1") << endl;
    cout << s.addBinary("1", "111") << endl;
    return 0;
}
```