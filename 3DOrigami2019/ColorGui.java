import java.awt.*;
import java.awt.Color.*;
import java.util.*;
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
public class ColorGui extends JFrame
{
    private Button[] buttons = new Button[ColorMakerList.totCol()];
    GridLayout gbl = new GridLayout();
    public void ColorGui()
    {
        setSize(500,500);
        setBackground(Color.WHITE);
        boolean which = true;
        setLayout(gbl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);

        double gridSize = ColorMakerList.totCol()/10;
        
        int gridS = (int)gridSize;
        ArrayList<ColorMaker> inList = ColorMakerList.getAll();
        inList.add(new ColorMaker(Color.RED.getRGB()));
        inList.add(new ColorMaker(Color.WHITE.getRGB()));
        inList.add(new ColorMaker(Color.BLACK.getRGB()));
        buttons = new Button[ColorMakerList.totCol()];
        
        if(gridSize% 1 > 0)
        gridS = (int)gridSize + 1;
        else
        gridS = (int)gridSize;
        
        setLayout(new GridLayout(10, gridS, 10,10));
        int cC = 0;
        for(int i = 0; i < buttons.length; i++)
        {
            cC = inList.get(i).getColor();
            Color nC = new Color(cC);
            buttons[i].setBackground(nC);
            
            add(buttons[i]);
            
        }
        
    }
    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ColorGui().setVisible(true);
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
    class BorderPanel extends JPanel {
    BorderPanel(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
    }
}
    