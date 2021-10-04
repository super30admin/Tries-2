#Time Complexity :  O(N*L*(26^L))
#Space Complexity: O(NL) where N is the number of words and L is the length of a single word.

class TrieNode():
    def __init__(self):
        self.children = [False]*26
        self.prefixwords = []
class Solution:
    def buildtrie(self,words):
        root = TrieNode()
        trie = root
        for word in words:
            cur = trie
            for index,ch in enumerate(word):
                w_index = ord(ch)- ord('a')
                if not cur.children[w_index]:
                    cur.children[w_index] = TrieNode()
                
                cur = cur.children[w_index]
                cur.prefixwords.append(word)
                
        return trie
                
    def search_prefix_words(self,prefix,trie):
        cur = trie
        for ch in prefix:
            wi = ord(ch)-ord('a')
            if not cur.children[wi]:
                return []
            cur = cur.children[wi]
        return cur.prefixwords
        
        
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        self.result = []
        if not words or len(words)==0:
            return [self.result]
        
        
        tr = self.buildtrie(words)
        n = len(words[0])
        
        def backtrack(wlist,n):
            #base
            if len(wlist)==n:
                self.result.append(list(wlist))
                return
            #logic
            index  = len(wlist)
            pre = ''
            for word in wlist:
                pre+=word[index]
            pre_list = self.search_prefix_words(pre,tr)
            for word in pre_list:
                wlist.append(word)
                backtrack(wlist,n)
                wlist.pop()
        path = []
        for index,word in enumerate(words):
            #action
            path.append(word)
            #recurse
            backtrack(path,n)
            #backtrack
            path.pop()
        return self.result