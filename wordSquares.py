class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_word = False
        self.word_list = []
        
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    def add(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
                
            node = node.children[char]
            node.word_list.append(word)
        node.is_word = True
    
    def find(self, word):
        node = self.root
        for char in word:
            node = node.children.get(char)
            if node is None:
                return None
            
        return node
    
    def word_prefix(self, prefix):
        node = self.find(prefix)
        return [] if node is None else node.word_list
            
            

class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:        
        
        trie = Trie()
        
        for word in words:
            trie.add(word)
        
        results = []
        
        for word in words:
            self.search(trie, [word], results)
            
        return results
    
    def search(self, trie, square, results):
        
        n, pos = len(square[0]), len(square)
        
        if pos == n:
            results.append(list(square))
            return
        
        for col in range(pos, n):
            prefix = ''.join(square[i][col] for i in range(pos))
            if trie.find(prefix) is None:
                return
            
        prefix = ''.join(square[i][pos] for i in range(pos))
        for word in trie.word_prefix(prefix):
            square.append(word)
            self.search(trie, square, results)
            square.pop()