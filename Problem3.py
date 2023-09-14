class Solution:
    def topKFrequent(self, nums, k):
        # Time complexity -O(n log k), min heap and extracting the k most frequent elements each take O(n log k), where k is the number of elements we want to find.
        # Space complexity - O(n + k), which is dominated by the O(n) space used for the count dictionary.
        # Step 1: Count the frequency of each element using a Counter
        count = Counter(nums)

        # Step 2: Create a min heap to store the k most frequent elements
        min_heap = []

        for num, freq in count.items():
            # Push elements into the min heap
            heapq.heappush(min_heap, (freq, num))

            # If the size of the min heap exceeds k, remove the smallest element
            if len(min_heap) > k:
                heapq.heappop(min_heap)

        # Step 3: Extract the k most frequent elements from the min heap
        result = []
        while min_heap:
            result.append(heapq.heappop(min_heap)[1])

        # Reverse the result to get elements in descending order of frequency
        result.reverse()

        return result
