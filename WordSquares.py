'''
Solution:
1.  Firstly, for each word, perform backtracking.
2.  In backtracking, generate prefix for current node in recursive tree.
3.  Search for prefix in words array [... costly search as it takes O(n * p) n - no. of words and p - avg. prefix size;
    we have to reduce this costly operation using a Trie which makes it to O(p)]
4.  For all valid prefixes, perform backtracking.

Time Complexity:    O(L ^ n) where n - no. of words and L - length of a row in word square
Space Complexity:   O(H * L * L) where H is the Height of Recursive Stack + O(n) for Trie [this is max possible length]
                    => O(H * (L^2)) if Trie is used.

--- Passed all test cases on leetcode if Trie is used and TLE if only backtracking.
'''


class OnlyBackTracking:

    def __backtracking(self, currentIndex, wordSquares, finalSquares):

        #   base case
        if (currentIndex == self.length):
            finalSquares.append(list(wordSquares))
            return

        #   generate prefix for current node in recursive tree
        prefix = []
        for word in wordSquares:
            prefix.append(word[currentIndex])
        prefix = ''.join(prefix)

        #   search for prefix in words array [... costly search as it takes O(n * p) n - no. of words and
        #   p - avg. prefix size; we have to reduce this costly operation using a Trie]

        for nextWord in self.words:

            #   perform backtracking if prefix is a match
            if (nextWord.startswith(prefix)):
                wordSquares.append(nextWord)
                self.__backtracking(currentIndex + 1, wordSquares, finalSquares)
                wordSquares.pop()

        return

    def wordSquares(self, words: List[str]) -> List[List[str]]:

        #   initializations
        self.length = len(words[0])
        self.words = words
        finalSquares = []
        wordSquares = []

        #   for each word, perform backtracking
        for word in words:
            wordSquares = [word]
            self.__backtracking(1, wordSquares, finalSquares)

        #   return the final resultant array
        return finalSquares


class BackTrackingWithTrie:

    def __backtracking(self, currentIndex, wordSquares, finalSquares):

        #   base case
        if (currentIndex == self.length):
            finalSquares.append(list(wordSquares))
            return

        #   generate prefix for current node in recursive tree
        prefix = []
        for word in wordSquares:
            prefix.append(word[currentIndex])
        prefix = ''.join(prefix)

        #   cost reduced from O(n * p) to O(p) using a Trie
        wordsWithPrefix = self.__getWordsWithPrefix(prefix)

        #   perform backtracking
        for nextWord in wordsWithPrefix:
            wordSquares.append(nextWord)
            self.__backtracking(currentIndex + 1, wordSquares, finalSquares)
            wordSquares.pop()

        return

    def __getWordsWithPrefix(self, prefix):

        #   get all words with the current prefix using word indices
        currentNode = self.trie

        for currentChar in prefix:
            if (currentChar not in currentNode):
                return []
            currentNode = currentNode[currentChar]

        return [self.words[wordIndex] for wordIndex in currentNode['prefixWords']]

    def __buildTrie(self, words):

        #   for each word
        for wordIndex in range(len(words)):

            #   cursor
            currentNode = self.trie

            #   for each char
            for currentChar in words[wordIndex]:

                #   if char not present in Trie => create a new Trie Node
                if (currentChar not in currentNode):
                    newNode = {}
                    newNode['prefixWords'] = []
                    currentNode[currentChar] = newNode

                #   move cursor accordingly
                currentNode = currentNode[currentChar]
                currentNode['prefixWords'].append(wordIndex)

    def wordSquares(self, words: List[str]) -> List[List[str]]:

        #   initializations
        self.length = len(words[0])
        self.words = words
        self.trie = {}
        self.__buildTrie(words)

        finalSquares = []
        wordSquares = []

        #   for each word, perform backtracking, here with a Trie on top
        for word in words:
            wordSquares = [word]
            self.__backtracking(1, wordSquares, finalSquares)

        #   return final result
        return finalSquares