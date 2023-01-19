class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        result = [0] * k

        hashMap = dict()
        maximum = float("-inf")

        # frequncy map

        for num in nums:
            hashMap[num] = hashMap.get(num, 0) + 1
            maximum = max(maximum, hashMap.get(num))

        buckets = [None] * (maximum + 1)

        for key in hashMap.keys():
            count = hashMap.get(key)
            if not buckets[count]:
                buckets[count] = list()
            buckets[count].append(key)

        for i in range(len(buckets) - 1, -1, -1):
            if k > 0:
                li = buckets[i]
                if li:
                    for j in range(len(li)):
                        if k >= 0:
                            result[k - 1] = li[j]
                            k -= 1
                        else:
                            break
            else:
                break

        return result

# Hashmap and bucket array
# Time Complexity: O(2n)
# Space Complexity: O(n) Size of hashMap
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No