import java.util.*;
import java.awt.Color;
public class Change
{
    private String name;
    private int num;
    
    public Change(String n, int nu)
    {
        name = n;
        num = nu;
        
    }
    public String toString()
    {
        return name+": "+num;
        
    }
    
}