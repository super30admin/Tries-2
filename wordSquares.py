'''
Time Complexity: 0(n*(m+k)) 
                n -- no of words in list
                m -- len of pattern word
                k -- avg len of words in list
Space Complexity: 0(1)
Run on LeetCode: Yes
'''
# create tries
class TrieNode:
    def __init__(self):
        self.triesList = [None]*26 # for 26 alphabet characters
        self.recommendList = []

class Tries:
    def __init__(self):
        self.root = None
    
    def insert(self,word):
        
        # base-case
        if self.root == None:
            # create an objNewNode of TrieNode
            objNewNode = TrieNode()
            self.root = objNewNode
        
        # assign currentNode rfr to the root
        currentNode = self.root
        
        # iterate the words
        for char in word:
            
            # get the ascii value
            index = ord(char)-ord('a')
            
            # chk the availability
            if currentNode.triesList[index] == None:
                # create an objNewNode
                objNewNode = TrieNode()
                # assign it at the index position
                currentNode.triesList[index] = objNewNode
                # add the word inside the recommendList
                currentNode.triesList[index].recommendList.append(word)
                # update the currentNode rfr
                currentNode = currentNode.triesList[index]
                
            # chk if not available
            elif currentNode.triesList[index] != None:
                # its already occupied
                # add to the set
                currentNode.triesList[index].recommendList.append(word)
                # update the currentNode rfr
                currentNode = currentNode.triesList[index]
        
        '''end of for loop'''
    '''end of insert function'''
    
    def prefixSearch(self,prefix):
        
        # base-case
        if self.root == None:
            return []
        
        # create currentNode rfr to the root
        currentNode = self.root
        
        # iterate the prefix to reach the respective node
        for char in prefix:
            
            # get the ascii value
            index = ord(char)-ord('a')
            
            # chk the availability
            if currentNode.triesList[index] == None:
                # not a valid available recommend list
                return []
            
            # chk if not available
            elif currentNode.triesList[index] != None:
                # update the currentNode rfr
                currentNode = currentNode.triesList[index]
        '''end of for loop iteration'''
        
        # return the currentNode recommend list
        return currentNode.recommendList
        
class Solution:
    
    def __init__(self):
        # obj of class Tries
        self.tries = Tries()
        # result list
        self.resultList = []
    
    
    def recurse(self,words,wordSquareList):
        # base-case
        if len(wordSquareList) == len(words[0]):
            self.resultList.append(wordSquareList[:])
            return
        
        # logic-case
        
        # 1. build prefix
        prefix = ''
        for i in range(0,len(wordSquareList)):
            prefix += wordSquareList[i][len(wordSquareList)]
        
        # 2. search in tries
        resultList = self.tries.prefixSearch(prefix)
        
        # 3. iterate the resultSet
        for i in range(0,len(resultList)):
            # action 
            wordSquareList.append(resultList[i])
            # recurse
            self.recurse(words,wordSquareList)
            # backtrack
            wordSquareList.pop()
        
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        
        # 1. convert the list of words into Tries
        for word in words:
            self.tries.insert(word)
        
        # 2. get the wordSquares
        wordSquareList = []
        for i in range(0,len(words)):
            # action
            wordSquareList.append(words[i])
            # recurse
            self.recurse(words,wordSquareList)
            # backtrack
            wordSquareList.pop()
        
        # 3. print the resultList
        # print('ResultList is:\t',self.resultList)
        return self.resultList