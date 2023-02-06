package s30.tries.tries2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Algo: Use max heap on freq of element using Entry. then remove k times from the heap.

//TC: O(nlogn + klogn)
//Sc: O(n)
public class TopKFrequent {


    PriorityQueue<Map.Entry<Integer,Integer>> pq;
    Map<Integer, Integer> freq;
    public int[] topKFrequent(int[] nums, int k) {

        int[] res = new int[k];

        pq = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        freq = new HashMap();

        for(int n : nums){
            if(!freq.containsKey(n)){
                freq.put(n, 1);
            }
            else {
                freq.put(n, freq.get(n)+1);
            }
        }


        for(Map.Entry<Integer,Integer> entry : freq.entrySet()){
            pq.add(entry);
        }

        int index=0;
        while(index < k){
            res[index] = pq.remove().getKey();
            index++;
        }

        return res;
    }


    public static void main(String[] args) {

    }
}
