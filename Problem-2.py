#Approach
#make 2 pointers one for pattern and one for query. check whetrher the two strings follow the pattern or not



#Complexities
#Time(O(L*Pl)
#Space O(1)

from typing import List


class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:

        result = []

        for query in queries:
            j = 0
            i = 0
            while j < len(pattern) and i<len(query):
                if pattern[j].isupper():
                    if not query[i].isupper():
                        i += 1
                    else:
                        if query[i] == pattern[j]:
                            i += 1
                            j += 1
                        else:
                            result.append(False)
                            break
                else:
                    if query[i] == pattern[j]:
                        i += 1
                        j += 1
                    else:
                        i+=1
            if j<len(pattern) and i == len(query):
                result.append(False)

            if j == len(pattern) :
                flag =0
                if i<len(query):
                    for j in range(i+1,len(query)):
                        if query[j].isupper():
                            flag=1
                            break
                if flag==1:
                    result.append(False)
                else:
                    result.append(True)

        return result


s =Solution()
print(s.camelMatch(["CompetitiveProgramming","CounterPick","ControlPanel"],"CooP"))