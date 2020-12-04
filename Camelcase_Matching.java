//Time Complexity-O(k(n+m)) where k is the length of queries and n and m are the length of query and pattern
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean>result=new ArrayList();
        for(String query:queries)
        {
            int i=0;
            boolean flag=false;
            for(int j=0;j<query.length();j++)
            {
                if(i<pattern.length()&&pattern.charAt(i)==query.charAt(j))
                {
                    i++;
                    if(i==pattern.length())
                    {
                    flag=true;
                    }
                }
                else if(Character.isUpperCase(query.charAt(j)))
                {
                    flag=false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}