// Time Cimplexity - O(N*(l + k)), where 'N' are the total number of queries, 'l' is the length of 'pattern' and 'k' is the average length of each query.
// Space Compexity - O(1)

class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        int ptrQ = 0;
        int ptrP = 0;
        int n = queries.size();
        int l = pattern.length();
        vector<bool> cmlM;
        for(int i = 0; i < n; i++){
            string curr = queries[i];
            int k = curr.length();
            ptrP = 0;
            ptrQ = 0;
            bool flag = false;
            
            while(ptrQ < k && ptrP < l){
                if(curr[ptrQ] == pattern[ptrP]){
                    if(ptrP == l-1){
                        flag = true;
                    }
                    ptrP++;
                }
                else if(isupper(curr[ptrQ])){
                    flag = false;
                    break;
                }
                ptrQ++;
            }
            
            
            if(flag){
                while(ptrQ < k){
                    if(isupper(curr[ptrQ])){
                        flag = false;
                        break;
                    }
                    ptrQ++;
                }
            }
            
            cmlM.push_back(flag);
        }
        
        return cmlM;
    }
};