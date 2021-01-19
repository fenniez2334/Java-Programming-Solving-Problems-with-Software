
/**
 * Find the lowest (coldest) temperature in a file of CSV weather data.
 * 
 * @Feifei Zhao 
 * @12/12/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with lowestSoFar as nothing
		CSVRecord lowestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If lowestSoFar is nothing
			if (lowestSoFar == null) {
				lowestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature < lowestSoFar’s
				if (currentTemp < lowestTemp && currentTemp != -9999) {
					//If so update lowestSoFar to currentRow
					lowestSoFar = currentRow;
			        }
		        }
                }
		//The lowestSoFar is the answer
		return lowestSoFar;
    }
    
    public void testColdestHourInFile(){
		FileResource fr = new FileResource();
		CSVRecord lowestTemp = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + lowestTemp.get("TemperatureF") +
				   " at " + lowestTemp.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        File fileName=null;
        CSVRecord coldestTemp=null;
        
        DirectoryResource dr=new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord currRow=coldestHourInFile(parser);
            
            if (coldestTemp ==null){
                coldestTemp= currRow;
                fileName=f;
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
	        double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if (currTemp<lowestTemp && currTemp !=-9999){
                    coldestTemp =currRow;
                    fileName=f;
                }
            }
            
        }
        //Returns the absolute pathname string of this abstract pathname.
        return fileName.getAbsolutePath();
    }
    
    public void testFileWithColdestTemperature(){
        String fileWithColdestTemp=fileWithColdestTemperature();
        File f=new File(fileWithColdestTemp);
        String filename=f.getName();
        
        System.out.println("Coldest day was in file"+filename);
        
        FileResource fr=new FileResource(f);
        CSVParser parser=fr.getCSVParser();
        CSVRecord lowestTemp=coldestHourInFile(parser);
        
        System.out.println("Coldest Temperature is: "+ lowestTemp.get("TemperatureF"));
        
        System.out.println("All the temperatures on the coldest day were: ");
        CSVParser parser2=fr.getCSVParser();
        for(CSVRecord record: parser2){
            double temp=Double.parseDouble(record.get("TemperatureF"));
            System.out.println(temp);
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity=null;
        int currHumd;
        int lowestHumd;
        for (CSVRecord currRow: parser){
            if (lowestHumidity==null){
                lowestHumidity=currRow;
            }
            else{
                if (!currRow.get("Humidity").equals("N/A") && !lowestHumidity.get("Humidity").equals("N/A")){
                    currHumd=Integer.parseInt(currRow.get("Humidity"));
                    lowestHumd=Integer.parseInt(lowestHumidity.get("Humidity"));
                    
                    if (currHumd<lowestHumd){
                        lowestHumidity=currRow;
                    }
                } 
            }
        }
        return lowestHumidity;
    }
    
    public void testlowestHumidityInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord lowestHumidity=lowestHumidityInFile(parser);
        
        System.out.println(lowestHumidity.get("Humidity")+" at "+lowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity=null;
        int currHumd;
        int lowestHumd;
        
        DirectoryResource dr=new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord currRow=lowestHumidityInFile(parser);
            
            if (lowestHumidity==null){
                lowestHumidity=currRow;
            }
            else{
                int currTemp=Integer.parseInt(currRow.get("Humidity"));
                int lowestTemp=Integer.parseInt(lowestHumidity.get("Humidity"));
                if (currTemp<lowestTemp){
                    lowestHumidity=currRow;
                }
                else{
                    if (currRow.get("Humidity") != "N/A" && lowestHumidity.get("Humidity") != "N/A"){
                        currHumd=Integer.parseInt(currRow.get("Humidity"));
                        lowestHumd=Integer.parseInt(lowestHumidity.get("Humidity"));
                        
                        if (currHumd<lowestHumd){
                            lowestHumidity=currRow;
                        }
                    }
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testlowestHumidityInManyFiles(){
        CSVRecord record=lowestHumidityInManyFiles();
        System.out.println(record.get("Humidity")+" at "+ record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double num=0.0;
        double sum=0.0;
        
        for(CSVRecord record: parser){
            double temp=Double.parseDouble(record.get("TemperatureF"));
            sum=sum+temp;
            num=num+1;
        }
        double average=sum/num;
        return average;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double avg=averageTemperatureInFile(parser);
        
        System.out.println("average temperature is "+ avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double num=0.0;
        double sum=0.0;
        
        for (CSVRecord record: parser){
            double temp=Double.parseDouble(record.get("TemperatureF"));
            int humidity=Integer.parseInt(record.get("Humidity"));
            if (humidity>=value){
                sum=temp+sum;
                num=num+1;
            }
        }
        double average=sum/num;
        return average;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double avg=averageTemperatureWithHighHumidityInFile(parser,80);
        
        if (!Double.isNaN(avg)){
            System.out.println("average temperature is "+ avg);
            
        }
        else{
            System.out.println("no temperature was found");
        }
    }
}
