//Using HashMap and Priority Queue
class Solution {

    //Time Complexity: 0(nlogk) where k is the top no. of elements and n is the no. of elements in nums
    //Space Complexity:: 0(n)

    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int []{};
        }

        HashMap<Integer, Integer> map = new HashMap<>();    //I am taking a hashmap to store the frequencies of elements
        int [] result = new int [k];    //to return the top k elements
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);    //Min heap to filter top k elements. I just store k no of elements in my priority queue

        for(int num : nums){    //iterating over nums array and storing their frequencies
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int key : map.keySet()){    //iterating over keys and creating a priority queue
            pq.add(new int[] {key, map.get(key)});
            if(pq.size() > k){
                pq.poll();
            }
        }
        //When I come out, I will have only k elements left in my pq. So i poll then and take the 1st element and add it to my result
        while(!pq.isEmpty()){
            int[] polled = pq.poll();   //polling.
            result[k-1] = polled[0];    //Storing at last index in result the 1st element of polled pq as 1st element is the num
            k--;    //doing k-- to store all elements in the array
        }

        return result;  //returning result
    }
}

//Using a hashmap and a list

class Solution {

    //Time Complexity : 0(n)
    //Space Complexity : 0(n)

    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int []{};
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int [] result = new int [k];
        int max = 0;

        for(int num : nums){    //getting frequency of each number
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));  //getting max
        }

        List [] list = new List[max + 1];   //list to store the nums as per their frequency. Here frequency act as indexes

        for(int key : map.keySet()){    //iterating over keyset and adding nums to list
            int index = map.get(key);
            if(list[index] == null){    //if it is null, means no element is added at that index
                list[index] = new ArrayList<>();    //hence initializing an arraylist at that index
            }
            list[index].add(key);   //adding the num
        }

        for(int i = max; i >= 0 && k >=0; i--){ //iterating over the list. i starts from max as the size of list is max and also k >= 0 because I only add k elements in my result
            List<Integer> li = list[i]; //getting the list for that frquency
            if(li != null){ //if list is not equal to null. As I might have 3 elemnts with 3 frequency and 0 with 2 frequency and 2 elements with 1 frequency. So, index 2 will be empty
                for(int j = 0; k > 0 && j < li.size(); j++){    //I start iterating over my list. and again keep a k > 0 condition as I only need k elements
                    result[k-1] = li.get(j);    //I store the element at the last index and move
                    k--;    //also reduce my k
                }
            }
        }

        return result;  //At the end I return result
    }
}