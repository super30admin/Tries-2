# Time complexity : O(n^k) --> n - number of words, k - length of any word
# Space complexity : O(n*k)
# Leetcode : Solved and submitted

# Defining a TrieNode with children having 26 chars and a list to store the words at that point
class TrieNode(object):
    def __init__(self):
        self.children = [None]*26
        self.startwith = []
        
class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        # create a root node and empty res
        self.root = TrieNode()
        self.res = []
        
        # insert the words into the Trie
        for word in words:
            self.insert(word)
        
        # start the path for backtracking
        temp = []
        
        # traverse over each word
        for word in words:
            # action - append the word into temp
            temp.append(word)
            # recurse - call the backtrack function
            self.backtrack(temp, words)
            # backtrack - remove the last added word in temp
            temp.pop()
        
        # return the result
        return self.res
    
    # Function to insert the words into Trie
    def insert(self, word):
        curr = self.root
        for i in range(len(word)):
            ch = word[i]
            if curr.children[ord(ch) - ord('a')] is None:
                curr.children[ord(ch) - ord('a')] = TrieNode()
            curr = curr.children[ord(ch) - ord('a')]
            curr.startwith.append(word)
    
    # Function to search for a prefix in Trie
    def searchPrefix(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            ch = prefix[i]
            if curr.children[ord(ch) - ord('a')] is None:
                return []
            curr = curr.children[ord(ch) - ord('a')]
        # return the list with all the matching prefix
        return curr.startwith
    
    # Keep checking for matching words
    def backtrack(self, temp, words):
        # if we have formed a square of words, then add the path to the result and return
        if len(temp) == len(words[0]):
            self.res.append(temp[:])
            return
        
        # start a pre with empty and idx is the len of temp which would be the next word
        pre = ''
        idx = len(temp)
        
        # form a pre
        for word in temp:
            pre += word[idx]
            
        # search for prefix in Trie and fetch the matching words
        start_with = self.searchPrefix(pre)
        
        # traverse over the list formed and do backtracking on that
        for word in start_with:
            # action - append the word to temp
            temp.append(word)
            # recurse - do backtracking on the current word
            self.backtrack(temp, words)
            # backtrack - remove the last added word from the path
            temp.pop()
