Big O

## Common Data Structure Operations

| Data Structure                                               | Time Complexity |             |             |             |             |             |             |             | Space Complexity |
| :----------------------------------------------------------- | :-------------- | :---------- | :---------- | :---------- | :---------- | :---------- | :---------- | :---------- | :--------------- |
|                                                              | Average         |             |             |             | Worst       |             |             |             | Worst            |
|                                                              | Access          | Search      | Insertion   | Deletion    | Access      | Search      | Insertion   | Deletion    |                  |
| [Array](http://en.wikipedia.org/wiki/Array_data_structure)   | `Θ(1)`          | `Θ(n)`      | `Θ(n)`      | `Θ(n)`      | `O(1)`      | `O(n)`      | `O(n)`      | `O(n)`      | `O(n)`           |
| [Stack](http://en.wikipedia.org/wiki/Stack_(abstract_data_type)) | `Θ(n)`          | `Θ(n)`      | `Θ(1)`      | `Θ(1)`      | `O(n)`      | `O(n)`      | `O(1)`      | `O(1)`      | `O(n)`           |
| [Queue](http://en.wikipedia.org/wiki/Queue_(abstract_data_type)) | `Θ(n)`          | `Θ(n)`      | `Θ(1)`      | `Θ(1)`      | `O(n)`      | `O(n)`      | `O(1)`      | `O(1)`      | `O(n)`           |
| [Singly-Linked List](http://en.wikipedia.org/wiki/Singly_linked_list#Singly_linked_lists) | `Θ(n)`          | `Θ(n)`      | `Θ(1)`      | `Θ(1)`      | `O(n)`      | `O(n)`      | `O(1)`      | `O(1)`      | `O(n)`           |
| [Doubly-Linked List](http://en.wikipedia.org/wiki/Doubly_linked_list) | `Θ(n)`          | `Θ(n)`      | `Θ(1)`      | `Θ(1)`      | `O(n)`      | `O(n)`      | `O(1)`      | `O(1)`      | `O(n)`           |
| [Hash Table](http://en.wikipedia.org/wiki/Hash_table)        | `N/A`           | `Θ(1)`      | `Θ(1)`      | `Θ(1)`      | `N/A`       | `O(n)`      | `O(n)`      | `O(n)`      | `O(n)`           |
| [Binary Search Tree](http://en.wikipedia.org/wiki/Binary_search_tree) | `Θ(log(n))`     | `Θ(log(n))` | `Θ(log(n))` | `Θ(log(n))` | `O(n)`      | `O(n)`      | `O(n)`      | `O(n)`      | `O(n)`           |
| [Red-Black Tree](http://en.wikipedia.org/wiki/Red-black_tree) | `Θ(log(n))`     | `Θ(log(n))` | `Θ(log(n))` | `Θ(log(n))` | `O(log(n))` | `O(log(n))` | `O(log(n))` | `O(log(n))` | `O(n)`           |
| [AVL Tree](http://en.wikipedia.org/wiki/AVL_tree)            | `Θ(log(n))`     | `Θ(log(n))` | `Θ(log(n))` | `Θ(log(n))` | `O(log(n))` | `O(log(n))` | `O(log(n))` | `O(log(n))` | `O(n)`           |