# Time Complexity : O(NL)
# Space Complexity : O(NL) - Trie
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class TrieNode:
    def __init__(self):
        self.children = defaultdict(TrieNode)
        
class Solution:
    def add_word(self,pattern):
        node = self.root
        for i in pattern:
            if i not in node.children:
                node.children[i] = TrieNode()
            node = node.children[i]
        
    def search_word(self,word):
        node = self.root
        for i in word:
            if i not in node.children:
                if i.isupper():
                    return False
            else:
                node = node.children[i]
        if len(node.children) == 0:
            return True
        return False
        
    def camelMatch(self, queries, pattern):
        self.root = TrieNode()
        self.add_word(pattern)

        ans = []
        for q in queries:
            ans.append(self.search_word(q))
        return ans