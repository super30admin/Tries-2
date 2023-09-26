class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        x={}
        ans=[]
        for i in range(len(nums)+1):
            x[i]=nums.count(i)
        for i in x.items():
            print(i)
            if (x[i]>=k):
                ans.append(i)
                k=k-1
                continue
        return [-1] if len(ans)==0 else ans