#Time: O(n)
#Space: O(n)
#Program ran on leetcode successfully

import heapq

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        countMap = dict()
        for num in nums:
            countMap[num] = countMap.get(num, 0) + 1
        
        queue = []
        for key in countMap.keys():
            print(countMap[key])
            temp = [countMap[key], key]
            heapq.heappush(queue, (countMap[key], key))
            if (len(queue) > k):
                heapq.heappop(queue)
            
        result = [0] * k
        i = 0
        while (queue):
            result[i] = heapq.heappop(queue)
            i+= 1
        
        return result

        


        