
# // Time Complexity :O(n), n is the length of stream
# // Space Complexity :O(1)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


class TrieNode:
    def __init__(self):
        self.children=[False for i in range(26)]
        self.isEnd=False
class StreamChecker:
    def insert(self,word):
        
        curr=self.root
        for i in range(len(word)-1,-1,-1):
            c=word[i]
            
            if not curr.children[ord(c)-ord('a')]:
                
                curr.children[ord(c)-ord('a')]=TrieNode()
            curr=curr.children[ord(c)-ord('a')]
        curr.isEnd=True
        

    def __init__(self, words: List[str]):
        self.root=TrieNode()
        self.sb=""
        for i in words:
            self.insert(i)
        

    def query(self, letter: str) -> bool:
        curr=self.root
        self.sb+=letter
        
        for i in range(len(self.sb)-1,-1,-1):
            c=self.sb[i]
            
            if not curr.children[ord(c)-ord('a')]:
            
                return False
            if curr.children[ord(c)-ord('a')].isEnd:
                
                return True
            curr=curr.children[ord(c)-ord('a')]
       
            
        


# Your StreamChecker object will be instantiated and called as such:
# obj = StreamChecker(words)
# param_1 = obj.query(letter)