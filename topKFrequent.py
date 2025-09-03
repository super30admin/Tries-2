# TimeComplexity:O(nlogk)
# SpaceComplexity:O(k)
# Approach:BF would be store freq then sort and get most freq iterms form there
# optimized would be apply min heap


def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        heap=[]
        heapq.heapify(heap) #default min heap 1,2 4,5 
        d=defaultdict(int)
        for i in nums:
            d[i]+=1
        # heapq.heappush(heap,(d[i],i))
        # print(d)
        for i in d:
            
            heapq.heappush(heap,(d[i],i))
            while(len(heap)>k):
                heapq.heappop(heap)
        ans=[]
        prevcnt=0
        # for cnt,i in heap:
        #     if cnt!=prevcnt:
        #         ans.append(i)
        #         prevcnt=cnt
        #     if len(ans)==k:break
        while(heap):
            cnt,i=heapq.heappop(heap)
            ans.append(i)
            k-=1
        return ans
