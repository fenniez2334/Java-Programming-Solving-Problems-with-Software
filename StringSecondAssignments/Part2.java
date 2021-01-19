
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int count=0;
        int startIndex=0;
        while (true){
            int pos=b.indexOf(a,startIndex);
            if (pos==-1){
                break;
            }
            count=count+1;
            startIndex=pos+a.length();
        }
        return count;
    }    
       
    public void testHowMany(){
        System.out.println("a=" + "GAA" + "b=" + "ATGAACGAATTGAATC" +"result:"+ howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println("a=" + "AA" + "b=" + "ATAAAA" +"result:"+ howMany("AA", "ATAAAA"));
       
    }
    
    public void test2222(){
        String dna = "CTGCCTGCATGATCGTA";
        String newDna= "";
        int count=0;
        int startPos=0;
        int pos=2;
        
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        System.out.println(count+"|"+startPos+"|"+newDna);
    }
}
