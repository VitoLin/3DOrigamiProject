import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class OrigamiText {
    private String text = "";

    public OrigamiText(){
    }
    
    public void addText(String in){
        text += in;
    }

    public void makeInstructions(String fileName, HashMap<Integer, Integer> in, HashMap<Integer, String> colorList){
        String files = fileName + ".txt";
        PrintWriter outputStream = null;

        // tries to find place in Downloads to put instructions, find better place Later
        try{
            String home = System.getProperty("user.home");
            File file = new File(home + File.separator + "Downloads" + File.separator + files);
            
            outputStream = new PrintWriter(file); 
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        // prints at the start of the page
        outputStream.println("The instructions are from left to right, bottom to top");
        // prints colors the person has chosen
        outputStream.println("These are the colors you have chosen and the total colors needed:");
        // get count of colors working
        for (Map.Entry<Integer, Integer> entry : in.entrySet()) {
            outputStream.println( colorList.get(entry.getKey()) + ": " + entry.getValue());
        }

        // print instructions stored in text
        outputStream.println(text);

        // close output stream
        outputStream.close();
    }
}
