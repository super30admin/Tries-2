# Time complexity : O(n)
# Space complexity : O(1)
# Leetcode : Solved and submitted

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # create two hashmaps one for freq count and other for bucket count
        freq, bucket = {}, {}
        
        # finding the min and max value from the buckets
        min_val, max_val = float('inf'), float('-inf')
        n = len(nums)
        
        # build the frequency map
        for i in range(n):
            if nums[i] not in freq:
                freq[nums[i]] = 0
            freq[nums[i]] += 1
        
        # build the bucket sort using the frequency count from the above Hashmap
        for key in freq:
            if freq[key] not in bucket:
                # also find the min and max frequeny from the bucket hashmap
                bucket[freq[key]] = []
                min_val = min(min_val, freq[key])
                max_val = max(max_val, freq[key])
            bucket[freq[key]].append(key)
        
        # traverse from the max till the min value and keep on adding the elements to the res
        # we can add max K value in the res
        res = []
        for i in range(max_val, min_val-1,-1):
            if i in bucket:
                lis = bucket[i]
                for num in lis:
                    if len(res) < k:
                        res.append(num)
        
        # return the result having K elements
        return res
