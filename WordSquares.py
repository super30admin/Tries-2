#https://leetcode.com/problems/word-squares/
#Backtrack +Trie
#TC:O(N 
		P 
			l) l => length of word, N => length of word list 
#Backtracking leads to exponential TC, Permutation of all words in the worst case scenario
#SC:O(N*l) n => length of word list, l => length of word
class TrieNode:
    def __init__(self):
        self.prefix_list = []
        self.node = [None]*26
class Solution:     
    def buildTree(self,word, root):
        curr = root
        for w in word:
            if curr.node[ord(w)-ord('a')] is None:
                curr.node[ord(w)-ord('a')] = TrieNode()
            curr.prefix_list.append(word)
            curr = curr.node[ord(w)-ord('a')]
    
    def searchTree(self, prefix, root):
        curr = root
        for p in prefix:
            if curr.node[ord(p)-ord('a')]==None:
                return list()
            curr = curr.node[ord(p)-ord('a')]
        return curr.prefix_list

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        root = TrieNode()
        for word in words:
            self.buildTree(word, root)
        self.output = []
        wordlist = []
        for word in words:
            wordlist.append(word)
            self.backtrack(wordlist, root)
            wordlist.pop(len(wordlist)-1)

        return self.output
    
    def backtrack(self, wordlist, root):
        #base
        if len(wordlist) == len(wordlist[0]):
            self.output.append(wordlist[:])
            return
        #logic
        prefix = ""
        for word in wordlist:
            prefix = prefix + word[len(wordlist)]
        
        prefixList = self.searchTree(prefix, root)
        for prefixWord in prefixList:
            wordlist.append(prefixWord)
            self.backtrack(wordlist, root)
            wordlist.pop(len(wordlist)-1)