//Time - O(n)
//Space - O(n)
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        vector<vector<int>> bucket(nums.size()+1);
        unordered_map<int,int> umap;
        for(auto n:nums){
            umap[n]++;
        }
        
        for(auto ele:umap){
            int idx = ele.second;
            bucket[idx].push_back(ele.first);
        }
        
        vector<int> result (k);
        for(int i = bucket.size()-1;i>=0 && k>0;i--){
            for(int j = 0;j<bucket[i].size() && k>0;j++){
                result[k-1] = bucket[i][j];
                k = k-1;
            }
        }
        
        return result;
    }
};