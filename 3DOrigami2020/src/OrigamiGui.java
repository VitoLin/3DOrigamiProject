import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.*;

public class OrigamiGui extends JFrame {

    GridBagLayout gbl = new GridBagLayout();
    OrigamiGui(){ 
        setLayout(gbl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);

        //instantiates Border panels.
        BorderPanel frontPnl = new BorderPanel("Front");
        BorderPanel backPnl = new BorderPanel("Back");
        BorderPanel pnlC = new BorderPanel("Width");
        BorderPanel pnlD = new BorderPanel("Height");
        BorderPanel pnlE = new BorderPanel("ColorSelector");
        BorderPanel pnlF = new BorderPanel("Colors");
        BorderPanel pnlG = new BorderPanel("Convert");

        //adding all panels to main contentPane.
        //A=================================================
        add(frontPnl);

        JButton frontButton = new JButton();
        frontButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JFileChooser fc = new JFileChooser();
                File file = null;
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
                    file = fc.getSelectedFile();

                    BufferedImage myPicture = OrigamiConverter.downloadOrigami(file);
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                    frontPnl.add(picLabel);
            }});
        
        frontPnl.add(frontButton);     

        //B=================================================
        add(backPnl);

        JButton backButton = new JButton();
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event)
            {
                JFileChooser fc = new JFileChooser();
                File file = null;
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
                    file = fc.getSelectedFile();

                Icon icon = new ImageIcon(file.getName());
                backButton.setIcon(icon);
            }});
        backPnl.add(backButton);     

        //C=================================================

        //D=================================================

        //E=================================================

        //F=================================================

        //G=================================================

        //set constraints of each panel.
        makeConstraints(gbl, frontPnl, 3, 3, 0, 0, 10.0, 1.0);
        makeConstraints(gbl, backPnl, 3, 3, 0, 3, 10.0, 1.0);
        makeConstraints(gbl, pnlC, 3, 1, 3, 0, 1.0, 1.0); 
        makeConstraints(gbl, pnlD, 3, 1, 3, 1, 1.0, 1.0);
        makeConstraints(gbl, pnlE, 3, 1, 3, 2, 1.0, 1.0);
        makeConstraints(gbl, pnlF, 3, 2, 3, 3, 1.0, 1.0);
        makeConstraints(gbl, pnlG, 3, 1, 3, 5, 1.0, 1.0);

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

    public static void main(String[]arg){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrigamiGui().setVisible(true);
            }
        });
    }

    class BorderPanel extends JPanel {
        BorderPanel(String title) {
            setBorder(BorderFactory.createTitledBorder(title));
        }
    }
}
