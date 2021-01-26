"""
Time Complexity : O(nlogk) for the heap solution and O(n) for the bucket sort solution 
Space Complexity : O(n) hashmap 
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

I implemented 2 solutions here. One using priority queue/heaps and the other using bucket sort. For the heaps, we just make a hashmap
with key as the number and value as its frequency of appearing inside the array. After that, we make a heap while iterating
through the map, and as soon as the capacity increasing k, we pop out the minimum freq from the heap. This way, we get the maximum
k freq inside heap. For bucket sort, we make the hashmap in the similar way, and after that make an array of size equal to length 
of the initial array, and in frequency indexes, we put list of keys. Thus way when we iterate from the back, we get the highest freq
elements first.
"""
from heapq import heapify
from heapq import heappop as remove


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if not nums:
            return nums
        countMap = {}
        for n in nums:
            if n in countMap:
                countMap[n] += 1
            else:
                countMap[n] = 1
        heap = []
        for key, value in countMap.items():
            heap.append((-value, key))
        heapify(heap)
        result = []
        l = len(heap)
        for i in range(k):
            result.append(remove(heap)[1])
        return result


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if not nums:
            return nums
        countMap = {}
        result = []
        for n in nums:
            if n in countMap:
                countMap[n] += 1
            else:
                countMap[n] = 1
        arr = [None]*(len(nums)+1)
        for key, value in countMap.items():
            if not arr[value]:
                arr[value] = []
            arr[value].append(key)
        for i in range(len(nums), -1, -1):
            if arr[i]:
                if k > 0:
                    li = arr[i]
                    for l in li:
                        result.append(l)
                        k -= 1
                else:
                    break
        return(result)
