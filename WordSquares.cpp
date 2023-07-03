// Time Complexity - O(n*26^l), n is the size of 'words' vector.
// Space Complexity - O(n*k), n is the size of 'words' vector. and 'k' is the length of each word.
// Problems Faced - What is 'l' in the n*26^l in the Time Complexity? I am not able to figure out.

class Solution {
    struct TrieNode{
        vector<TrieNode*> children;
        vector<string> strsWtprefix;
        
        public: TrieNode(): children{NULL}{
            children.resize(26);
            strsWtprefix.clear();
        }
    };
            
    void insert(string word, TrieNode* root){
        TrieNode* curr = root;
        int l = word.length();

        for(int i = 0; i < l; i++){
            char c = word[i];
            if(curr->children[c - 'a'] == NULL)
                curr->children[c - 'a'] = new TrieNode();
            curr = curr->children[c - 'a'];
            curr->strsWtprefix.push_back(word);
        }
    }
        
    vector<string> searchAllStrs(string prefix, TrieNode* root){
        TrieNode* curr = root;
        int l = prefix.length();
        for(int i = 0; i < l; i++){
            char c = prefix[i];
            if(curr->children[c - 'a'] == NULL)
                return {};
            curr = curr->children[c - 'a'];
        }
        
        return curr->strsWtprefix;
    }
    
    void helper(vector<string>& words, vector<string>& currList, vector<vector<string>>& answer, TrieNode* root){
        // base
        if(currList.size() == words[0].size()){
            answer.push_back(currList);
            return;
        }
        // logic
        int lPrefix = currList.size();
        string prefix = "";
        for(int i = 0; i < lPrefix; i++)
            prefix += currList[i][lPrefix];
        
        vector<string> strs = searchAllStrs(prefix, root);
        
        for(int i = 0; i < strs.size(); i++){
            // action
            currList.push_back(strs[i]);
            
            // recurse
            helper(words, currList, answer, root);
            
            // backtrack
            currList.pop_back();
        }
    }
    
public:
    vector<vector<string>> wordSquares(vector<string>& words) {
        TrieNode* root = new TrieNode();
        vector<vector<string>> answer;
        int n = words.size();
        for(int i = 0; i < n; i++)
            insert(words[i], root);
        
        vector<string> currList;
        for(int i = 0; i < n; i++){
            // action
            currList.push_back(words[i]);
            
            // recurse
            helper(words, currList, answer, root);
            
            // backtrack
            currList.pop_back();
        }
        return answer;
    }
};