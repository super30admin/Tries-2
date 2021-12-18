from typing import List

# Using buckets 
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        result = []
        hashmap = {}
        for num in nums:
            hashmap[num] = hashmap.get(num, 0) + 1
            
        buckets = [None for i in range(len(nums) + 1)]
        for key in hashmap.keys():
            freq = hashmap[key]
            if buckets[freq] == None:
                buckets[freq] = []
            buckets[freq].append(key)
        
        for i in range(len(buckets)-1, 0, -1):
            if len(result) < k:
                if buckets[i] != None:
                    result += buckets[i]
        
        if len(result) > k:
            return result[:k]
        else:
            return result

# Time Complexity: O(n)
# Space Complexity: O(n)