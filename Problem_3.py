# Top K Frequent Elements

# Time Complexity : O(nlogk)
# Space Complexity : O(N+k)
# Did this code successfully run on Leetcode : Yes, Runtime 100 ms and Memory 18.7 MB
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
# Approach
"""
Using HAshmap where Key is element and count is value. and using priority queue to return the most frequent element 
In python using heapq. The property of this data structure in Python is that each time the smallest of heap element 
is popped(min heap).nlargest(k, iterable, key = fun) :- This function is used to return the k largest  elements from 
the iterable specified and satisfying the key if mentioned.

"""
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if len(nums)==0 or nums==None: # Base Case
            return 0
        if k==len(nums):
            return nums
        count=Counter(nums) # Counter is an unordered collection where elements are stored as Dict keys and their count as dict value.
        return heapq.nlargest(k,count.keys(),key=count.get)