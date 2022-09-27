// Time: O(n^2)
// Space: O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query: queries){
            boolean flag = false;
            int p1 = 0;
            for(int i = 0; i<query.length(); i++){
                char qChar = query.charAt(i);
                if (p1 < pattern.length() && qChar == pattern.charAt(p1)){
                    p1++;
                    if(p1 == pattern.length()){
                        flag = true;
                    }
                }else if (Character.isUpperCase(qChar)){
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
