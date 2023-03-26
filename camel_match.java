class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String word:queries){
            int i =0;
            boolean flag = false;
            for(int j=0;j<word.length();j++){
                char c = word.charAt(j);
                if(i<pattern.length() && pattern.charAt(i) ==c){
                    i++;
                    if(i>=pattern.length()){
                        flag = true;
                    }
                    }
                    else if(Character.isUpperCase(c)){
                        flag = false;
                        break;
                }
            }
                result.add(flag);
        }
return result;


    }
}