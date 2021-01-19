
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import library of edu.duke.
//"*" means : imports all classes in edu.duke package
import edu.duke.*;

public class Part4 {
    public void printUrls(String url){
        URLResource ur = new URLResource(url);
        for (String wd : ur.words()){
            int start=wd.toLowerCase().indexOf("youtube.com");
            if (start != -1){
                int quoteindex=wd.indexOf("\"");
                int lastquoteindex=wd.lastIndexOf("\"");
                System.out.println(wd.substring(quoteindex+1, lastquoteindex));
            }
        }
    
    }
    public void testUrl(){
        //http are not work anymore should use https
        printUrls("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    public static void main() {
        Part4 url = new Part4();
        url.testUrl();
    } 
}
