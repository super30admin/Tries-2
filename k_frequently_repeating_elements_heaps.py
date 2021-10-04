#Time complexity : O(nlogk) where n is number of elements 
#Space complexity : O(n) 

import heapq
from collections import defaultdict
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if len(nums)==0: 
            return 0
        hmap = defaultdict(int)
        for item in nums:
            hmap[item]+=1
        heap = []
        
        for key,value in hmap.items():
            heapq.heappush(heap,(value,key))
            if len(heap)>k:
                heapq.heappop(heap)
        res = []
        while(heap):
            res.append(heapq.heappop(heap)[1])
        return res
            
            
        