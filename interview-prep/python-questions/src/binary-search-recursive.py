# Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
# Compare x with the middle element.
# If x matches with the middle element, we return the mid index.
# Else If x is greater than the mid element, then x can only lie in the right half subarray after the mid element. So we recur for the right half.
# Else (x is smaller) recur for the left half.

# recursive implementation
def binarySearchRecursive(array, indexLeft, indexRight, x):
    # base case
    if indexRight >= indexLeft:
        # check middle element
        indexMid = indexLeft + (indexRight - indexLeft) // 2 # floor division
        if array[indexMid] == x:
            return indexMid
        # if element is smaller than mid then it must be present in left subarray
        elif array[indexMid] > x:
            return binarySearchRecursive(array, indexLeft, indexMid - 1, x)
        # else element will be present in right subarray
        else :
            return binarySearchRecursive(array, indexMid + 1, indexRight, x)
    # once indexRight goes through all subarrays, no more searches left
    else :
        return -1


if __name__ == "__main__":
    arr = [2, 3, 4, 10, 40]
    x = 3
    foundIndex = binarySearchRecursive(arr, 0, len(arr), x)
    print("if element exists in array, it will be in index: ", foundIndex)
