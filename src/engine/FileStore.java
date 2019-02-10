package engine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class FileStore {

	public static char[] conf = new char[15];
	
	private static String fileName = "log\\conf.txt";
	private static String initState = "a0----------020";
									 //0123456789 
	
	private  Writer writer = null;
	

    // The name of the file to open.
    

    // This will reference one line at a time
    private String line = null;
    
    public FileStore() {
    	
    	
    	readFileConf();
    	
    	if(conf[0] != 'a') initStorageFile();
    	
    	if(conf[13] == '0') {
    		AudioPlayer.enumVolume = VOLUME.Mute;
    	}else if(conf[13] == '1') {
    		AudioPlayer.enumVolume = VOLUME.Low;
    	}else if(conf[13] == '2') {
    		AudioPlayer.enumVolume = VOLUME.High;
    	}
    	
    	
    }
	
    public void  readFileConf(){
    	try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

           line = bufferedReader.readLine();
           
           for(int i=0; i<15; i++) {
        	   conf[i] = line.charAt(i);
           }
          
           
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException e) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");  
           initStorageFile();
           readFileConf();
            
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
    }//end of readFileConf()
    
    public void initStorageFile() {

        try {
            // Assume default encoding.
        	File file = new File(fileName);
        	
        	  if(!file.exists()) {
        		  file.getParentFile().mkdir();
        		  file.createNewFile();
              }
        	
            FileWriter fileWriter =
                new FileWriter(file);
            
          

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(initState);
        						
            
            for(int i=0; i<15; i++) {
         	   conf[i] = initState.charAt(i);
            }
           

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }//end of initStorageFile()

    
    public static void update(int index, char state) {
    	conf[index] = state;
    	
    	 try {
             // Assume default encoding.
         	File file = new File(fileName);
         	
         	  if(!file.exists()) {
         		  file.getParentFile().mkdir();
         		  file.createNewFile();
               }
         	
             FileWriter fileWriter =
                 new FileWriter(file);
             
           

             // Always wrap FileWriter in BufferedWriter.
             BufferedWriter bufferedWriter =
                 new BufferedWriter(fileWriter);

             // Note that write() does not automatically
             // append a newline character.
             bufferedWriter.write(conf);
             

             // Always close files.
             bufferedWriter.close();
         }
         catch(IOException ex) {
             System.out.println(
                 "Error writing to file '"
                 + fileName + "'");
             // Or we could just do this:
             // ex.printStackTrace();
         }
    	
    }
    
}

	
	

