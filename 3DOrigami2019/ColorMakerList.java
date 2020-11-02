import java.awt.Color;
import java.util.*;
public class ColorMakerList 
{
    private static ArrayList<ColorMaker> all = new ArrayList<ColorMaker>();
    public ColorMakerList()
    {
    
    }
    public static void setList(ArrayList<ColorMaker> in)
    {
        all = in;
    }
    public static ArrayList<ColorMaker> getAll()
    {
        return all;
    }
    public static int totCol()
    {
        return all.size();
    }
    public void removeColor(int cnum)
    {
        all.remove(cnum);
    }
    public ColorMakerList(ArrayList<ColorMaker> in)
    {
        all = in;
    }
    public static int closer(int in)
    {
        double dif = 0;
        int out = 0;
        
        double me = 443.405007+1;
        
        for(int i =0; i<all.size(); i++)
        {
        //checks which color is closer in the RGB colorspace
        
        dif = dist(in, all.get(i).getColor());
        
        if(dif<me)
        {
            me = dif;
            out = i;
        }
        
        }
        
        return all.get(out).getColor();   
        
    }
    public void addColor(ColorMaker in)
    {
        all.add(in);
    }
    
    public String listColors()
    {
        String lists = "";
        for (int i = 0; i<all.size(); i++)
        {
            if(i < all.size() -1)
            lists += all.get(i) + ", ";
            else 
            lists += all.get(i);
            
        }
        return lists;
    }
    public static double dist(int in, int two)
    {
        int red = (in >> 16) & 0xFF;

        int green = (in >> 8) & 0xFF;

        int blue = in & 0xFF;
        
        
        int red2 = (two >> 16) & 0xFF;

        int green2 = (two >> 8) & 0xFF;

        int blue2 = two & 0xFF;
         
        return (Math.sqrt(Math.pow(red-red2,2)+Math.pow(blue-blue2,2)+Math.pow(green-green2,2)));
    }
}