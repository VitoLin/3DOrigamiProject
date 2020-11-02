import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.util.*;
public class ColorGatherer
{
    private static ArrayList<ColorMaker> out = new ArrayList<ColorMaker>();
    public static ArrayList<ColorMaker> gather(BufferedImage img, int range)
    {
        ArrayList<Integer> all = new ArrayList<Integer>();
        for(int x = 0; x < img.getWidth(); x+= (int)img.getWidth()/range)
        {
            for (int y = 0; y < img.getHeight(); y+= (int)img.getHeight()/range)
            {
                int clr=  img.getRGB(x,y);
                
                all.add(clr);
            }
        }
        
        while(all.size()>1)
        {
            int mode = all.get(0);
            int most = 0;
            for(int i = 0; i< all.size(); i ++)
            {   
                int value = all.get(i);
                int count = 1;
                for (int j = 0; j < all.size(); j++) 
                {
                    if (all.get(j) == value)
                    count++;
                    if (count > most) 
                    {
                        mode = value;
                        most = count;
                    }
                }
            }
            out.add(new ColorMaker(mode));
            for(int i = 0; i< all.size(); i ++)
            {
                if(ColorMakerList.dist(all.get(i), mode) < range)
                all.remove(i);
            }
            
        }
        
        return out;
    }
    public static ArrayList<ColorMaker> clean(int choice)
    {
        int start = out.size() - 1;
        for(int i = start; i >= choice; i--)
        {
        out.remove(i);
        }
            
        return out;
    }
    public static ArrayList<ColorMaker> deGray(int gray)
    {
        int start = out.size() - 1;
        for(int i = start; i >= 0; i--)
        {
            if(Math.abs(out.get(i).red()-out.get(i).green()) < gray ||
            Math.abs(out.get(i).blue()-out.get(i).green()) < gray ||
            Math.abs(out.get(i).red()-out.get(i).blue()) < gray)
            out.remove(i);
        }
            
        return out;
    }
    public static ArrayList<ColorMaker> getGather()
    {
        return out;
    }
}
