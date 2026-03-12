# Time Complexity : O(N)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# Count frequencies using hashmap.
# Use bucket sort where index = frequency.
# Traverse buckets from high frequency.

from collections import Counter

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        
        count = Counter(nums)
        bucket = [[] for _ in range(len(nums)+1)]
        
        for num, freq in count.items():
            bucket[freq].append(num)
        
        res = []
        
        for i in range(len(bucket)-1, -1, -1):
            for num in bucket[i]:
                res.append(num)
                if len(res) == k:
                    return res