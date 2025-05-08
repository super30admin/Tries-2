# Problem 1 : Word Squares
# Time Complexity : O(N * L) where N is the number of words in words list and L is the average length of the word
# Space Complexity : O(L) where L is the average length of the word
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# Your code here along with comments explaining your approach

from typing import List

# define TriNode class
class TrieNode:
    def __init__(self):
        # define children array which will alphabet
        self.children = [None] * 26
        # define startsWith array which will store list of words which start the prefix
        self.startsWith = []

class Solution:
    # insert function to insert the word in the Trie 
    def insert(self, word: str, root: TrieNode) -> None:
        # set the curr to the root node at Trie
        curr = root
        # loop through each character in word
        for ch in word:
            # get the index of the character 
            index = ord(ch) - ord('a')
            # check if the current node does not have children for the index
            if not curr.children[index]:
                # if it it true then create a TriNode for the index add as child to curr
                curr.children[index] = TrieNode()
            # move the curr to the index child node in the Trie
            curr = curr.children[index]
            # add the word in the list of startsWith for the curr
            curr.startsWith.append(word)
    
    # search function to get the list of words for the prefix
    def search(self, root: TrieNode, prefix: str) -> List[str]:
        # set the curr to the root node at Trie
        curr = root
        # loop through each character in prefix
        for ch in prefix:
            # get the index of the character 
            index = ord(ch) - ord('a')
            # check if the current node does not have children for the index
            if not curr.children[index]:
                # if it is then return empty list
                return []
            # move the curr to the index child node in the Trie
            curr = curr.children[index]
        # return the list of the words which is stored in startsWith
        return curr.startsWith

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        # define result array which will store the all word squares
        result = []
        # define root of the Trie
        root = TrieNode()
        # loop through each word in words list
        for word in words:
            # insert the word in the Trie
            self.insert(word, root)
        
        # backtrack function to check the path
        def backtrack(path: List[str]) -> None:
            # base case 
            # if the length of the path is equal to length of word at 0th position in words then append path to result return
            if len(path) == len(words[0]):
                result.append(list(path))
                return
            # get the string by concatenating the character at index[len(path)] from each word in the list path
            prefix = ''.join(word[len(path)] for word in path)
            # get the list of word by calling search for the prefix
            listWords = self.search(root, prefix)
            # loop through the listWords
            for nextWord in listWords:
                # append the next word
                path.append(nextWord)
                # call backtrack function for the path
                backtrack(path)
                # remove the last added word in the path
                path.pop()
        # loop through words list
        for word in words:
            # call backtrack function for each word
            backtrack([word])
        # return result 
        return result
