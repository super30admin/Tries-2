class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.string = []
        
class Solution:
    def __init__(self):
        self.root = TrieNode()
        
    def insertWord(self, word):
        curr = self.root
        for i in range(len(word)):
            index = ord(word[i]) - ord('a')
            if curr.children[index] == None:
                curr.children[index] = TrieNode()
            curr.children[index].string.append(word)
            curr = curr.children[index]
            
    def search(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            index = ord(prefix[i]) - ord('a')
            if curr.children[index] == None:
                return []
            curr = curr.children[index]
        return curr.string
    
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        self.result = []
        for word in words:
            self.insertWord(word)
            
        li = []    
        for word in words:
            li.append(word)
            self.backtrack(li, len(word))
            li.pop()
        return self.result
    
    def backtrack(self, li, l):
        if len(li) == l:
            self.result.append(li.copy())
            return
        
        idx = len(li)
        searchString = ''
        for word in li:
            searchString += word[idx]
        newli = self.search(searchString)
        
        for word in newli:
            li.append(word)
            self.backtrack(li, len(word))
            li.pop()

# Time Complexity : Exponential
# Space Complexity : Reduced due to trie
            

            
            
    
            
            
        