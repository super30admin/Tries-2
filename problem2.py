#time O(N)
#spac  O(N)
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        from collections import Counter
        list1=[]
        counter = Counter(nums)
        for i in counter.items():
            heapq.heappush(list1,(i[1],i[0]))
            if len(list1)>k:
                heapq.heappop(list1)
        print(list1)
        return [ b for a,b in list1]

        