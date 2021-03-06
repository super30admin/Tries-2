//Time - O(NxK) k - max (query, pattern)
//Space - O(N)
class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        vector<bool> result;
        
        for(auto query:queries){
            bool flag = false;
            int i = 0;
            for(int j = 0;j<query.size();j++){
                char c = query[j];
                if(i<pattern.size() && c == pattern[i]){
                    i++;
                    if(i == pattern.size()){
                        flag = true;
                    }
                }else if(isupper(c)){
                    flag = false;
                    break;
                }
            }
            result.push_back(flag);
        }
       
        return result;
        
    }
};