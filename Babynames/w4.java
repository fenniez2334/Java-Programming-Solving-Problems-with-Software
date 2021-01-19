
/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;


public class w4 {
	public void printNames () {
	        //make a file resource to choose the file I wanna open
		FileResource fr = new FileResource();
		//create a csv record, iterate over all of these reocrds in file
		//false means the CSV file doesn't have a header row.
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			//print only when numBorn is under a certain value.
			if (numBorn <= 100) {
			        //"0"is first one,"1" is second one, "2"is third one.
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}
        //this method returns the total number of births males and females in file
	public void totalBirths (FileResource fr) {
		//declare variables
	        int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		//create a csv record, iterate over all of these reocrds in file
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));

			totalBirths += numBorn;
			//check if the gender is male or female
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			}
			else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	
	//This method returns the rank of the name in the file for the given gender
	public long getRank(int year, String name, String gender){
	    long rank=-1;   
	    FileResource fr=new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
	    CSVParser parser=fr.getCSVParser(false);
	    
	    for (CSVRecord record: parser){
	        String currName=record.get(0);
	        String currGender=record.get(1);
	        if (currGender.equals(gender) && currName.equals(name)){
	            rank=record.getRecordNumber();   
	        }
	    }
	    return rank;
	}
	
	//this method returns the name of the person in the file for given rank
	public String getName(int year,int rank, String gender){
	    String name="";
	    FileResource fr=new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
	    CSVParser parser=fr.getCSVParser(false);
	    
	    for (CSVRecord record: parser){
	        long currRank=record.getRecordNumber();
	        String currGender=record.get(1);
	        String currName=record.get(0);
	        if (currRank == rank && currGender.equals(gender)){
	            name=currName;   
	        }
	    }
	    if (name != ""){
	        return name;   
	    }
	    else {
	        return "NO NAME";   
	    }
	}
	
	//This method determines what name would be named if they were born in a different year
        public void whatIsNameInYear(String name, int year,int newYear, String gender){
            FileResource fr=new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
	    FileResource newfr=new FileResource("us_babynames/us_babynames_by_year/yob"+newYear+".csv");
	    CSVParser parserOld=fr.getCSVParser(false);
            CSVParser parserNew=newfr.getCSVParser(false);
            String newName="";
            long popularity=0;
            
            for (CSVRecord record: parserOld){
                String currName=record.get(0);
                String currGender=record.get(1);
                
                if (currName.equals(name) && currGender.equals(gender)){
                    popularity=record.getRecordNumber();
                }
            }
            
            for (CSVRecord record: parserNew){
                String currGender=record.get(1);
                long currPopularity=record.getRecordNumber();
                
                if (currGender.equals(gender) && popularity == currPopularity){
                    newName=record.get(0);
                }
            }
            System.out.println(name+" born in "+year+" would be "+newName+" If she was born in "+newYear);
        }
        
        //this method selects a range of the files to process and returns an integer
        public int yearOfHighestRank(String name, String gender){
            long highestRank=0;
            int yearOfHighestRank=-1;
            String fileName="";
            DirectoryResource dr=new DirectoryResource();
            
            //Iterate through all files
            for (File f: dr.selectedFiles()){
                FileResource fr=new FileResource(f);
                CSVParser parser=fr.getCSVParser(false);
                
                //Iterate through all records in file
                for (CSVRecord record: parser){
                    String currName=record.get(0);
                    String currGender=record.get(1);
                    
                    if (currName.equals(name) && currGender.equals(gender)){
                        long currRank=record.getRecordNumber();
                        
                        if(highestRank == 0){
                            highestRank=currRank;
                            fileName=f.getName();
                        }
                        else{
                            if (highestRank > currRank){
                                highestRank = currRank;
                                fileName=f.getName();
                            }
                        }
                    }
                }
            }
            //remove all non-numeric characters from the filename
            fileName=fileName.replaceAll("[^\\d]","");
            //convert String fileName to Integer
            yearOfHighestRank=Integer.parseInt(fileName);
            return yearOfHighestRank;
        }
        
        //this method returns the average rank of a name in multiple files
        public double getAverageRank(String name, String gender) {
		// Initialize a DirectoryResource
		DirectoryResource dr = new DirectoryResource();
		// Define rankTotal, howMany
		double rankTotal = 0.0;
		int howMany = 0;
		// For every file the directory add name rank to agvRank
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);
				if(currName.equals(name) && currGender.equals(gender)){
					long currRank = record.getRecordNumber();
					rankTotal += (double)currRank;
					howMany += 1;
				}
			}
		}
		// Define avgRank = rankTotal / howMany
		double avgRank = rankTotal / (double)howMany;
		return avgRank;
	}
        
	
	//this method returns the total births of the same gender that are ranked higher
	//than the parameter name
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int numBorn = 0;
		long rank = getRank(year, name, gender);
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			int currBorn = Integer.parseInt(record.get(2));
			String currGender = record.get(1);
			long currRank = record.getRecordNumber();
			if(gender.equals(currGender) && rank > currRank) {
				numBorn += currBorn;
			}
		}
		return numBorn;
	}
	
	//testing all methods
	public void testTotlaBirth() {
		FileResource fr = new FileResource();
		totalBirths(fr);

		// long rank = getRank(1971, "Frank", "M");
		// System.out.println("Rank is: " + rank);

		// String name = getName(1982, 450, "M");
		// System.out.println("Name: " + name);

		// whatIsNameInYear("Owen", 1974, 2014, "M");

		// System.out.println(yearOfHighestRank("Genevieve", "F"));
		// System.out.println(yearOfHighestRank("Mich", "M"));
		//System.out.println(getAverageRank("Robert", "M"));
		
		 System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}
}

