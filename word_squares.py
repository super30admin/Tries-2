// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
word square is a matrix formed by the given input which has the property of having the same string at the same row and column.
for instance:
ball
area
lend
lady
at row=0 and col=0, we have the same string ball.
at row=1 and col=1 we have the same string area.
at row=2 and col=2 we have the same string lend.
at row =3 and col=3 we have the same string lady.
we used trie+backtracking to solve this problem.
1)create a trie with the given input words by storing the word at each and every character.
2)for the given input words try to do backtracking by using given input one by one.
3)using the given inputs we create the prefix and check if they are present in the trie or not.if yes return the word stored at that particular character.
4)in this way we get all the required possibilites to make a word square.

#Time complexity --> o(n*L*(26**L)) where n is the length of the input sequence and L is the max length of the word given in the input.
#Space complexity --> o(n*L)
class TrieNode:
    #Implementing a trie node which has array children to store the character in the index and word1 to store the word at each and every character.
    def __init__(self):
        self.word1=[]
        self.children=[None for i in range(26)] 
class Solution:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root=TrieNode()
        #Intialised 0 at first and then changes it to length of the first word to limit the backtracking traversal.
        self.n=0
    #Building a trie i.e inserting a given string into the trie
    def buildtrie(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: None
        """
        root1=self.root
        for i in range(len(word)):
            index=ord(word[i])-ord('a')
            #If the character is new then we create a new trie node at that index
            if root1.children[index]==None:
                root1.children[index]=TrieNode()
                #storing  the given word at each and every character.
                root1.children[index].word1 = [word]
            else:
                #if already present appending the current word to the already present word.(storing the two words which has same prefix value)
                root1.children[index].word1.append(word)
            root1=root1.children[index]
    #function to check if a given prefix is present in the trie if yes return the stored word at the end of that prefix.
    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        root1=self.root
        for i in range(len(prefix)):
            index=ord(prefix[i])-ord('a')
            #If prefix is not present then return blank array.
            if root1.children[index]==None:
                return []
            #If present then go to the particular index and then return the word stored at the end of prefix .
            root1=root1.children[index]
        return root1.word1
    
    def backtrack(self,size,wordlist,out):
        #now consider the first value in wordlist as ["ball"]
        we have index as 1.
        #we have to check if any prefix has character 'a' as the start,we get it 'area'.now wordlist=["ball","area"],index=2.
        #Next we check if any prefix in the trie starts with 'le' (i.e. take the characters at index 2 in the wordlist which is 'le'.from trie we get the word as "lead".Now wordlist=["ball","area","lead"] and index=3.
        #Now we check in the trie which has 'lad'(at index 3 in the wordlist) as prefix in the trie.we get 'lady' from the trie.
        #Now one of the final list is ["ball","area","lead","lady"]
        if size==self.n:
            #used [:] to make a copy of the wordlist.becoz without it we make changes to the wordlist and the out will be ahving those changes.
            out.append(wordlist[:])
            return
        #for the above to get prefix 'le' from the worlist=["ball","area"],index=2, we use the below.
        prefix=''.join([word[size] for word in wordlist])
        for word in self.startsWith(prefix):
            #we add the word to the wordlist
            wordlist.append(word)
            self.backtrack(size+1,wordlist,out)
            #we pop the word from the wordlist to traverse all different ways
            wordlist.pop()
        
        
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        if words==None or len(words)==0:
            return []
        #given words are ["area","lead","wall","lady","ball"], we are creating trie using the given input.
        for i in range(len(words)):
            self.buildtrie(words[i])
        self.n=len(words[0])
        out=[]
        #each and every word in the input is considered as a intial value in the result and then used for backtracking.
        #for first iteration we start with ["area"] as the first value in the result and then check for remaining possibilites in the result if it satisfies to make the word squares or not.
        #for second iteration we start with ["lead"] and follow the same.
        for i in range(len(words)):
            self.backtrack(1,[words[i]],out)
        return out
        
        
        