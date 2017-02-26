# Plus One

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/plus-one/?tab=Description)_
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

## Solution
```c++
#include <iostream>
#include <vector>
using namespace std;

/*class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int c = 1;
        for(int i = (int)digits.size() - 1; i >= 0; i--)
        {
            int k = digits[i] + c;
            digits[i] = k % 10;
            c = k / 10;
        }
        if(c) digits.insert(digits.begin(), c);
        return digits;
    }
};*/

class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        for(int i = (int)digits.size() - 1; i >= 0; i--)
        {
            if(digits[i] == 9)
                digits[i] = 0;
            else
            {
                digits[i]++;
                return digits;
            }
        }
        digits[0] = 1;
        digits.push_back(0);
        return digits;
    }
};

int main()
{
    int nums[] = {1, 2, 9};
    vector<int> digits(nums, nums+3);
    Solution s;
    digits = s.plusOne(digits);
    for(int i = 0; i < (int)digits.size(); i++)
        cout << digits[i];
    cout << endl;
    return 0;
}
```