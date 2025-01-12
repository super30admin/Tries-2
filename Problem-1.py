#Approach
# create a trie and itertare through the list of words and form the search string and search in trie and form the word square

#Complexity
#Time : O(n*l) trie + n*L
#Space: O(N*l)*O(N*L)


class Trie:
    def __init__(self):
        self.dictionary = [None]*26
        self.wordSet = set()

class Solution:

    def wordSquares(self,words):
        self.root = Trie()
        self.createTrie(words)
        self.result = []
        path  = []
        for word in words:
            path.append(word)
            self.dfs(words,path)
            path.pop(-1)
        return self.result

    def dfs(self,words,path):
        if len(path)==4:
            self.result.append(path.copy())
            return

        #formthestring
        searchString = ""
        for i in range(len(path)):
            searchString+=path[i][len(path)]

        trieStartswith = self.searchTrie(searchString)
        if len(trieStartswith)==0:
            return
        else:
            for word in trieStartswith:
                path.append(word)
                self.dfs(words,path)
                path.pop(-1)
        return
    def createTrie(self,words):
        for word in words:
            temp = self.root
            for char in word:
                if temp.dictionary[ord(char)-ord("a")] == None:
                    node = Trie()
                    temp.dictionary[ord(char) - ord("a")] = node
                temp = temp.dictionary[ord(char)-ord("a")]
                temp.wordSet.add(word)

    def searchTrie(self,word):
        result = []
        temp = self.root
        for char in word:
            if temp.dictionary[ord(char)-ord("a")] is None:
                return result
            temp = temp.dictionary[ord(char) - ord("a")]
        return list(temp.wordSet)


s = Solution()
print(s.wordSquares(["area","lead","wall","lady","ball"]))
print(s.wordSquares(["abat","baba","atan","atal"]))
