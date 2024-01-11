# Time: O(n)
# Space: O(n)
# Did it run on Leetcode: yes

from collections import defaultdict
from Queue import PriorityQueue

class Solution(object):
    def topKFrequent(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        # O(nlogk)
        hmap = defaultdict(int)
        for num in nums:
            hmap[num] += 1

        pq = PriorityQueue()
        for num, freq in hmap.items():
            pq.put((freq, num))
            if pq.qsize() > k:
                pq.get()

        res = []
        while not pq.empty():
            res.append(pq.get()[1])

        return res[::-1]
# using bucketsort
# O(n)
        # hmap = defaultdict(int)
        # for num in nums:
        #     hmap[num] += 1
        # freqmap={}
        # minel=float('inf')
        # maxel=float('-inf')
        # for key in hmap:
        #     freq=hmap[key]
        #     maxel=max(maxel,freq)
        #     minel=min(minel, freq)
        #     if freq not in freqmap:
        #         freqmap[freq]=[]
        #     freqmap[freq].append(key)
        # res=[]
        
        # for j in range(maxel, minel+1, -1):
        #     if j in freqmap:
        #         li=freqmap[j]
        #         if(len(li)==0):
        #             continue
        #         for i in range(len(li)):
        #             if(k>0):
        #                 res.append(li[i])
        #                 k-=1
        #             else:
        #                 break
        # return res
