import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
/*
Time Complexity: O(Nlogk), N is the number of elements in the given array and k is the frequency given
Space Complexity: O(N+k)
Run on leetcode: Yes
Any difficulties: No

Approach:
1. Create Frequency Map and then use Minheap to sort the elements based on their frequency
2. Keep adding elements till size of heap becomes K
3. Make resultant by popping out the elements from the array
 */
public class TopKFrequentElements {
    public static int[] topKFrequentElements(int[] nums, int k){
        if(k == nums.length){
            return nums;
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (n1,n2)-> map.get(n1)-map.get(n2)
        );

        for(int num: map.keySet()){
            heap.add(num);
            if(heap.size()>k){
                heap.poll();
            }
        }

        int[] result = new int[k];

        for(int i = 0; i<k; i++){
            result[i] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println("Top K Frequent Elements: "+ Arrays.toString(topKFrequentElements(new int[]{1,1,1,2,2,3}, 2)));
    }
}
