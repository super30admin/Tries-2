

class Solution(object):
    def wordSquares(self, words):
        """
        :type words: List[str]
        :rtype: List[List[str]]
        """
        self.words = words
        self.N = len(words[0])
        self.buildTrie(self.words)

        res = []
        word_squares = []
        
        for word in words:
            word_squares = [word]
            self.backtracking(1, word_squares, res)
        return res
    
    # added a new function to build a Trie out of the input words.
    def buildTrie(self, words):
        self.trie = {}

        for wordIndex, word in enumerate(words):
            node = self.trie
            for char in word:
                if char in node:
                    node = node[char]
                else:
                    newNode = {}
                    newNode['#'] = []
                    node[char] = newNode
                    node = newNode
                node['#'].append(wordIndex)

    def backtracking(self, step, word_squares, res):
        if step == self.N:
            res.append(word_squares[:])
            return

        prefix = ''.join([word[step] for word in word_squares])
        for candidate in self.getWordsWithPrefix(prefix):
            word_squares.append(candidate)
            self.backtracking(step+1, word_squares, res)
            word_squares.pop()
    
    #query the Trie to retrieve all the words that possess the given prefix.
    def getWordsWithPrefix(self, prefix):
        node = self.trie
        for char in prefix:
            if char not in node:
                return []
            node = node[char]
        return [self.words[wordIndex] for wordIndex in node['#']]