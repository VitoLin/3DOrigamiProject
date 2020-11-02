import java.awt.Color;
import java.util.*;
public class ColorMaker
{
    String colors; 
    int value;
    public ColorMaker(String col, int val)
    {
        
        colors =  col;
        value = val;
        
    }
    public ColorMaker(int val)
    {
        value = val;
        colors = "none";
        
    }
    public void addName(String in)
    {
        colors = in;
    }
    public int getColor()
    {
        return value;
    }
    public String toString()
    {
        return colors;
    }
    public int red()
    {
        return (value >> 16) & 0xFF;
    }
    public int green()
    {
        return (value >> 8) & 0xFF;
    }
    public int blue()
    {
        return value & 0xFF;
    }
    
    
}