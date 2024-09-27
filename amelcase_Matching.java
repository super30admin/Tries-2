class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        
        for(String query: queries){
                boolean flag = false;
                int i = 0;
                for(int j = 0;j < query.length(); j++ ){
                        char c = query.charAt(j);
                        if(i < pattern.length() && c == pattern.charAt(i)){
                            i++;
                            if(i == pattern.length()){
                                flag = true;
                            }
                        }else if(Character.isUpperCase(c)){
                            flag = false;
                            break;
                        }
                }
            result.add(flag);
        }
        return result;
    }
}
//TC: O(m+n)
//SC: O(m)
