# Word Squares

# Time Complexity : O(N.26 exponential L)
# Space Complexity : O(N⋅L) where N is the number of words and L is the length of a single word.
# Did this code successfully run on Leetcode : Yes, Runtime 260 ms and Memory 15.8 MB
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
# Approach
"""
For a valid word square to happen length of strings should be same. 
Backtracking is involved so after placing the first word information about
words starting with further characters/alphabets is checked and a trie data 
structure is maintained with augmneted strings.

"""
class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        childrens=[]
        trie={}
        def buildTrie():   # maintaining trie
            for word in words:
                for i in range(len(word) - 1):
                    prefix = word[:i+1] # a,ar,are,l,le,lea,w,wa,wal,....
                    #print(prefix)
                    if prefix in trie:
                        trie[prefix].append(word)
                    else:
                        trie[prefix] = [word]
                    #print(trie) a:[area],'are':[area],'l':[lead,lady],
        
        def starts_with(children):
            """Find the next candidates to fill in for the current square"""
            size_square = len(children)
            if size_square == length:
                childrens.append(children)
                return
                
            prefix = ''
            for word in children:
                prefix += word[size_square]
            if prefix not in trie:
                return
            
            for candidate in trie[prefix]:
                starts_with(children + [candidate])
        
        buildTrie()
        length = len(words[0])
        for word in words:
            children = [word]
            starts_with(children)
            #print(childrens) [],[],[['wall','area','lead','lady']]
        return childrens