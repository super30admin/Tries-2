//Time - O(m+n)*k
//Space - O(1)
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query: queries){
        Boolean flag = false;
        int pl=0;
            for(int i=0; i<query.length(); i++){
                if(pl <pattern.length() && pattern.charAt(pl) == query.charAt(i)){
                    pl++;
                    if(pl==pattern.length()){
                        flag = true;
                    }

                } else if(Character.isUpperCase(query.charAt(i))){
                    flag = false;
                    break;
                }
            }
            result.add(flag);

        }
        return result;
    }
}