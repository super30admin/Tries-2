# // Time Complexity :O(m*n*26^n)
# // Space Complexity :O(m*n), words ad length of each word , tobuild trie
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no
import copy
class TrieNode:
    def __init__(self):
        self.children=[None for i in range(26)]
        self.li=[]
class Solution:
    def __init__(self):
        self.root=TrieNode()
        self.result=[]
    def insert(self,word):
        curr=self.root
        for i in word:
            if not curr.children[ord(i)-ord('a')]:
                curr.children[ord(i)-ord('a')]=TrieNode()
            curr=curr.children[ord(i)-ord('a')]
            curr.li.append(word)
    def search(self,prefix):
        curr=self.root
        for i in prefix:
            if not curr.children[ord(i)-ord('a')]:
                return []
            curr=curr.children[ord(i)-ord('a')]
        return curr.li


    def calc(self,words):
        for i in words:
            self.insert(i)
        temp=[]
        for i in words:
            temp.append(i)
            self.backtrack(words,temp)
            temp.pop()
        return self.result
    def backtrack(self,words,temp):
        
        #base
        
        if len(temp)==len(words[0]):
            
            self.result.append(copy.copy(temp))
            
            return
            
        #logic
        i=len(temp)
        prefix=""
        for j in temp:
            
            prefix=prefix+j[i]
        
        wordwithpre=self.search(prefix)
        
        for i in wordwithpre:
            temp.append(i)
            self.backtrack(words,temp)
            temp.pop()

        



        




obj=Solution()
print(obj.calc(["leap","eyes","lead","area","wall","ball","lady","land","andy","rear"]))