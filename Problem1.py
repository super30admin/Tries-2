class Solution:

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        '''
        # Time Complexity: O(N * M * M!), where N is the number of words, M is the length of each word, and M! is the maximum number of word squares that can be formed.The backtracking algorithm explores all possible word squares, and each square has M cells, and for each cell, there are at most N choices of words to fill it.
        # Space Complexity: O(N * M * M!), space used by the Trie for storing the words. Each TrieNode has M children (characters in the words), and there are N words, so the space complexity is O(N * M). Additionally, the result list can potentially store up to M! word squares, leading to an additional space factor of M!.
        '''
        # Initialize variables
        self.words = words
        self.N = len(words[0])
        self.buildTrie(self.words)

        # List to store the word squares
        results = []
        word_squares = []

        # Iterate through words and build word squares
        for word in words:
            word_squares = [word]
            self.backtracking(1, word_squares, results)

        return results

    def buildTrie(self, words):
        # Initialize the trie
        self.trie = {}

        # Build the trie by iterating through each word
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

    def backtracking(self, step, word_squares, results):
        if step == self.N:
            # When the square is complete, add it to results
            results.append(word_squares[:])
            return

        prefix = ''.join([word[step] for word in word_squares])

        # Find candidate words with the given prefix
        for candidate in self.getWordsWithPrefix(prefix):
            word_squares.append(candidate)
            self.backtracking(step + 1, word_squares, results)
            word_squares.pop()

    def getWordsWithPrefix(self, prefix):
        node = self.trie

        # Traverse the trie based on the prefix
        for char in prefix:
            if char not in node:
                return []
            node = node[char]

        # Retrieve words associated with the prefix
        return [self.words[wordIndex] for wordIndex in node['#']]
