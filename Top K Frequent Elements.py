class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        #Approach: HashMap, Sorting
        #Time Complexity: O(n log k)
        #Space Complexity: O(n)
        #where, n is the length of nums
        
        if k == len(nums):
            return nums
        
        numsMap = {}
        for num in nums:
            numsMap[num] = numsMap.get(num, 0) + 1  
        
        result = []
        for key, item in sorted(numsMap.items(), key=lambda x: x[1], reverse=True):
            result.append(key)
            if len(result) == k:
                break
        
        return result