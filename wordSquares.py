class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        self.words = words
        self.length = len(words[0])
        result = []
        self.buildHashMap(words)
        self.buildTrieMap(words)
        for word in words:
            wordList = [word]
            self.backtrack(wordList, 1, result)
        return result

    #brute force 
    def findPrefix(self, prefix):
        for word in self.words:
            if word.startswith(prefix):
                yield word
            
    #hashmap solution
    def buildHashMap(self, words):
        self.hashmap = defaultdict(list)
        for word in words:
            for prefix in [word[:i] for i in range(1, len(words[0])) ]:
                self.hashmap[prefix].append(word)
                
    #trie solution
    def buildTrieMap(self, words):
        self.trie = defaultdict(list)
        for word in words:
            start = self.trie
            for letter in word:
                if letter in start:
                    start = start[letter]
                else:
                    newNode = defaultdict(list)
                    newNode["words"] = []
                    start[letter] = newNode
                    start = start[letter]
                start["words"].append(word)
                
    def findPrefixFromTrie(self, word):
        start = self.trie
        for letter in word:
            if letter not in start:
                return []
            else:
                start = start[letter]
        
        return [word for word in start["words"]]
    
    #plain old backtracking
    def backtrack(self, wordList, stepIndex, result):
        #base condition
        if len(wordList) == self.length:
            result.append(wordList[:])
        else:
            
            prefix = "".join([word[stepIndex] for word in wordList])
            for word in self.findPrefixFromTrie(prefix):
                self.backtrack(wordList+[word], stepIndex+1, result)
        return result
                
Time: N*26^L for Trie and N*26^L for Hashmap
Space: NL
            
        
