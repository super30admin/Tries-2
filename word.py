# Time: O(kn)
# Space: O(n)
# Did it run on Leetcode: yes

class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.startswith = []

    def insert(self, word):
        curr = self
        for i in range(len(word)):
            c=word[i]
            if curr.children[ord(c)-ord('a')] is None:
                curr.children[ord(c)-ord('a')] = Trie()
            curr = curr.children[ord(c)-ord('a')]
            curr.startswith.append(word)

    def search(self, prefix):
        curr = self
        for c in prefix:
            idx = ord(c) - ord('a')
            if curr.children[idx] is None:
                return []
            curr = curr.children[idx]
        return curr.startswith


class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
       res=[]
       li=[]
       root=Trie()
       def backtrack(root,li,l,res):
          sb=[]
          if(len(li)==k):
             res.append([li])
             return
          for word in lie:
            sb.append(word[len(li)])
            startwith=search(root,str(sb))
            for word in startswith:
                li.append(word)
                backtrack(root,li,k,res)
                li.pop()
        
       for word in words:
          insert(root,word)
        for word in words:
          li.append(word)
          backtrack(root,li,len(word), res)
          li.pop()
        return res
