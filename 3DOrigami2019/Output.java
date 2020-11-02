import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.*;

public class Output
{
    public static void returnImage(BufferedImage img, String fileName, String fileType)
    {
        BufferedImage images = img;
        File f = null;
        
        try
        {
            String home = System.getProperty("user.home");
            File file = new File(home + File.separator + "Desktop" + File.separator + fileName + "." + fileType);
            
            ImageIO.write(images, fileType, file);
            f = new File(fileName +"." + fileType);
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    public static void returnSchem(BufferedImage img, ArrayList<ColorMaker> colors, String fileName, String fileType) 
    {
        PrintWriter outputStream = null;
        
        outputStream.println("The instructions start from the bottom of the image and builds up");
        outputStream.println("The instructions go from left to right");
        
        
        
    }
    
    
}
