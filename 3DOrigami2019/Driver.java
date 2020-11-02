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
public class Driver
{
    public static void main (String[]args)
    {
        BufferedImage img = null;

        img = ConvertClean.convertBasic("test.bmp",250,200,400,5); 
        
        
        Output.returnImage(img, "out","bmp");
        
        
    }
}