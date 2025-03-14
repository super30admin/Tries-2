# Problem 3
# Top K Frequently Repeating Elements (https://leetcode.com/problems/top-k-frequent-elements/)

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        Map = {}
        freqMap = {}
        result = []
        Min = 204370
        Max = -328829

        for num in nums:
            if num not in Map:
                Map[num] = 0
            Map[num] += 1

        for key in Map:
            if Map[key] not in freqMap:
                freqMap[Map[key]] = []
            freqMap[Map[key]].append(key)
            Min = min(Min,Map[key])
            Max = max(Max,Map[key])

        idx = 0
        for i in range(Max,Min-1,-1):
            if idx == k: break
            if i not in freqMap: continue
            lst = freqMap[i]
            for el in lst:
                result.append(el)
                idx += 1

        return result




        
        