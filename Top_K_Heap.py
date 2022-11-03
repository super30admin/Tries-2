# Time complexity : O (n log k)
# Space complexity : O(n)
# Leetcode : Solved and submitted

import heapq
class Solution:
   # For max heap, define the comparator function to maintain the elements with highest frequency
    class Distance:
        def __init__(self, val, freq):
            self.val = val
            self.freq = freq
        def __lt__(self, other):
            return self.freq[self.val] < self.freq[other.val]
            
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # find the lenght of the list and build a frequency hashmap
        n = len(nums)
        self.freq = {}
        res = []
        
        # count the frequency of the numbers in Hashmap
        for i in range(n):
            if nums[i] in self.freq:
                self.freq[nums[i]] += 1
            else:
                self.freq[nums[i]] = 1
        
        # build a maxHeap
        minHeap = []
        
        # traverse over the keys in the hashmap and insert them into heap
        for keys in self.freq:
            heapq.heappush(minHeap, self.Distance(keys, self.freq))
            
            # if the capacity of k elements is reached in the heao, then remove the element with least value
            if len(minHeap) > k:
                heapq.heappop(minHeap)
        
        # pop the K elements from the heap and append them to the result
        while minHeap:
            key = heapq.heappop(minHeap).val
            res.append(key)
        
        # return resulting k elements
        return res
