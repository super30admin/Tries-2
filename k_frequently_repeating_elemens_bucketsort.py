#Time complexity : O(n) where n is number of elements 
#Space complexity : O(n) 
import heapq
from collections import defaultdict
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if len(nums)==0: 
            return 0
        hmap = defaultdict(int)
        maxi = float('-inf')
        mini = float('inf')
        for item in nums:
            hmap[item]+=1
            maxi = max(hmap[item],maxi)
            mini = min(hmap[item],mini)
        counts_array = [[]for i in range(maxi+1)]
        
        for key,value in hmap.items():
            counts_array[value].append(key)
        res = []
        flag = False
        for i in range(len(counts_array)-1,-1,-1):
            for ele in counts_array[i]:
                res.append(ele)
                k-=1
                if k == 0:
                    flag = True
                    break
            if flag:
                break
        return res
            
            
        