# Problem 3 : Top K Frequent Elements
# Time Complexity : O(n) where n is the number elements in nums list
# Space Complexity : O(n) where n is the number elements in nums list
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# Your code here along with comments explaining your approach

from typing import List
from collections import defaultdict

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # define freqMap which will store num as key and its frequency as value
        freqMap = defaultdict(int)
        
        # loop through nums list
        for num in nums:
            # increment the value of the num key in freqMap
            freqMap[num] += 1
       
       # define minFreq and maxFreq and set to inf and 0 respectively
        minFreq = float('inf')
        maxFreq = 0
        # define freqToNum hash map which store frequency as key and list of the number as value
        freqToNum = defaultdict(list)

        # loop through each key, value pair of freqMap
        for num, freq in freqMap.items():
            # get the minimum between minFreq and freq
            minFreq = min(minFreq, freq)
            # get the maximum between maxFreq and freq
            maxFreq = max(maxFreq, freq)
            # add the num to list of values to freq key in freqToNum hash map
            freqToNum[freq].append(num)
        
        # define result list which store the top k frequent num
        result = []
        # loop from maxFreq to minFreq
        for freq in range(maxFreq, minFreq - 1, -1):
            # check if the freq is present as key in freqToNum hash map
            if freq in freqToNum:
                # if it is present then loop through the list of values for that freq
                for num in freqToNum[freq]:
                    # append the num to the result
                    result.append(num)
                    # check if the length of the result is equal to k
                    if len(result) == k:
                        # if it is then return result since it has k top frequent num
                        return result
        # return result
        return result
