
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int start = dna.indexOf("ATG");
        if (start == -1){
             return "";
        }
        int stop = dna.indexOf("TAA",start+3);
        if (stop == -1){
             return "";
        }
        if ((stop-start) % 3 == 0){
             return dna.substring(start,stop+3);
        }
        else{
            return "";
        }
    }
    
    public void testSimpleGene(){
        //String s1="ACGGGGTCTATCTAA";
        //String s1="AAAATGCCCTCA";
        //String s1="AAAGGGCCCTCA";
        //String s1="AAAATGCCCCATTAA";
        String s1="“AAATGCCCTAACTAGATTAAGAAACC”";
        String result=findSimpleGene(s1);
        if (result != ""){
            System.out.println("print the gene " + result);
        }
        else{
            System.out.println("print empty string " + result);
        }
    }
}
