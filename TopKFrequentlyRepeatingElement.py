from typing import List,Counter
import heapq 
# class Solution:
#     def topKFrequent(self, nums: List[int], k: int) -> List[int]:
#         mapcount = Counter(nums)
#         pq = []
#         for keys in mapcount:
#             heapq.heappush(pq, (mapcount[keys],keys))
#             if len(pq)>k:
#                 heapq.heappop(pq)
#         res = [0]*k
#         idx = 0
#         while pq:
#             res[idx] = heapq.heappop(pq)[1]
#             idx+=1
#         return res

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        mapcount = Counter(nums)
        freqtonum = {}
        minval = float("inf")
        maxval = float("-inf")
        for key in mapcount:
            freq = mapcount[key]
            if  freq not in freqtonum:
                freqtonum[freq] = []
            freqtonum[freq].append(key)
            minval = min(minval,freq)
            maxval = max(maxval,freq)
        res,idx = [0]*k,0
        for i in range(maxval,minval-1,-1):
            if i not in freqtonum: continue
            if idx == k: break
            for n in freqtonum[i]:
                res[idx] = n
                idx+=1
                if idx==k:break
        return res
        

        