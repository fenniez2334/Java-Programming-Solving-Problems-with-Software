
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurences(String a, String b){
        int first=b.indexOf(a);
        if (first != -1){
            int second=b.indexOf(a,first+1);
            if (second != -1){
                return "True";
            }
            else{
                return "False";
            }
        }
        else{
            return "False";
        }
    }
    public String lastPart(String a, String b){
        int first=b.indexOf(a);
        if (first != -1){
            String re=b.substring(first+a.length(),b.length());
            return re;
        }
        else{
            return b;
        }    
    }
    
    public void testing(){
        System.out.println("a=" + "by" + "b=" + "A story by Abby Long" + twoOccurences("by", "A story by Abby Long"));
        System.out.println("a=" + "a" + "b=" + "banana" + twoOccurences("a", "banana"));
        System.out.println("a=" + "atg" + "b=" + "ctgtatgta" + twoOccurences("atg", "ctgtatgta"));
        
        System.out.println("a=" + "an" + "b=" + "banana" + lastPart("an", "banana"));
        System.out.println("a=" + "zoo" + "b=" + "forest" + lastPart("zoo", "forest"));        
    }
        public static void main() {
        Part3 howMany = new Part3();
        howMany.testing();
    }
}
