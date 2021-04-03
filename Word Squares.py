class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        #Approach: Backtracking with Trie
        #Time Complexity: O(nPk)
        #Space Complexity: O(n^2 * k)
        #where, n is the number of words and k is the length of a word/sequence
        
        root = self.buildTree(words)
        self.result = []
        self.backtrack(root, [], len(words[0]))
        return self.result
        
    def buildTree(self, words):
        root = TrieNode()
        for word in words:
            curr = root
            for char in word:
                idx = ord(char) - ord('a')
                if not curr.children[idx]:
                    curr.children[idx] = TrieNode()
                curr.startsWith.append(word)
                curr = curr.children[idx]
        return root
                
    def search(self, root, prefix):
        curr = root
        for char in prefix:
            idx = ord(char) - ord('a')
            if not curr.children[idx]:
                return []
            curr = curr.children[idx]
        return curr.startsWith
    
    def backtrack(self, root, seq, length):
        #base
        if len(seq) == length:
            self.result.append(seq.copy())
            return
        
        #logic
        prefix = []
        for word in seq:
            prefix.append(word[len(seq)])
        startsWith = self.search(root, prefix)
        
        for word in startsWith:
            seq.append(word)                        #action
            self.backtrack(root, seq, length)       #recursion
            seq.pop()                               #backtrack
    
class TrieNode:
    def __init__(self):
        self.children = [None for i in range(26)]
        self.startsWith = []