// Approach 1: Frequency map + min heap

// Time complexity: O(n log k) lok k to insert into min heap
// Space complexity: O(n) -> k <= n, therefore bounded by n

class Comparator {
public:
    bool operator ()(pair<int, int> a, pair<int,int>b) {
        return b.second < a.second;
    }
};

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        if(nums.size() == 0)
            return nums;
        unordered_map<int, int> fMap;
        priority_queue<pair<int,int>, vector<pair<int,int>>, Comparator> pq;
        // create the frequency map 
        for(int n:nums) {
            fMap[n]++;
        }

        // push the pairs into the min heap of size 3 - this will store the 3 max frequencies 
        for(auto it:fMap) {
            pq.push(it);
            if(pq.size() > k)
                pq.pop();
        }

        vector<int> result;
        while(!pq.empty()) {
            result.push_back(pq.top().first);
            pq.pop();
        }
        return result;
    }
};


// Approach 2: Frequency map + counting buckets 

// Time complexity: O(n) - going over the numbers and max will have n elements in the buckets 
// Space complexity: O(n) 


class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        if(nums.size() == 0)
            return nums;
        unordered_map<int, int> fMap;
        int maxFreq = INT_MIN;
        vector<int> result;

        for(int n:nums) {
            fMap[n]++;
            maxFreq = max(maxFreq, fMap[n]);
        }

        // create bucekts where the index is the frequency 
        vector<vector<int>> buckets(maxFreq+1);

        // push the keys/elemetns at the frequency index 
        for(auto it: fMap) {
            buckets[it.second].push_back(it.first);
        }

        // iterate over the buckets from the back and update the result
        for(int i = maxFreq; i>0; i--) {
            for(int j = 0; j < buckets[i].size() && k > 0; j++) {
                result.push_back(buckets[i][j]);
                k--;
            }
        }
        return result;
    }
};




