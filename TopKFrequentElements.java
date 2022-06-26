import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Time Complexity: O(n); where n is length of input array.
//Space Complexity: O(n)
public class TopKFrequentElements {
	/**Approach2: HashMap + Bucket sort**/	
	public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if(k == nums.length) return nums;         
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){ //O(n)
            map.put(num, map.getOrDefault(num, 0)+1);
        }        
        List<Integer>[] buckets = new ArrayList[n + 1];
        for(int i=0; i<=n; i++){ //O(n)
            buckets[i] = new ArrayList<>();
        }
        for(Integer key: map.keySet()){ //O(n)
            buckets[map.get(key)].add(key);
        }
        int[] res = new int[k];
        int cnt=0;//counter on result array        
        for(int i=n; i>=0; i--){ //O(n)
        	if(cnt == k) return res;
            for(int num : buckets[i]){
                if(cnt == k) return res;
                res[cnt] = num;
                cnt++;                                 
            }
        }
        return new int[0];  
    }
	
	/**Approach1: HashMap + Min Heap | Time O(nlogk) | Space O(n); where n is length of input array**/	
	/*public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }        
        PriorityQueue<int[]> q = new PriorityQueue<>(k, (a,b) -> a[1]-b[1]);         
        for(Integer key: map.keySet()){
            q.add(new int[] {key, map.get(key)});
            if(q.size() > k){
                q.poll();
            }   
        }      
        int[] res = new int[k];        
        int idx=0;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            res[idx] =  curr[0];
            idx++;            
        }        
        return res;
    }*/
		
	/** Driver code to test above **/
	public static void main (String[] args) {			
		TopKFrequentElements ob  = new TopKFrequentElements();
		int[] nums = {-1, -1}; //{1,1,1,2,2,3};
		int k = 1;//2;
								
		System.out.println("Top "+k+" frequent elements: "+Arrays.toString(ob.topKFrequent(nums, k))); 
	}	
}
