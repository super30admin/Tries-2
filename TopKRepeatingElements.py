""""// Time Complexity : O(n)
// Space Complexity :O(n)
"""


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        d = {}
        result = []
        maxi = float('-inf')
        for i in nums:
            if i not in d:
                d[i] = 0
            d[i] += 1
            maxi = max(maxi, d[i])

        li = [-1] * (maxi + 1)

        for i in d:
            if li[d[i]] == -1:
                li[d[i]] = [][:]

            li[d[i]].append(i)

        for i in range(maxi, -1, -1):
            if li[i] != -1:
                for i in li[i]:
                    if k > 0:
                        result.append(i)
                        print(result)
                        k -= 1
                    else:
                        return result
        return result