class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # T: O(n + m log k), S: O(m + k)
        # Step 1: Count the frequency of each element
        count = Counter(nums)

        # Step 2: Use a min-heap to keep track of top k elements
        heap = []
        for num, freq in count.items():
            heapq.heappush(heap, (freq, num))
            if len(heap) > k:
                heapq.heappop(heap)

        # Step 3: Extract the elements from the heap
        return [num for freq, num in heap]
