class TrieNode{
public:
    vector<TrieNode*> children;
    vector<string> words;
    TrieNode(){
        children = vector<TrieNode*> (26, NULL);
    }
};


class Solution{
    
    TrieNode* buildTrie(vector<string> wordList){
        TrieNode* root = new TrieNode();

        for(auto str:wordList){
            TrieNode* curr = root;
            for(auto c:str){
                if(curr->children[c-'a'] == NULL){
                    curr->children[c-'a'] = new TrieNode();
                }
                curr = curr->children[c-'a'];
                curr->words.push_back(str);
            }
        }

        return root;
    }
    
    vector<string> getWordsStartingWith(string str, TrieNode* root){
        TrieNode* curr = root;

        for(auto c:str){
            if(curr->children[c-'a'] == NULL){
                return vector<string>();
            }
            curr = curr->children[c-'a'];
        }

        return curr->words;
    }

public:
    vector<vector<string>> result;
    vector<vector<string>> wordSquares(vector<string> wordList) {
        TrieNode* root = buildTrie(wordList);

        vector<string> list;
        int len = wordList.size();
        
        for(auto word:wordList){
            list.push_back(word);
            backtrack(list, root, len);
            list.pop_back();
        }
 
        return result;
    }
    
    void backtrack(vector<string> list, TrieNode* root, int& len){
        //base case
        if(list.size() == len){
            result.push_back(list);
            return;
        }
        //logic
        string str;
        for(string s:list){
            str = str + s[list.size()];
        }

        vector<string> startsWith = getWordsStartingWith(str, root);
        for(auto s:startsWith){
            list.push_back(s);
            backtrack(list, root, len);
            list.pop_back();
        }
    }

};

int main() {
    std::cout << "Hello World!\n";
    Solution sol;
    vector<string> wordList {"area","lead","wall","lady","ball"};
    vector<vector<string>> result;
    result = sol.wordSquares(wordList);
    
    for(auto wordsList:result){
        for(auto word:wordsList){
            cout<<word<<" ";
        }
        cout<<endl;
    }
    return 0;
}


