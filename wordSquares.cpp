// Time complexity: 
// Space complexity: 

/*
Create a trie to store all the words and the startsWith vectors in each node of the trie 

Pick a new word and spawn a method to get the word square starting from that - use backtracking

Use a backrtacking method to get all the other words which satisy the condition of word squares and return the list of words when the length of the list reaches the lenght of the first word we were looking at 
*/


class Solution {
public:
    class TrieNode {
    public:
        vector<TrieNode*> children;
        vector<string> startsWith;
        TrieNode () {
            children = vector<TrieNode*> (26);
            startsWith = vector<string> ();
        }
    };
    TrieNode* root = new TrieNode();
    
    void constructTrie(string word) {
        TrieNode* curr = root;
        for(char c:word) {
            if(curr->children[c-'a'] == NULL) {
                curr->children[c-'a'] = new TrieNode();
            }
            curr = curr->children[c-'a'];
            curr->startsWith.push_back(word);
        }
        return;
    }
    
    vector<string> getWords(string prefix) {
        TrieNode* curr = root;
        for(char c: prefix) {
            if(curr->children[c-'a'] != NULL)
                curr = curr->children[c-'a'];
            else
                return vector<string>();
        }
        return curr->startsWith;
    }
    
    vector<vector<string>> result;
    

    // iterate over every word and use that as the starting point to get the qord squres
    vector<vector<string>> wordSquares(vector<string>& words) {
        if(words.size() == 0)
            return vector<vector<string>> ();
        // construct trie 
        for(string word: words)
            constructTrie(word);
        
        result = vector<vector<string>>();
        vector<string> list;
        
        // get result for each word and add it to the result
        for(string word: words) {
            //action
            list.push_back(word);
            //recurse
            backtrack(list, word.length());
            //backtrack
            list.pop_back();
        }
        return result;
    }
    
    // With the words currently in the list, get the prefix 
    // get the words starting with prefix and add each of those to the list and call the same method to continue getting the solution 
    // base case is when we have all the words we want in the list - 
    // The list can't have more words than the length of the frist word
    void backtrack(vector<string> list, int k) {
        // base
        // when the number of words in the list is k, add the list to the result 
        if(list.size() == k) {
            result.push_back(list);
            return;
        }
        // logic
        // pick up a string starting with prefix
        // get the prefix
        string prefix = "";
        int index = list.size();
        for(string word:list) {
            prefix += word[index];
        }
        //get the words starting with prefix (could be more than one)
        vector<string> startsWith = getWords(prefix);
        for(string word: startsWith) {
            // add each word to the list and call backtrack
            // action
            list.push_back(word);
            // recurse
            backtrack(list, word.length());
            // backtrack
            list.pop_back();
        }
        
    }
};