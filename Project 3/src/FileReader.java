import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader { 
	
	public FileReader() {
		
	}
	
	public String readSequenceFromFile(String filename) {
		//Getting input from a text file
		String result = "";
	    try {
	    	File f = new File(filename);
	    	Scanner fileInput = new Scanner(f);
			  
		    while (fileInput.hasNext()) {
		          result = result + fileInput.nextLine();
		     }
		    
		    
		} catch(FileNotFoundException e) { 
			System.out.println("Sorry, can't find file!"); 
			System.out.println(e.getMessage()); 
			System.exit(1); 
		}

		return result;
	}
	
	public String readSequenceFromFileVer2(String filename) throws FileNotFoundException {
		//Getting input from a text file
		String result = "";
    	File f = new File(filename);
    	Scanner fileInput = new Scanner(f);
		  
	    while (fileInput.hasNext()) {
	          result = result + fileInput.nextLine();
	     }
		    		    
		return result;
	}
}
