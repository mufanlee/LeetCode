# Linked List Cycle II

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/linked-list-cycle-ii/#/description)_

## Approach
>1.顺序遍历链表，并用一个Set存储遍历过的元素，将当前元素与Set中的元素比较，如果相等，则找到环，并且这个元素为环的起始点。时间复杂度为O(n)，但空间复杂度也为O(n)。

>2.使用两个指针遍历链表，一个走的快，一个走的慢，如果链表中存在环，则走的快的一定会在环中与走的慢的相遇。设相遇的位置到环的起始位置的距离为L2，链表的头到环起始位置的距离为L1，环中的长度为C，快的指针在环中走了n圈。
    - 慢的指针相遇前走了L1+L2
    - 快的指针相遇前走了L1+L2+n*C
因为快的指针的速度是慢的指针的两倍，则距离也是慢的指针的两倍，因此

> 2 * (L1 + L2) = L1 + L2 + n * C    ==>  L1 + L2 = n * C  ==> L1 = (n - 1) * C + (C - L2)

> L1 = C - L2，即头指针到相遇位置的距离等于环中相遇位置到环起始位置的距离。

## Solution
```c++
#include <iostream>
#include <set>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

/*class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        set<ListNode *> nodes;
        while(head)
        {
            if(nodes.find(head) != nodes.end())
                return *nodes.find(head);
            else
                nodes.insert(head);
            head = head->next;
        }
        return NULL;
    }
};*/

class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        ListNode *slow = head;
        ListNode *fast = head;
        while(fast && fast->next)
        {
            slow = slow->next;
            fast = fast->next->next;
            if(slow == fast)
            {
                slow = head;
                while(slow != fast)
                {
                    slow = slow->next;
                    fast = fast->next;
                }
                return fast;
            }
        }
        return NULL;
    }
};

int main()
{
    ListNode *head = new ListNode(1);
    //head->next = head;
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    //head->next->next->next = head->next;
    Solution s;
    ListNode * ret = s.detectCycle(head);
    if(ret)
        cout << ret->val << endl;
    else
        cout << "NULL" << endl;
    return 0;
}
```