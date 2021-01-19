
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex=dna.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1){
            int diff=currIndex-startIndex;
            if (diff % 3 ==0){
                return currIndex;
            }
            else {
                currIndex=dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currentGene=findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex=dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
    }
    
    public void testFindGene(){
        String dna1 = "AAATTCCCTAACTAGATTAAGAAACC";
        String dna2 = "AAATGCCCTAACCAGATCAAGAAACC";
        String dna3 = "AAATGCCCTAACTAGATTAAGAAACC";
        String dna4 = "AAATGCCCCAACCAGATCAAGAAACC";
        String dna5 = "AATGCTAACTAGCTGACTAAT";
        String gene1 = findGene(dna1,0);
        System.out.println(dna1 + ":" + gene1);
        String gene2 = findGene(dna2,0);
        System.out.println(dna2 + ":" + gene2);
        String gene3 = findGene(dna3,0);
        System.out.println(dna3 + ":" + gene3);
        String gene4 = findGene(dna4,0);
        System.out.println(dna4 + ":" + gene4);
        String gene5 = findGene(dna5,0);
        System.out.println(dna5 + ":" + gene5);
    }
    
    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex=findStopCodon(dna,0,"TAA");
        if (dex !=9) System.out.println("Error on 9");
        dex=findStopCodon(dna,9,"TAA");
        if (dex !=21) System.out.println("Error on 21");
        dex=findStopCodon(dna,1,"TAA");
        if (dex !=26) System.out.println("Error on 26");
        dex=findStopCodon(dna,1,"TAG");
        if (dex !=26) System.out.println("Error on 26TAG");
        System.out.println("finish testing");
    }
}
