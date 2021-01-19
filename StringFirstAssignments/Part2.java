
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna,String startCoton,String stopCoton){
        
        // check if dna are uppercase--Character.isUpperCase
        // Return the first character of a string: dna
        if (Character.isUpperCase(dna.charAt(0))){
            startCoton = startCoton.toUpperCase();
            stopCoton = stopCoton.toUpperCase();
        }
        else {
            startCoton = startCoton.toLowerCase();
            stopCoton = stopCoton.toLowerCase();
        }
        
        int start = dna.indexOf(startCoton);
        if (start == -1){
             return "";
        }
        int stop = dna.indexOf(stopCoton,start+3);
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
        String s1="ACGGGGTCTATCTAA";
        String s2="AAAATGCCCTCA";
        String s3="AAAGGGCCCTCA";
        String s4="AAAATGCCCCATTAA";
        String s5="AAAATGCCCATTAA";
        String result1 = findSimpleGene(s1,"ATG","TAA");
        String result2 = findSimpleGene(s2,"ATG","TAA");
        String result3 = findSimpleGene(s3,"ATG","TAA");
        String result4 = findSimpleGene(s4,"ATG","TAA");
        String result5 = findSimpleGene(s5,"ATG","TAA");
        System.out.println("The string is " + s1 + "the gene is " + result1);
        System.out.println("The string is " + s2 + "the gene is " + result2);
        System.out.println("The string is " + s3 + "the gene is " + result3);
        System.out.println("The string is " + s4 + "the gene is " + result4);
        System.out.println("The string is " + s5 + "the gene is " + result5);
    }
}

