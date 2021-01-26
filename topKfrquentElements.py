#Time Complexity : O(n)
#Space Complexity : O(n) 
#Did this code successfully run on Leetcode : Yes

from collections import Counter
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        #BRUTE FORCE
        #counts = Counter(nums)
        # arr = sorted(nums_count, key = nums_count.get,reverse = True)
        # return(arr[:k])


        #HASHMAP + HEAP: TC - O(nlogk), SC - O(n)
        #counts = Counter(nums)
        #return heapq.nlargest(k, counts.keys(), key=counts.get)

        counts = Counter(nums)
        buckets = [[] for _ in range(len(nums)+1)]
        result = []

        for key, val in counts.items():
            buckets[val].append(key)

        for sublist in buckets[::-1]:
            for ele in sublist:
                if len(result) < k:
                    result.append(ele)
                else:
                    break

        return result
