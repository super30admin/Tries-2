# Time Complexity : O(N) where N is number of elements in nums
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using a frequency map to count the occurrences of each number in the input list.
# Then, I am using a frequency bucket (fmap) where the keys are frequencies and the values are lists of numbers with that frequency.
# I keep track of the maximum and minimum frequencies while populating the fmap.
# Finally, I iterate from the maximum frequency to the minimum frequency, collecting numbers until I have k elements for the result.
# This ensures that I get the k most frequent elements efficiently.


from typing import List
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        freq = {}
        fmap = {}
        maxi = 0
        mini = float('inf')
        for i in nums:
            freq[i] = freq.get(i,0) + 1
        for t in freq:
            f = freq[t]
            if f not in fmap:
                fmap[f] = []
            fmap[f].append(t)
            mini = min(mini,f)
            maxi = max(maxi,f)
        res = [0] * k
        idx = 0
        for i in range(maxi,mini-1,-1):
            if i not in fmap:
                continue
            for n in fmap[i]:
                res[idx] = n
                idx += 1
                if idx == k:
                    break 
            if idx == k:
                break       
        return res


