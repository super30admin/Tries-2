class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        
        count = Counter(nums)
        
        bucket = [[] for _ in range(len(nums)+1)]

        for i in count:
            bucket[count[i]].append(i)
        
        output = []
        for i in reversed(bucket):
            for j in i:
                if j!=[]:
                    output.append(j)
                    k-=1
            if not k:
                return output
                
Time: O(N)
Space: O(N)
