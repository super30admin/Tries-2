def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        
        h =[]
        d = {}
        for num in nums:
            if num in d:
                d[num] += 1
            else:
                d[num] = 1        
        for key,val in d.items():
            heapq.heappush(h,(val, key))     
            if len(h) > k:
                heapq.heappop(h)
        return [num[1] for num in h]
        
