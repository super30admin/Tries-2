'''
347 Top K Frequent Elements
https://leetcode.com/problems/top-k-frequent-elements/

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Example 3:
Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]

Constraints:
1 <= nums.length <= 105
-10^4 <= nums[i] <= 10^4
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Solution:
1. Hash Map and Heap
First, count how often each number appears using a hash map. Then, for each unique number in the hash map, add the (freq, number) pair to a min-heap of size k. After we have gone through all the keys (unique no.) in the hash map,  the elements left over in the heap constitute the k-most freq elements. Finally, extract the heap contents into a result array.

https://youtu.be/eglgM0NoRX0?t=2155

Time: O(N + U log k), Space: O(U), where U = no. of unique nos. in nums, worst case U=N
If U = N, Time: O(N log k), Space: O(N)


2. Bucketing
First, count how often each number appears using a hash map. Note down the max frequency from the hash map. The create an array 'bucket' of length max_freq + 1 where the indices of the bucket range from 0,...,max_freq and the values are empty lists. Hence, the bucket array is a list of lists.

Then, we fill the bucket at index = freq and value = unique number. Thus, for each key (unique number) in the hash map, we get the freq count (f) and set bucket[f] = key. Now, we read the bucket array in the reverse order (max_freq, max_freq-1,...,0 since we need the top k frequencies) and collect the values at each index and save them in the result until we have 'k' numbers in the result.

https://youtu.be/eglgM0NoRX0?t=3120

Time: O(max(N, F), Space: O(max(U<=N, F)), F = no. of frequencies

Note:
U > F if nums contain all unique nos. eg. nums = [1,2,3,4,5,6] (U = 6, F = 1)

F > U if nums contain too many repeated numbers. eg. nums = [1,1,1,1,1,2] (U=2, F = 5)

worst case F = N
If F = N, Time: O(N), Space: O(N)
'''
from typing import List
import heapq
from collections import defaultdict

def topKFrequent_heap(nums: List[int], k: int) -> List[int]:
    ''' Time: O(N + U log k), Space: O(U), where U = no. of unique nos. in      nums.
    Worst case occurs when U=N. Then,
    Time: O(N log k), Space: O(N) '''
    h = defaultdict(int)
    for num in nums: # O(N)
        h[num] += 1 # <k,v> = <number, freq>
    U = len(h) # count of unique nos. in the list nums,
    # worst of U is N if every number in nums is unique
    heap = []
    for i, num in enumerate(h): # O(U), worst case
        # construct a tuple = (freq, number) and add it to the heap
        # FYI, by default, heapq sorts the tuples using the 1st ele of tuple
        item = (h[num], num)
        if i < k:
            heapq.heappush(heap, item) # O(log k)
        else:
            heapq.heappushpop(heap, item) # O(log k)

    heap = [item[1] for item in heap[::-1]]
    return heap


def topKFrequent_bucket(nums: List[int], k: int) -> List[int]:
    ''' Time: O(max(U,N)) = O(N), Space: O(max(U, F)), F = no. of frequencies

        Note:
        U > F if nums contain all unique nos. eg. nums = [1,2,3,4,5,6] (U = 6, F = 1)

        F > U if nums contain too many repeated numbers. eg. nums = [1,1,1,1,1,2] (U=2, F = 5)

        worst case F = N
        If F = N, Time: O(N), Space: O(N)
    '''
    h = defaultdict(int)
    for num in nums: # O(N)
        h[num] += 1 # <k,v> = <number, freq>

    max_freq = 0
    for num in h: # O(U), worst case U = N
        max_freq = max(max_freq, h[num])

    bucket =  [[] for _ in range(max_freq+1)] # O(F)

    for num in h: # O(U), worst case U = N
        freq = h[num]
        bucket[freq].append(num)

    result = []
    for freq in range(max_freq,-1,-1): # O(F), worst case F = N (since freq cannot be > N)
        if bucket[freq]:
            j = 0
            while j < len(bucket[freq]):
                result.append(bucket[freq][j])
                if len(result) == k:
                    return result
                j += 1
    return result


def run_topKFrequent():
    tests = [([1,1,1,2,2,3], 2, [1,2]),
             ([1], 1, [1]),
             ([1,2,1,2,1,2,3,1,3,2], 2, [1,2]),
    ]
    for test in tests:
        nums, k, ans = test[0], test[1], test[2]
        print(f"\nnums = {nums}")
        print(f"k = {k}")
        for method in ['heap', 'bucket']:
            if method == 'heap':
                result = topKFrequent_heap(nums, k)
            elif method == 'bucket':
                result = topKFrequent_bucket(nums, k)
            print(f"Method {method}: result = {result}")
            success = (sorted(ans) == sorted(result))
            print(f"Pass: {success}")
            if not success:
                print("Failed")
                return

run_topKFrequent()
