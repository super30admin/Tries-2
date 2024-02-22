#include<iostream>
#include<vector>
#include<string>

using namespace std;

//use this if only unique charcters in the sentence as in unique prefix with no case check

const int arr_size{26};
const int asc{97};

class TrieNode{
    public:
    vector<TrieNode*> arr;
    bool caps;
    bool flag;
    // Trie(){
    //     this->arr.resize(26,false);
    //     this->caps = false;
    //     this->flag = false;
    // }
    // Trie(bool caps_val){
    //     this->arr.resize(26,false);
    //     this->caps = caps_val;
    //     this->flag = false;
    // }
    // Trie(bool caps_val, bool flag_val){
    //     this->arr.resize(26,false);
    //     this->caps = caps_val
    //     this->flag = flag_val
    // }
    TrieNode(bool caps_val = false,bool flag_val = false):caps(caps_val),flag(flag_val){
        this->arr.assign(arr_size,NULL);
    }
};

class Trie{
    public:
    TrieNode *root;
    int tree_size;
    vector<bool> p_arr;
    string pattern;
    Trie(){
        this->root = new TrieNode();
        this->tree_size = 0;
        pattern = "";
    }
    // dont make a new node if the tree exist edit this part.
    void make_tree(const vector<string>& queries){
        //cout<<"char\tascii_val\tidx\tcaps_val\tflag"<<endl;
        this->tree_size = queries.size();
        for(const string& query:queries){
            TrieNode* ptr = root;
            int sz = query.size();
            int idx{};

            for(int c: query){
                bool caps_val{false};
                bool flag{false};
                if(c<97){
                    c+=32;
                    caps_val = true;
                }
                if(idx == sz-1){
                    flag = true;
                }
                //cout<<char(c)<<"\t"<<c<<"\t"<<int(c-asc)<<"\t"<<caps_val<<"\t"<<flag<<endl;
                int q_idx = c-asc;
                if(ptr->arr.at(q_idx) == NULL){
                    ptr->arr.at(q_idx) = new TrieNode(caps_val,flag);
                }
                ptr = ptr->arr.at(q_idx);
                ++idx;
                //cout<<"error\n";
            }
        }
        //cout<<"end_tree"<<endl;
    }
    void make_pattern_array(string& pattern){
        this->pattern = pattern;
        int p_sz = pattern.size();
        this->p_arr.resize(p_sz,false);
        int j{};
        for(const int c:pattern){
            if(c<97){
                this->p_arr.at(j) = true;
                this->pattern.at(j) = char(c+32);
            }
            ++j;
        }
    }

    bool find_pattern(const string& query){
        int psz = this->pattern.size();
        TrieNode* ptr = root;
        int j{};
        cout<<query<<endl;
        for(int c:query){
            if(c<97){
                c+=32;
            }
            cout<<char(c)<<"\t"<<int(c-asc)<<"\t";
            int q_idx = c-asc; // the value of the character in query
            ptr = ptr->arr.at(q_idx);
            if(j==psz){
                if(ptr->caps == true){
                    return false;
                }
                continue;
            }
            //do this if j!=psz( there are still characters in j)
            int p_idx = this->pattern.at(j) - asc; //value of the character in the pattern
            cout<<char(this->pattern.at(j))<<"\t"<<p_idx<<endl;
            cout<<ptr->caps<<endl;
            if(q_idx == p_idx && ptr->caps == p_arr.at(j)){
                j++;
            }
            else if(ptr->caps == true){
                return false;
            }
        }
        if(j==psz) return true;
        return false;
    }

    void display(TrieNode* node,string prefix){
        if (node == NULL) return;
        if(node->flag){
            cout<<prefix<<endl;
        }
        for(int i{};i<arr_size;++i){
            if(node->arr.at(i) == NULL) continue;
            display(node->arr.at(i),prefix+char(i+asc));
        }
    }

};

class Solution {

public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        Trie Tree;
        Tree.make_tree(queries);
        //Tree.display(Tree.root,"");
        Tree.make_pattern_array(pattern);
        int sz = queries.size();
        vector<bool> res(sz,false);
        for(int i{};i<sz;++i){
            res.at(i) = Tree.find_pattern(queries.at(i));
            if(res.at(i)){
                cout<<"true";
            }
            else{
                cout<<"false";
            }
            cout<<endl;
        }
        return res;
    }
};

//use this without trie
/*
// Time Complexity : O(N*M)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
return false if the letter is caps and not equal to the pattern. 
if the pattern is over , the letter is caps return false;
*/

class Solution {
    bool match_pattern(string& query,string& pattern){
        int i{},j{};
        while(i<query.length() && j<pattern.length()){
            if(query.at(i) == pattern.at(j)){
                ++i;
                ++j;
            }
            else{
                if(isupper(query.at(i))){
                    return false;
                }
                i++;
            }
        }
        while(i<query.length()){
            if(isupper(query.at(i))){
                return false;
            }
            ++i;
        }
        return j==pattern.length();
    }
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        int qsz = queries.size();
        vector<bool> res(qsz,false);
        for(int i{};i<qsz;++i){
            res.at(i) = match_pattern(queries.at(i),pattern);
        }
        return res;
    }
};

