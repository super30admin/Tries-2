/*
//Using Priority Queue and HashMap
//Time: O(Nlogk)
//Space: O(N) Space taken by Hashmap and Priority queue
class Solution {
public:
    vector<int> ans;
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int,int> hmap;
        for(int num:nums){
            hmap[num]++;
        }
        priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> minHeap;
        for(auto p: hmap){
            minHeap.push({p.second,p.first});
            if(minHeap.size() > k)
                minHeap.pop();
        }
        while(!minHeap.empty()){
            ans.push_back(minHeap.top().second);
            minHeap.pop();
        }
        return ans;
    }
};
*/
//Using bucket Sort 
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        if(nums.size() == 1 and k == 1) return nums;
        vector<vector<int>> numArray(nums.size()+1,vector<int>());
        vector<int> ans;
        unordered_map<int,int> hmap;
        for(int num:nums){
            hmap[num]++;
        }
        for(auto &num : hmap){
            numArray[num.second].push_back(num.first);
        }
        int l = 0;
        for(int i = numArray.size()-1; i >= 0 and l < k; i--){
            int j = numArray[i].size();
            while(j != 0 and l < k){
                l++;
                ans.push_back(numArray[i][j-1]);
                j--;
            }
        }
        return ans;
    }
};