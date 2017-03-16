# Linked List Cycle

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/linked-list-cycle/#/description)_
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

## Approach
>这道题比较容易想到的是遍历所有元素，判断当前元素是否指向前面的某个元素（包括自己）。如果使用想插入排序的方法，会超时。将前面遍历过得元素存到Set中，将当前元素与Set中的元素比较，这种方法时间复杂度为O(n)，空间复杂度也为O(n)。把这个问题想成两个跑步的人，一个跑的快一个跑的慢，跑的快的必然会将跑的慢的套圈。若题目中存在环，则两个指针必然会在某一时刻相等。

## Solution
```c++
#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

/*class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode *q = head;
        while(q)
        {
            ListNode *p = head;
            if(q->next == q) return true;
            while(p != q)
            {
                if(q->next == p)
                    return true;

                p = p->next;
            }
            q = q->next;
        }
        return false;
    }
};*/

class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode *slow = head;
        ListNode *fast = head;
        while(fast && fast->next)
        {
            slow = slow->next;
            fast = fast->next->next;
            if(fast == slow)
                return true;
        }
        return false;
    }
};

int main()
{
    ListNode *head = new ListNode(1);
    head->next = head;
    //head->next = new ListNode(2);
    //head->next->next = new ListNode(3);
    //head->next->next->next = head->next;
    Solution s;
    cout << s.hasCycle(head) << endl;
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/linked-list-cycle/)