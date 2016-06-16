import javax.swing.*;
import java.awt.*;

public class Shrinker
{
    int x = 1;
    int y = 1;

    public static void main(String[] args)
    {
        Shrinker shrinker = new Shrinker();
        shrinker.go();
    }

    public void go()
    {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel myDrawPanel = new MyDrawPanel();
        frame.getContentPane().add(myDrawPanel);
        frame.setSize(500, 270);
        frame.setVisible(true);
        for(int i = 0; i < 124; i++, y++, x++)
        {
            x++;
            myDrawPanel.repaint();
            try {
                Thread.sleep(50);
            }
            catch (Exception ex){}
        }





    }

    class MyDrawPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 500, 250);

            g.setColor(Color.blue);
            g.fillRect(x, y, 500 - x*2, 250 - y*2);
        }
    }
}
