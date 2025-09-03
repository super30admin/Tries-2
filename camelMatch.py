# TimeComplexity:O(N*K*P)
# SpaceCompelxity:O(1)
# Approach: Use two pointers for each quireie
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        """
        have two pointer on quieries and patterns
        and check f capital if yes compare
        """
        mx=ord('Z')
        mn=ord('A')
        lq,lp=0,0
        
        ans=[]
        for q in queries:
            j=0
            comp= False
            k=True
            for i in range(len(q)):
                # print(q,pattern,i,j)
                if not comp and  q[i]==pattern[j]:
                    j+=1
                    if j==len(pattern):
                        comp=True
                elif (comp and mn<=ord(q[i])<=mx) :
                    k=False
                    
                    break
                elif (not comp and q[i]!=pattern[j] and mn<=ord(q[i])<=mx) :
                    k=False
                    
                    break
                # elif not comp and (q[i]!=pattern[j] and ord("a")<=ord(pattern[j])<=ord("z")) :
                #     k=False
                    
                #     break
                
            if j<len(pattern):
                k=False
            ans.append(k)
        return ans


        
            

                
