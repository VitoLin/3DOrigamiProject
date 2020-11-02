import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Scanner;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ConvertClean
{
    
    public static BufferedImage convertBasic(String in, int nw, int nh, int range, int grays)
    {
        //get image
        BufferedImage img = getImage(in);
        
        //resize
        img = adjustSize(img, nw, nh);
        //getColors
        ColorGatherer inList = new ColorGatherer();
        
        inList.gather(img, range);
        inList.deGray(grays);
        
        ColorMakerList.setList(inList.getGather());
        
        
        img = colorvert(img, nw, nh);
        
        
        
        Output.returnImage(img, "out","bmp");

        return img;
        
    }
    public static BufferedImage colorvert(BufferedImage in, int nw, int nh)
    {
        for(int x = 0; x < nw; x++)
        {
            for (int y = 0; y < nh; y++)
            {
                int clr=  in.getRGB(x,y); 
                Color now = new Color(ColorMakerList.closer(clr));
                in.setRGB(x, y, now.getRGB());
                
            }
        }
        return in;
    }
    
    
    public static BufferedImage getImage(String pic)
    {
        BufferedImage img = null;
        File f = null;
        try
        {
            f = new File(pic);
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return img;
    }
    
    public static BufferedImage adjustSize(BufferedImage in, int nw, int nh)
    {
        BufferedImage newImage = new BufferedImage(nw, nh, in.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(in, 0, 0, nw, nh, null);
        g2.dispose();
        return newImage;
    }
}