425. Word Squares

Time complexity: O(N⋅26^L⋅L)where N is the number of input words and L is the length of a single word.

Space Complexity: O(N⋅L+N⋅L2)=O(N.L) where N is the number of words and L is the length of a single word.


class TrieNode {
public:
	TrieNode* child[26];
	bool isWord;
	TrieNode(): isWord(false) {
		for (int i = 0; i < 26; i++) {
			child[i] = nullptr;
		}
	}
};

class Trie {
public:
	TrieNode root;
	Trie() {}
	void addWord(string& s) {
		TrieNode* curr = &root;
		for (char c : s) {
			if (!curr->child[c-'a']) {
				curr->child[c-'a'] = new TrieNode();
			}
			curr = curr->child[c-'a'];
		}
		curr->isWord = true;
	}
	bool hasPrefix(string& s) {
		TrieNode* curr = &root;
		for (char c : s) {
			if (c == ' ') return true;
			if (!curr->child[c-'a']) {
				return false;
			}
			curr = curr->child[c-'a'];
		}
		return true;
	}
};

class Solution {
public:
	vector<vector<string>> wordSquares(vector<string>& words) {
		Trie t;
		for (auto& w : words) {
			t.addWord(w);
		}
		vector<vector<string>> ans;
		int len = words[0].length();
		vector<string> curr(len, string(len, ' '));
		// trie + DFS
		// state: current placed strings
		// when placing a string, check the current transpose on the trie
		search(words, t, ans, curr, 0);
		return ans;
	}
private:
	void search(vector<string>& words, Trie& t, vector<vector<string>>& ans, vector<string>& curr, int i) {
		if (i == curr.size()) {
			ans.push_back(curr);
			return;
		}
		for (auto& w : words) {
			if (match(w, curr[i])) {    // whether w can be placed at i-th row
				string ori = curr[i];
				curr[i] = w;
				bool ok = true;
				for (int j = i+1; j < curr.size() && ok; j++) { // check transpose
					curr[j][i] = w[j];
					if (!t.hasPrefix(curr[j])) {    // non-existing prefix -> can't place w here
						ok = false;
					}
				}
				if (ok) {   // keep searching
					search(words, t, ans, curr, i+1);
				}
				for (int j = i+1; j < curr.size(); j++) {   // recover the state
					curr[j][i] = ' ';
				}
				curr[i] = ori;
			}
		}
	}
	bool match(string& w, string& pre) {    // check whether pre is a prefix of w
		int n = pre.length();
		for (int i = 0; i < n; i++) {
			if (pre[i] == ' ') return true;
			if (w[i] != pre[i]) return false;
		}
		return true;
	}
};
