// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
As per the given we have make the pattern to query by inserting as many lowercase characters as possible and if it can be made then return True else return False.
1)we take two pointers at the start of both query and pattern.
2)If both the characters at the particular indices in both query and pattern are the same then we increment both the index pointers for both query and pattern.
3)if the character in query is lower case and character in pattern is upper case then we increment the query pattern indicating it can be ignored.
4)when the character in query is upper case and the character in pattern is not upper case then we know that that upper case is not present in the pattern.then we return false.

# Time complexity --> o(nL) where n--> len(queries) and L--> len(queries[i])
# space complexity --> o(n)
class Solution(object):
    def match(self,query,pattern):
        #for instance query--> FooBar, pattern --> FoBa
        #case1: when the characters at a given index is same then we increment the pointers in query and pattern.
        #case2: if the character in query is lower case and character in pattern is upper case then we increment the query pattern indicating it can be ignored.
        #case 3: when the character in query is upper case and the character in pattern is not upper case then we know that that upper case is not present in the pattern.then we return false(in the quesiton it was given that we can insert as many lower case characters to make it query so that is why we return false in this case.)
        index_pattern=0
        for val in query:
            #FooBarT
            #FoBa  --> start at index 0 in both and value at these indices are the same.
            if index_pattern<len(pattern) and val==pattern[index_pattern]:
                index_pattern+=1
            elif val.isupper():
                return False
                #If the index in the pattern reaches the end of the pattern then it means the pattern can be made into the query by inserting or not inserting lower case characters.
        return index_pattern==len(pattern)
                
                
    
    def camelMatch(self, queries, pattern):
        """
        :type queries: List[str]
        :type pattern: str
        :rtype: List[bool]
        """
        res=[False for i in range(len(queries))]
        for i in range(len(queries)):
            #we check every query with a specific pattern.
            if self.match(queries[i],pattern):
                res[i]=True
        return res
        
        