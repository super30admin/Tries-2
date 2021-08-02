//Time: O((m+n)*k) where m and n are the length the string and pattern and k is the number of queries
//Space : O(1) No extra space
class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        vector<bool> ans;
        int i = 0;
        for(auto &query :queries ){
            bool flag = false;
            i =0;
            for(int j = 0; j < query.length();j++){
                auto c = query[j];
                if(i < pattern.length() and c == pattern[i]){
                    i++;
                    if(i == pattern.length()){
                        flag = true;
                    }
                }
                else if(isupper(c)){
                    flag = false;
                    break;
                }
            }
            ans.push_back(flag);
        }
        return ans;
    }
};