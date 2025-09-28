'''
425 Word Squares
https://leetcode.com/problems/word-squares/description/

Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

Example 1:
Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Example 2:
Input: words = ["abat","baba","atan","atal"]
Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 4
All words[i] have the same length.
words[i] consists of only lowercase English letters.
All words[i] are unique.

Solution:
1. Trie + Backtracking - 1
Build a Trie where each node represents a char and additionally holds a list words starting with that prefix. The list of words with that prefix is only for speed up.

Try every word as the first row and build column prefixes as you go.

Use backtracking to explore valid paths; stop when square size equals word length.

Time: O(N ^ K) where N is the number of input words and K is the avg length of each word.
Space: O(N^2 * K^2) - N is the number of input words and K is the avg length of each word.

This gives TLE (Time Limit Exceeded) error! (I created solution #1 first before going to solution #2 which was done in the lecture)

2. Trie + Backtracking - 2
Very similar to solution # 1. The only difference is in the backtracking step.
Here, we backtrack only over those words which have the target prefix. This is faster than solution #1.

In solution #1, we run a for loop over all N input words but only recurse on those words which contain the target prefix. But the for loop runs over N input words which is time consuming (hence, TLE error).

https://youtu.be/nF2IWTtdKjQ?t=352

Time: O(M ^ K) where M is the number of words that start with a prefix (constructed from the word square building up) and K is the avg length of each word.
Space: O(N^2 * K^2) - N is the number of input words and K is the avg length of each word.
'''
from collections import defaultdict

class TrieNode:
    def __init__(self):
            self.children = defaultdict(TrieNode)
            self.startswith = [] # words which start with prefix
            self.isEOS = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        ''' T: O(W), S: O(W), W = length of word = num chars in word '''
        if not self.root:
            return None
        curr = self.root
        for c in word:
            if not c in curr.children:
                curr.children[c] = TrieNode()
            curr.children[c].startswith.append(word)
            curr = curr.children[c]
        curr.isEOS = True

    def startsWith(self, prefix):
        ''' T: O(W), S: O(1)'''
        if not self.root:
            return []
        curr = self.root
        for c in prefix:
            if not c in curr.children:
                return []
            curr = curr.children[c]
        # return the list of words which start with the prefix
        return curr.startswith

def wordSquares_1(words):
    '''
    Time: O(N ^ K) where N is the number of input words and K is the avg length of each word.
    Space: O(N^2 * K^2) - N is the number of input words and K is the avg length of each word.
    '''
    def is_word_square(trie, path):
        N = len(path) # no. of words in the list 'path'

        if N == 0:
            return False
        elif N == 1:
            return True

        # if N > 1: build the prefix
        # eg. path = [ball,area], prefix = a (N=2, path[0][1])
        # eg. path = [ball,area,lead], prefix = le (N=3, path[0][2], path[1][2])
        prefix = ""
        for i in range(N-1):
            word = path[i]
            prefix += word[N-1]

        target_word = path[-1]
        # get all the words that start with the prefix
        all_words = trie.startsWith(prefix)
        for word in all_words:
            if word == target_word:
                return True
        return False

    def recurse(words, i, path):
        N = len(words)
        if i == K: # size of list of words == length of any word
            result.append(path.copy())
            return

        # --- backtrack ---
        for j in range(N):
            path.append(words[j])
            if is_word_square(trie, path):
                recurse(words, i+1, path)
            path.pop()
        # ------

    K = len(words[0])
    trie = Trie()
    for word in words:
        trie.insert(word)

    result = []
    recurse(words, 0, [])
    return result

def wordSquares_2(words):
    '''
    Time: O(M ^ K) where M is the number of words that start with a prefix (constructed from the word square building up) and K is the avg length of each word.
    Space: O(N^2 * K^2) - N is the number of input words and K is the avg length of each word.
    '''
    def recurse(words, path):
        N = len(path) # N >= 1

        if len(path) == K: # size of list of words == length of any word
            result.append(path.copy())
            return

        # build the prefix from the list of words in path

        # eg. path = [ball], prefix = a
        # (N=1, prefix = path[0][1])
        # This means next word should start with a

        # eg. path = [ball,area], prefix = le
        # (N=2, prefix = path[0][2] + path[1][2])
        # This means next word should start with le

        # eg. path = [ball,area,lead], prefix = lea
        # (N=3, prefix = path[0][3] + path[1][3] + path[2][3])
        # This means next word should start with lea

        prefix = ""
        for i in range(N):
            word = path[i]
            prefix += word[N]

        # get all the words that start with the prefix
        all_words = trie.startsWith(prefix)

        # --- backtrack ---
        # try each word as the next possible word in the word square
        for word in all_words:
            path.append(word)
            recurse(words, path)
            path.pop()
        # ------

    K = len(words[0])
    trie = Trie()
    for word in words:
        trie.insert(word)

    result = []
    path = []
    for word in words:
        path.append(word)
        recurse(words, path)
        path.pop()
    return result

def run_wordSquares():
    tests = [(["area","lead","wall","lady","ball"],
              [["wall","area","lead","lady"], ["ball","area","lead","lady"]]),

             (["abat","baba","atan","atal"],
              [['baba', 'abat', 'baba', 'atan'], ['baba', 'abat', 'baba', 'atal']]),

              (["momy","oooo","yoyo"],
               [["momy","oooo","momy","yoyo"],["oooo","oooo","oooo","oooo"],["yoyo","oooo","yoyo","oooo"]]),
    ]

    for test in tests:
        words, ans = test[0], test[1]
        print(f"\nwords = {words}")
        for method in [1,2]:
            if method == 1:
                result = wordSquares_1(words)
            elif method == 2:
                result = wordSquares_2(words)
            print(f"Method {method}: word squares = {result}")
            success = (ans == result)
            print(f"Pass: {success}")
            if not success:
                print(f"Failed")
                return

run_wordSquares()
