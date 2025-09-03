TimeComplexity:O(N*k) k is avg lenght of ball
SpaceComplexity:O(N*k) k is avg lenght of ball
Approach:Brute would be try and check all combinations that would be o(n!xnxn) n! for genrating nxn would be for checking .
Consider Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
lets sat you fixed   B A L L
What are possibliyed in 2nd row ?if we need square
Anything that starts with A so using this and tries we can solve this
                                                                                                        


class Solution:
    class TrieNode:
        def __init__(self):
            # Dictionary to hold children (each key is a character)
            self.children = {}
            self.prefix=[]
            # Flag to mark end of word
            self.is_end_of_word = False
    class Trie:
        def __init__(self):
            self.root=Solution.TrieNode()
        def insert(self,s):
            node=self.root
            for char in s:
                if char in node.children:
                    node.prefix.append(s)
                    node=node.children[char]
                    
                else:
                    node.children[char]=Solution.TrieNode()
                    node.prefix.append(s)
                    node=node.children[char]
            node.is_end_of_word=True
        def searchPrefix(self,pre):
            node=self.root
            for char in pre :
                if char in node.children:
                    node=node.children[char]
                else:
                    return []
            return node.prefix



    def wordSquares(self, words: List[str]) -> List[List[str]]:
        ans=[]
        l=[]
        #fill trie
        troot=self.Trie()
        for word in words:
            troot.insert(word)
        
        def backtrack(l,troot,ans,sqlength):
            #base
                # words = ["area","lead","wall","lady","ball"]
                # [["ball","area",
                #need something with prefix le
            if len(l)==sqlength:
                ans.append(l[:])
                return 
            #logic
            x=len(l)
            pre=""

            for i in l:
                pre+=i[x]
            newL=troot.searchPrefix(pre)
            for word in newL:
                l.append(word)
                backtrack(l,troot,ans,sqlength)
                l.pop()
        sqlength=len(words[0])
        for word in words:
            #action
            l.append(word)
            #recurse
            backtrack(l,troot,ans,sqlength)
            #backtrack
            l.pop()
        return ans




        


        
