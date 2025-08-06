#TC:O(nlogn) #logn for heapq insertions
#SC:O(n)
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        freq_map = {}
        for n in nums:
            if n in freq_map:
                freq_map[n]+=1
            else:
                freq_map[n] = 1
        heap = []
        for key in freq_map:
            heapq.heappush(heap,(-freq_map[key],key))
        output = []
        for i in range(0,k):
            element = heapq.heappop(heap)
            output.append(element[1])
        return output

        