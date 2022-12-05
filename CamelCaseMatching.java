class Solution {
    //tc-o(nk) sc-o(1)
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        
        for(String query : queries)
        {
            boolean flag = false;
            int p1=0;
            for(int i=0;i<query.length();i++)
            {
                if(p1<pattern.length() && query.charAt(i) == pattern.charAt(p1))
                {
                    p1++;
                    if(p1 == pattern.length())
                    {
                        flag = true;
                    }
                }
                else if(Character.isUpperCase(query.charAt(i)))
                {
                    flag = false;
                    break;
                }

            }
            result.add(flag);
        }
        return result;
    }
}