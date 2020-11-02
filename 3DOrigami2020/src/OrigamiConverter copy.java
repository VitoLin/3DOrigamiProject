// import java.io.File;
// import java.io.IOException;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;
// import java.awt.Color;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Scanner;
// import java.util.ArrayList;
// import java.awt.AlphaComposite;
// import java.awt.Image;
// import java.awt.RenderingHints;

// public class OrigamiConverter {
//     // list of colors < RGB value, name >
//     private HashMap<Integer, String> colorList = new HashMap<Integer, String>();
//     // contains colors from colorList and the count used
//     private HashMap<Integer, Integer> colorCount = new HashMap<Integer, Integer>();
//     // list of colors and their HCL values
//     private HashMap<Integer, double[]> HCLList = new HashMap<Integer, double[]>();
//     // contains all colors and the closest match from colorList
//     private HashMap<Integer, Integer> colorDex = new HashMap<Integer, Integer>();
    
//     // gets image stored inside a Buffered Image object
//     public static BufferedImage downloadOrigami(String pic){
//         BufferedImage img = null;
//         File f = null;
//         try{
//             f = new File(pic);
//             img = ImageIO.read(f);
//         }
//         catch(IOException e){
//             System.out.println(e);
//         }
//         return img;
//     }

//     public static BufferedImage resizeImage(final Image image, int width, int height) {
        
//         final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//         final Graphics2D graphics2D = bufferedImage.createGraphics();

//         // graphics2D.setComposite(AlphaComposite.Src);

//         graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
//         graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
//         graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

//         graphics2D.drawImage(image, 0, 0, width, height, null);
//         graphics2D.dispose();
    
//         return bufferedImage;
//     }

//     // resizes image to desired dimensions
//     public static BufferedImage resize(BufferedImage in, int width, int height){
//         BufferedImage newImage = new BufferedImage(width, height, in.getType());
//         Graphics2D g2 = newImage.createGraphics();
//         g2.drawImage(in, 0, 0, width, height, null);
//         g2.dispose();
        
//         return newImage;
//     }

//     // saves image 
//     public static void printImage(BufferedImage img, String output){
//         try{
//             File f = null;
//             f = new File(output + ".bmp");
//             ImageIO.write(img, "bmp", f);
//         }
//         catch(IOException e){
//             System.out.println(e);
//         }
//     }
    
//     // adds colors to color list
//     public void addColor(int color, String name){
//         colorList.put(color, name);
//     }
//     // getter for color counter
//     public int getCount(int n){
//         return colorCount.get(n);
//     }
//     // add increment count of color
//     public void incrementColor( final int c, final int n ){
//         int nval = colorCount.get(c) + n;
//         colorCount.put(c, nval);
//     }

//     // gives each RSB value in the colorList a HCL value
//     public void setColors(){
//         for (Map.Entry<Integer, String> entry : colorList.entrySet()) {
//             // convert to HCL and put it in hasmap of color integer and float
//             int red = (entry.getKey() >> 16) & 0xFF;
//             int green = (entry.getKey() >> 8) & 0xFF;
//             int blue = entry.getKey() & 0xFF;

//             double[] hcl = HCL.rgb2hcl(red, green, blue);
//             HCLList.put( entry.getKey(), hcl );
//         }
//     }
//     public void printHCL(){
//         for (Map.Entry<Integer, double[]> entry : HCLList.entrySet()) {
//             System.out.println(entry.getValue()[0]);
//         }
//     }
//     // returns closest color
//     public int getClosest(int color){
//         if( colorDex.containsKey( color ) ){
//             incrementColor( colorDex.get( color ), 1 );
//             return colorDex.get( color );
//         }
//         else{
//             // change to RGB then convert to HCL
//             int red = (color >> 16) & 0xFF;
//             int green = (color >> 8) & 0xFF;
//             int blue = color & 0xFF;

//             double[] hcl = HCL.rgb2hcl(red, green, blue);
            
//             // closest color
//             double distance = Double.MAX_VALUE;
//             int closestColor = 0;

//             // calculate which color is closest 
//             for (Map.Entry<Integer, double[]> entry : HCLList.entrySet()) {
//                 double newDistance = HCL.distance_hcl(hcl, entry.getValue());

//                 if( newDistance < distance ){
//                     distance = newDistance;
//                     closestColor = entry.getKey();
//                 }
//             }
//             colorDex.put( color , closestColor );
//             colorCount.put( closestColor, 1 );
//             return closestColor;
//         }
//     }

//     // function instructions for two sided
//     public void origamiConverterTwosided( String front, String back , int width, int height, String outputName ){
//         // set colors from selector
//         setColors();
//         // download image
//         BufferedImage originalFront = downloadOrigami(front);
//         BufferedImage originalBack = downloadOrigami(back);

//         // resize image
//         BufferedImage resizedImageFront = resizeImage(originalFront, width, height);
//         BufferedImage resizedImageBack = resizeImage(originalBack, width-1, height);

//         OrigamiText outputFile = new OrigamiText();

//         // function to iterate through image and call functions
//         int[][] intimageF = new int[width][height];
//         int[][] intimageB = new int[width-1][height];

//         for(int y = intimageF[0].length-1; y >= 0; --y){
//             // setting up variables for printing
//             ArrayList<Integer> colorStack = new ArrayList<>();
//             int count = 1;
//             // Front
//             outputFile.addText("Front facing row " + (intimageF.length - y) + ": ");
//             for (int x = 0; x < intimageF.length; ++x){
//                 // gets int value for color and finds closest match
//                 intimageF[x][y] = getClosest( resizedImageFront.getRGB(x,y) );
//                 // draws it to a picture to be outputted
//                 resizedImageFront.setRGB(x, y, intimageF[x][y] );

//                 if(colorStack.isEmpty()){
//                     colorStack.add(intimageF[x][y]);
//                 }
//                 else if( intimageF[x][y] == colorStack.get(colorStack.size()-1) ){
//                     ++count;
//                 }
//                 else{
//                     outputFile.addText( colorList.get( colorStack.get(colorStack.size()-1) ) + ": " + count + " ");
//                     colorStack.add(intimageF[x][y]);
//                     count = 1;
//                 }
//             }
//             outputFile.addText( colorList.get( colorStack.get(colorStack.size()-1) ) + ": " + count + "\n\n");
//             colorStack.clear();

//             // Back
//             outputFile.addText("Back facing row " + (intimageB.length - y) + ": ");
//             for (int x = 0; x < intimageB.length; ++x){
//                 // gets int value for color and finds closest match
//                 intimageB[x][y] = getClosest( resizedImageBack.getRGB(x,y) );
//                 // draws it to a picture to be outputted
//                 resizedImageBack.setRGB(x, y, intimageB[x][y] );

//                 if(colorStack.isEmpty()){
//                     colorStack.add(intimageB[x][y]);
//                 }
//                 else if( intimageB[x][y] == colorStack.get(colorStack.size()-1) ){
//                     ++count;
//                 }
//                 else{
//                     outputFile.addText( colorList.get( colorStack.get(colorStack.size()-1) ) + ": " + count + " ");
//                     colorStack.add(intimageB[x][y]);
//                     count = 1;
//                 }
//             }
//             outputFile.addText( colorList.get( colorStack.get(colorStack.size()-1) ) + ": " + count + "\n\n");
//             colorStack.clear();
//         }
//         // gives converted image
//         printImage(resizedImageFront, outputName + "Front");
//         printImage(resizedImageBack, outputName + "Back");
//         outputFile.makeInstructions(outputName, colorCount, colorList);
//     }


//     //driver
//     public static void main(String[]args){
        
//         OrigamiConverter oC = new OrigamiConverter();

//         oC.addColor(Color.BLACK.getRGB(), "black");
//         oC.addColor(Color.WHITE.getRGB(), "white");
//         oC.addColor(Color.LIGHT_GRAY.getRGB(), "light gray");
//         oC.addColor(Color.GRAY.getRGB(), "gray");
//         oC.addColor(Color.RED.getRGB(), "red");


//         Scanner scan = new Scanner(System.in);
//         Scanner sca = new Scanner(System.in);

//         System.out.print("Enter image to convert: ");
//         String image = scan.nextLine();
//         System.out.print("Enter image width and height: ");
//         int width = sca.nextInt();
//         int height = sca.nextInt();

        
//         oC.origamiConverterTwosided(image, image, width, height, "output");
//         scan.close();
//         sca.close();


//     }
// }
