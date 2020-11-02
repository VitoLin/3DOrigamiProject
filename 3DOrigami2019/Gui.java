import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
class Gui extends JFrame {
    GridBagLayout gbl = new GridBagLayout();
    
    Gui() {
        boolean which = true;
        setLayout(gbl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);

        //instantiates Border panels.
        BorderPanel pnlA = new BorderPanel("Front");
        BorderPanel pnlB = new BorderPanel("Back");
        BorderPanel pnlC = new BorderPanel("Front File (bmp)");
        BorderPanel pnlD = new BorderPanel("Dimensions");
        BorderPanel pnlE = new BorderPanel("Document name");
        BorderPanel pnlF = new BorderPanel("");
        BorderPanel pnlG = new BorderPanel("Back File (bmp)");
        //adding all panels to main contentPane.
        //A=================================================
        add(pnlA);
        
        
        //B=================================================
        add(pnlB);
        
        //C=================================================
        add(pnlC);
        JTextField files = new JTextField("");
        files.setPreferredSize(new Dimension(150, 30));
        pnlC.add(files);
        //D=================================================
        add(pnlD);
        JTextField w = new JTextField("");
        w.setPreferredSize(new Dimension(100, 30));
        pnlD.add(w);
        JTextField h = new JTextField("");
        h.setPreferredSize(new Dimension(100, 30));
        pnlD.add(h,1);
        //E=================================================
        add(pnlE);
        JTextField docu = new JTextField("");
        docu.setPreferredSize(new Dimension(100, 30));
        pnlE.add(docu);
        
        //G=================================================
        add(pnlG);
        JTextField files2 = new JTextField("");
        files2.setPreferredSize(new Dimension(150, 30));
        pnlG.add(files2);
        //F=================================================
        add(pnlF);
        
        JButton Conversion = new JButton("Convert");
        Conversion.setPreferredSize(new Dimension(100, 50));
        Conversion.addActionListener(new ActionListener()
        
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               Convert.convert(files.getText(),files2.getText(), Integer.parseInt(w.getText()), Integer.parseInt(h.getText()));
               Convert.doc(docu.getText());
               Convert.images();
               
               BufferedImage img = null;
               BufferedImage img2 = null;
               File file = null;
               JPanel  myPanel = new JPanel();
  
               try
               {
                   String home = System.getProperty("user.home");
                   file = new File(home + File.separator + "Desktop" + File.separator + "3DOrigamiGenerator" + File.separator + "Cycle.bmp");
                   
                   img = ImageIO.read(file);
               }
               catch(IOException e)
               {
                   e.printStackTrace();
                   System.exit(1);
               }
               
               try
               {
                  String home = System.getProperty("user.home");
                  file = new File(home + File.separator + "Desktop" + File.separator + "3DOrigamiGenerator" + File.separator + "Cycle2.bmp");
                   
                  img2 = ImageIO.read(file);
               }
               catch(IOException e)
               {
                    e.printStackTrace();
                    System.exit(1);
               }
            
               ImageIcon imgIcon = new ImageIcon(img);
               JLabel lbl = new JLabel();
               lbl.setIcon(imgIcon);
               pnlA.add(lbl, BorderLayout.LINE_START);
               ImageIcon imgIcon2 = new ImageIcon(img2);
               JLabel lbl2 = new JLabel();
               lbl2.setIcon(imgIcon2);
               pnlB.add(lbl2, BorderLayout.LINE_END);
        
               lbl.repaint();
               lbl2.repaint();
               
               pnlA.removeAll();
               
               pnlA.revalidate();
               pnlA.repaint();
               pnlA.add(lbl);
               
               pnlB.removeAll();
               
               pnlB.revalidate();
               pnlB.repaint();
               pnlB.add(lbl2);
               
            }
        });
        
        pnlF.add(Conversion);
        
        //set constraints of each panel.
        makeConstraints(gbl, pnlA, 2, 1, 0, 0, 10.0, 1.0);
        makeConstraints(gbl, pnlB, 2, 1, 0, 1, 10.0, 1.0);
        makeConstraints(gbl, pnlC, 1, 1, 0, 2, 1.0, 1.0); 
        makeConstraints(gbl, pnlD, 1, 1, 2, 0, 1.0, 1.0);
        makeConstraints(gbl, pnlE, 1, 1, 2, 1, 1.0, 1.0);
        makeConstraints(gbl, pnlF, 1, 1, 2, 2, 1.0, 1.0);
        makeConstraints(gbl, pnlG, 1, 1, 1, 2, 1.0, 1.0);
    }

    /**
      * Generate constraints for Swing components
      * @param gbl     a gridbaglayout that used to embed Swing component
      * @param comp    a Swing component intended to be embedded in gbl
      * @param w       desired component width
      * @param h       desired component height
      * @param x       desired location in x-axis
      * @param y       desired location in y-axis
      * @param weightx desired weight in terms of x-axis
      * @param weighty desired weight in terms of y-axis
      */
    public void makeConstraints(GridBagLayout gbl, JComponent comp, int w, int h, int x, int y,
            double weightx, double weighty) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        gbl.setConstraints(comp, constraints);
    }

    //Main method
    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    private static BufferedImage readPropagate(String imagePath) throws IOException {
    InputStream is = null;
    try {
        is = Gui.class.getClassLoader().getResourceAsStream(imagePath);
        // an example of how null checks can easily be forgotten 
        if (is == null) {
            throw new FileNotFoundException("Resource not found: " + imagePath);
        }
        return ImageIO.read(is);
    } finally {
        if (is != null) {
            is.close();
        }
    }
    }
    }
class BorderPanel extends JPanel {
    BorderPanel(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
} 