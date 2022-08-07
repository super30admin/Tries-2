# Time Complexity : O(n)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:

        hashMap = {}
        for n in nums:
            hashMap[n] = 1 + hashMap.get(n,0)

        freqList = []
        for key,value in hashMap.items():
            freqList.append((-value,key))

        heapq.heapify(freqList)

        res = []
        while len(res) < k:
            pop = heapq.heappop(freqList)
            res.append(pop[1])

        return res
