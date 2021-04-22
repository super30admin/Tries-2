#Time Complexity:O(n)
#Space Complexity:O(n)
#Approach : Use a hashmap to hold the unique integers from the array and their counts. Use a min heap to hold the counts and numbers and pop
#from minheap k elements into the result array
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        result=[]
        if k==0:
            return result
        count=defaultdict()
        for num in nums:
            if num not in count:
                count[num]=0
            count[num]+=1
        minheap=[]
        for num in count:
            minheap.append([-count[num],num])
        heapq.heapify(minheap)
        for i in range(k):
            elem=heapq.heappop(minheap)
            result.append(elem[1])
        return result