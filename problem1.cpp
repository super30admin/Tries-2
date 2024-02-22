/*
// Time Complexity : O(N*L^2) // N number of words, L average word length or maximum depth of recursinon
In each L you traverse L times to find prefix.
// Space Complexity : O(N*L) to save the trie and words.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
*/

#include<iostream>
#include<vector>
#include<string>

using namespace std;

const int asc{97};
const int arr_size{26};
class TrieNode{
    public:
    vector<string> prefix;
    vector<TrieNode*> arr;
    TrieNode(){
        arr.resize(arr_size,NULL);
    }
};

class Trie{
    public:
    vector<vector<string>> ans;
    TrieNode* root;
    int word_length{};
    Trie(){
        this->root = new TrieNode();
    }
    void make_tree(vector<string>& words){
        word_length = words.at(0).size();
        for(string& word:words){
            TrieNode* ptr = root;

            for(char c:word){
                int idx = c - asc;
                if(ptr->arr.at(idx) == NULL){
                    ptr->arr.at(idx) = new TrieNode();
                }
                ptr = ptr->arr.at(idx);
                ptr->prefix.push_back(word);
            }
        }
    }

    vector<string> search_prefix(string& prefix){
        vector<string> words{};

        TrieNode* ptr = root;
        //cout<<"the prefix is "<<prefix<<endl;
        for(char c:prefix){
            int idx = c - asc;
            if(ptr->arr.at(idx)==NULL){
                return vector<string>();
            }
            ptr = ptr->arr.at(idx);
        }
        //cout<<"prefix invoked"<<endl;
        //display(ptr->prefix);
        return ptr->prefix;
    }

    void display(vector<string>& vec){
        for(string& word:vec){
            cout<<word<<"\t";
        }
        cout<<endl;
    }
    
    void recursion(vector<string>& words,vector<string>& res){
        // if(res.size() == word_length){
        //     ans.push_back(res);
        //     return;
        // }
        for(string word:words){
            //action
            res.push_back(word);
            //display(res);
            //create prefix
            if(res.size() != word_length)
            {
                string prefix{};
                for(int i{};i<res.size();++i){
                    prefix+=res.at(i).at(res.size());
                }
                //search words
                vector<string> temp = search_prefix(prefix);
                //recurse
                recursion(temp,res);
            }
            else{
                ans.push_back(res);
            }
            //backtrack
            res.pop_back();            
        }

    }

    void find_solution(vector<string>& words){
        vector<string> res{};
        for(string word:words){
            //action
            res.push_back(word);
            //display(res);
            if(res.size() != word_length)
            {
                string prefix{};
                for(int i{};i<res.size();++i){
                    prefix+=res.at(i).at(res.size());
                }
                //search words
                vector<string> temp = search_prefix(prefix);
                //recurse
                recursion(temp,res);
            }
            else{
                ans.push_back(res);
            }
            //backtrack
            res.pop_back();
        }
    }

    vector<vector<string>> solution(){
        return this->ans;
    }


};
class Solution {
public:
    vector<vector<string>> wordSquares(vector<string>& words) {
        Trie Tree;
        Tree.make_tree(words);
        Tree.find_solution(words);
        return Tree.solution();
    }
};