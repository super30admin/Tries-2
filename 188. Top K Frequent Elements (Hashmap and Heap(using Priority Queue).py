from queue import PriorityQueue


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        result = [0] * k

        hashMap = dict()

        # frequncy map
        for num in nums:
            hashMap[num] = hashMap.get(num, 0) + 1

        pq = PriorityQueue()

        for key in hashMap.keys():
            pq.put((hashMap.get(key), key))
            if pq.qsize() > k:
                pq.get()

        for i in range(k - 1, -1, -1):
            curr = pq.get()
            result[i] = curr[1]

        return result

# Hashmap and Heap(using Priority Queue)
# Time Complexity: O(nlogk)
# Space Complexity: O(n) Size of hashMap
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No