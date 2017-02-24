# Valid Parentheses

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/valid-parentheses/)_
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

## Approach
> 堆栈

## Solution
```c++
#include <iostream>
#include <stack>
using namespace std;

class Solution {
public:
    bool isValid(string s) {
        stack<char> st;
        for(int i = 0; i < s.length(); i++)
        {
            switch(s[i])
            {
            case '(':
            case '{':
            case '[':
                st.push(s[i]);
                break;
            case ')':
                if(st.empty() || st.top() != '(') return false;
                else st.pop();
                break;
            case '}':
                if(st.empty() || st.top() != '{') return false;
                else st.pop();
                break;
            case ']':
                if(st.empty() || st.top() != '[') return false;
                else st.pop();
                break;
            default: ;
            }
        }
        return st.empty();
    }
};

int main()
{
    Solution s;
    cout << s.isValid("()") << endl;
    cout << s.isValid("({[]})") << endl;
    cout << s.isValid("]") << endl;
    return 0;
}
```

## Error
1."]"，没有判断堆栈最后是否为空