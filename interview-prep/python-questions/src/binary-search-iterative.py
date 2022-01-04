# Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
# Compare x with the middle element.
# If x matches with the middle element, we return the mid index.
# Else If x is greater than the mid element, then x can only lie in the right half subarray after the mid element. So we recur for the right half.
# Else (x is smaller) recur for the left half.

def binarySearchIterative(array, leftIndex, rightIndex, x):
    while rightIndex >= leftIndex:
        indexMid = leftIndex + (rightIndex - leftIndex) // 2 # floor division
        print(indexMid)
        if array[indexMid] == x:
            return indexMid
        elif array[indexMid] >= x:
            rightIndex = indexMid - 1
        else :
            leftIndex = indexMid + 1
    return -1


if __name__ == "__main__":
    arr = [2, 3, 4, 10, 40, 300]
    x = 101

    # Function call
    result = binarySearchIterative(arr, 0, len(arr) - 1, x)

    if result != -1:
        print("Element is present at index % d" % result)
    else:
        print("Element is not present in array")