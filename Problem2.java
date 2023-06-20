//Time -> O(m + n)
//Space -> O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        int pl = pattern.length();
        for(String query: queries){
            int ql = query.length();
            boolean flag = false;
            int p1 = 0;
            for(int i = 0; i < ql; i++){
                 char q = query.charAt(i);
                 if(p1 < pl && q == pattern.charAt(p1)){
                    p1++;
                    if(p1 == pl)
                        flag = true;
                 }
                 else if(Character.isUpperCase(q)){
                     flag = false;
                     break;
                 }
            }
            result.add(flag);
    }
    return result;
    }
}
