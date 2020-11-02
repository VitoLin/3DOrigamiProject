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

public class Convert
{
    
    public static void convert(String i, String j, int nw, int nh)
    {
        //this reads the picture that needs to be converted
        Scanner scal = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        
        String choice = i;
        BufferedImage img = null;
        File f = null;
        try
        {
            f = new File(choice);
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        int width = img.getWidth();
        int height = img.getHeight();

        int newWidth = nw;
        int newHeight = nh;
        
        
        try{
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(img, 0, 0, newWidth, newHeight, null);
        g2.dispose();
            f = new File("Output.bmp");
            ImageIO.write(newImage, "bmp", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
        //------------------------------------------------------------------------------------
        //convert to standardized colors
        BufferedImage newImage = null;
        try
        {
            f = new File("Output.bmp");
            newImage = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        BufferedImage images = newImage;

        // Getting pixel color by position x and y 
        for(int x = 0; x < newWidth; x++)
        {
            for (int y = 0; y < newHeight; y++)
            {
                int clr=  images.getRGB(x,y); 
                Color now = new Color(closer(clr));
                images.setRGB(x, y, now.getRGB());
                     

            }
        }
        
        try
        {
            String home = System.getProperty("user.home");
            File file = new File(home + File.separator + "Desktop" + File.separator + "OutputFront.bmp");
            ImageIO.write(images, "bmp", file);
            f = new File("Output.bmp");
            ImageIO.write(images, "bmp", f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        
        //------------------------------------------------------------------------------------
        //second file
        //this reads the picture that needs to be converted
        String choice2 = j;
        BufferedImage img2 = null;
        File f2 = null;
        try
        {
            f2 = new File(choice2);
            img2 = ImageIO.read(f2);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();

        int newWidth2 = nw-1;
        int newHeight2 = nh;
        
        
        try{
        BufferedImage newImage2 = new BufferedImage(newWidth2, newHeight2, img2.getType());
        Graphics2D g2 = newImage2.createGraphics();
        g2.drawImage(img2, 0, 0, newWidth2, newHeight2, null);
        g2.dispose();
            f = new File("Output2.bmp");
            ImageIO.write(newImage2, "bmp", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
        //------------------------------------------------------------------------------------
        //convert to standardized colors
        BufferedImage newImage2 = null;
        try
        {
            f2 = new File("Output2.bmp");
            newImage2 = ImageIO.read(f2);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        BufferedImage images2 = newImage2;

        // Getting pixel color by position x and y 
        for(int x = 0; x < newWidth2; x++)
        {
            for (int y = 0; y < newHeight2; y++)
            {
                int clr=  images2.getRGB(x,y); 
                Color now = new Color(closer(clr));
                images2.setRGB(x, y, now.getRGB());
                     

            }
        }
        try
        {
            String home = System.getProperty("user.home");
            File file2 = new File(home + File.separator + "Desktop" + File.separator + "OutputBack.bmp");
            f = new File("Output2.bmp");
            ImageIO.write(images2, "bmp", f);
            ImageIO.write(images2, "bmp", file2);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void doc(String sss)
    {
        BufferedImage images = null;
        File f = null;
        BufferedImage images2 = null;
        File f2 = null;
        try
        {
            f = new File("Output.bmp");
            images = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        try
        {
            f2 = new File("Output2.bmp");
            images2 = ImageIO.read(f2);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        String files = sss+".txt";
        PrintWriter outputStream = null;
        try 
        {
            String home = System.getProperty("user.home");
            File file = new File(home + File.separator + "Desktop" + File.separator + files);
            
            outputStream = new PrintWriter(file); 
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        boolean first = true;
        int row=0;
        int[] test = new int[images.getWidth()];
        int[] test2 = new int[images2.getWidth()];
        
        test[0] = 0;
        int[] totals = new int[(images.getWidth()+images2.getWidth())*images.getHeight()];
        int tot =0;
        for(int y =images.getHeight()-1; y>=0; y--)
        {
            for(int x = 0; x<images.getWidth(); x++)
            {
                totals[tot] = images.getRGB(x,y); 
                tot++;
            }
            
            for(int x = 0; x<images2.getWidth(); x++)
            {
                totals[tot] = images2.getRGB(x,y); 
                tot++;
            }
        }
        
        
        
        for(int y =images.getHeight()-1; y>=0; y--)
        {
            
            row ++;
            for(int x = 0; x<images.getWidth(); x++)
            {
                test[x]=images.getRGB(x,y);
            }
            
            if(first)
            {
                outputStream.println("The instructions are from bottom to top, left to right");
                outputStream.println();
                outputStream.println("The total pieces required for this project is "+Checkt(totals));
                outputStream.println();
                first = false;
            }
            
            outputStream.println("Front Facing Row "+row+": "+ Check(test));
            outputStream.println();
            
            for(int x = 0; x<images2.getWidth(); x++)
            {
                test2[x]=images2.getRGB(x,y); 
            }

            outputStream.println("Back Facing Row "+row+": "+ Check(test2));
            outputStream.println();
        }
        
        
        outputStream.close();
                
    }
    public static String Checkt(int[] in)
    {
        ArrayList<Object> total = new ArrayList<Object>();
        int go = 0;
        int r = 0;
        int gre = 0;
        int b = 0;
        int y = 0;
        int c = 0;
        int m = 0;
        int w = 0;
        int bl = 0;
        int gra = 0;
        int l = 0;
        int d = 0;
        int o = 0;
        int p = 0;
        
        while(go < in.length)
        {
            
        if(in[go]== Color.RED.getRGB())
        {
            r++;
        }
        if(in[go] == Color.WHITE.getRGB())
        {
            w++;
        }
        if(in[go] == Color.GREEN.getRGB())
        {
            gre++;
        }
        if(in[go] == Color.BLUE.getRGB())
        {
            b++;
        }
        if(in[go] == Color.YELLOW.getRGB())
        {
            y++;
        }
        if(in[go] == Color.CYAN.getRGB())
        {
            c++;
        }
        if(in[go] == Color.MAGENTA.getRGB())
        {
            m++;
        }
        if(in[go] == Color.BLACK.getRGB())
        {
            bl++;
        }
        if(in[go] == Color.GRAY.getRGB())
        {
            gra++;
        }
        if(in[go] == Color.LIGHT_GRAY.getRGB())
        {
            l++;
        }
        if(in[go] == Color.DARK_GRAY.getRGB())
        {
            d++;
        }
        if(in[go] == Color.ORANGE.getRGB())
        {
            o++;
        }
        if(in[go] == Color.PINK.getRGB())
        {
            p++;
        }
        go++;
        }
        if(r>0)
        total.add(new Change("Red",r));
        if(gre>0)
        total.add(new Change("Green",gre));
        if(b>0)
        total.add(new Change("Blue",b));
        if(y>0)
        total.add(new Change("Yellow",y));
        if(c>0)
        total.add(new Change("Cyan",c));
        if(m>0)
        total.add(new Change("Magenta",m));
        if(w>0)
        total.add(new Change("White",w));
        if(bl>0)
        total.add(new Change("Black",bl));
        if(gra>0)
        total.add(new Change("Gray",gra));
        if(l>0)
        total.add(new Change("Light Gray",l));
        if(d>0)
        total.add(new Change("Dark Gray",d));
        if(o>0)
        total.add(new Change("Orange",o));
        if(p>0)
        total.add(new Change("Pink",p));
        return total.toString();
    }
    public static String Check (int[] c)
    {
        ArrayList<Object> total = new ArrayList<Object>(c.length);
        
            int count = 1;
            for (int i = 0; i<c.length; i++)
            {
                if(i == c.length-1)
                {
                    total.add(new Change(cc(c[i]), count));
                    count =1;
                }
                else if(c[i] == c[i+1])
                {
                    count ++;
                }
                
                else
                {
                    total.add(new Change(cc(c[i]), count));
                    count =1;
                    
                }
                

            }
            
            
        return total.toString();
    }

    public static String cc(int c)
    {
        if(c == Color.RED.getRGB())
        {
            return "Red";
        }
        if(c == Color.WHITE.getRGB())
        {
            return "White";
        }
        if(c == Color.GREEN.getRGB())
        {
            return "Green";
        }
        if(c == Color.BLUE.getRGB())
        {
            return "Blue";
        }
        if(c == Color.YELLOW.getRGB())
        {
            return "Yellow";
        }
        if(c == Color.CYAN.getRGB())
        {
            return "Cyan";
        }
        if(c == Color.MAGENTA.getRGB())
        {
            return "Magenta";
        }
        if(c == Color.BLACK.getRGB())
        {
            return "BLACK";
        }
        if(c == Color.GRAY.getRGB())
        {
            return "Gray";
        }
        if(c == Color.LIGHT_GRAY.getRGB())
        {
            return "Light Gray";
        }
        if(c == Color.DARK_GRAY.getRGB())
        {
            return "Dark Gray";
        }
        if(c == Color.ORANGE.getRGB())
        {
            return "Orange";
        }
        if(c == Color.PINK.getRGB())
        {
            return "Pink";
        }
        return "Error";
    }
    
    public static int closer(int in)
    {
        int r = Color.RED.getRGB();
        int gre = Color.GREEN.getRGB();
        int b = Color.BLUE.getRGB();
        int y = Color.YELLOW.getRGB();
        int c = Color.CYAN.getRGB();
        int m = Color.MAGENTA.getRGB();
        int w = Color.WHITE.getRGB();
        int bl = Color.BLACK.getRGB();
        int gra = Color.GRAY.getRGB();
        int l = Color.LIGHT_GRAY.getRGB();
        int d = Color.DARK_GRAY.getRGB();
        int o = Color.ORANGE.getRGB();
        int p = Color.PINK.getRGB();
        int check = 999999999;
        int[] colors = {r,w};
        

        int red = (in >> 16) & 0xFF;

        int green = (in >> 8) & 0xFF;

        int blue = in & 0xFF;
        
        int dif = 0;
        int out = 0;
        int me = 99999999;
        for(int i =0; i<colors.length; i++)
        {
        int red2 = (colors[i] >> 16) & 0xFF;

        int green2 = (colors[i] >> 8) & 0xFF;

        int blue2 = colors[i] & 0xFF;
            
        dif = (int)(Math.sqrt(Math.pow(red-red2,2)+Math.pow(blue-blue2,2)+Math.pow(green-green2,2))); 
        if(dif<me)
        {
            me = dif;
            out = i;
        }
        
        }
        return colors[out];
    }
    public static void images()
    {
        BufferedImage img = null;
        File f = null;
        try
        {
            f = new File("Output.bmp");
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        try{
        BufferedImage newImage = new BufferedImage(200, 200/img.getWidth()*img.getHeight(), img.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(img, 0, 0, 200, 200/img.getWidth()*img.getHeight(), null);
        g2.dispose();
            f = new File("Cycle.bmp");
            ImageIO.write(newImage, "bmp", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
        try
        {
            f = new File("Output2.bmp");
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        try{
        BufferedImage newImage = new BufferedImage(200, 200/img.getWidth()*img.getHeight(), img.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(img, 0, 0, 200, 200/img.getWidth()*img.getHeight(), null);
        g2.dispose();
            f = new File("Cycle2.bmp");
            ImageIO.write(newImage, "bmp", f);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
