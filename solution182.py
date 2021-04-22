#Time Complexity:O(n!/(n-k)!)
#Space Complexity:O(mn)
#Approach : Use tries to keep track  of the prefixes of the word. For each word in the words list, create combinantions of word squares using the
#next letter of the word. Using the letter as prefix search in the trie node for same letter prefixed words and try various combinations using backtracking
class Solution:
    class TrieNode:
        def __init__(self):
            self.children=[None]*26
            self.startsWith=[]
        
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        self.result=[]
        if not words:
            return result
        root=self.buildTrie(words)
        li=[]
        for word in words:
            li.append(word)
            self.backtrack(li,root,len(word))
            li.pop()
        return self.result
    
    def backtrack(self,li:str,root:TrieNode,length:int)->None:
        if len(li)==length:
            self.result.append(li.copy())
            return
        s=''
        i=len(li)
        for word in li:
            s+=word[i]
        startsWithList=self.startsWith(s,root)
        for word in startsWithList:
            li.append(word)
            self.backtrack(li,root,length)
            li.pop()
        
        
    def buildTrie(self,words:str)->TrieNode:
        root=self.TrieNode()
        for word in words:
            curr=root
            for i in range(len(word)):
                c=word[i]
                if not curr.children[ord(c)-ord('a')]:
                    curr.children[ord(c)-ord('a')]=self.TrieNode()
                curr=curr.children[ord(c)-ord('a')]
                curr.startsWith.append(word)
        return root
    
    def startsWith(self,prefix:str,root:TrieNode)->List[str]:
        curr=root
        for i in range(len(prefix)):
            c=prefix[i]
            if not curr.children[ord(c)-ord('a')]:
                return []
            curr=curr.children[ord(c)-ord('a')]
        return curr.startsWith