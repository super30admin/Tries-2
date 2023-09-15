# Time Complexity : O(nlogk)
# Space Complexity : O(k)
# Passed on Leetcode: yes

import collections
import heapq

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        frequency_map = collections.Counter(nums)
        
        min_heap = []
        
        for num, freq in frequency_map.items():
            heapq.heappush(min_heap, (freq, num))
            
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        
        result = [element for freq, element in min_heap]
        return result
