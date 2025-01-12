#Approach
#Find frequency map and while finding we can have the min freq and max freq
# iterate from the max to min and add all the top k eems in result

#Complexities
#Time: O(N)
#Space: O(N)



from typing import List


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        freqMap = dict()
        minfreq = len(nums)
        maxfreq = 0
        for num in nums:
            freqMap[num] = freqMap.get(num, 0) + 1
            minfreq = min(minfreq, freqMap[num])
            maxfreq = max(maxfreq, freqMap[num])

        reverseFreqMap = dict()
        for keys in freqMap:
            if freqMap[keys] not in reverseFreqMap:
                reverseFreqMap[freqMap[keys]] = []
            reverseFreqMap[freqMap[keys]].append(keys)
        result = []
        for i in range(maxfreq, minfreq - 1, -1):
            if i in reverseFreqMap:
                for ele in reverseFreqMap[i]:
                    k -= 1
                    if k < 0:
                        break
                    else:
                        result.append(ele)
        return result