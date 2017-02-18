# Count and Say

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/count-and-say/?tab=Description)_

## Approach
>模拟

## Solution
```c++
#include <iostream>

using namespace std;

class Solution {
public:
    string countAndSay(int n) {
        string str = "1";
        for(int i = 0; i < n-1; i++)
        {
            char c = str[0];
            int cnt = 0;
            string s = "";
            for(int j = 0; j < str.length(); j++)
            {
                if(str[j] == c)
                    cnt++;
                else
                {
                    s += '0' + cnt;
                    s += c;
                    c = str[j];
                    cnt = 1;
                }
            }
            s += '0' + cnt;
            s += c;
            str = s;
        }
        return str;
    }
};

int main()
{
    Solution s;
    for(int i = 1; i <= 10; i++)
        cout << s.countAndSay(i) << " " << endl;
    return 0;
}
```