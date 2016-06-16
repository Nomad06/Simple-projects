import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Colorit{

    JLabel label;
    JFrame frame;
    int x = 0;
    int y = 0;

    public static void main(String[] args)
    {
        Colorit colorit = new Colorit();
        colorit.go();
    }

    public void go()
    {
        frame = new JFrame();

        JButton labelButton = new JButton("Change label");
        labelButton.addActionListener(new LabelListener());

        JButton colorButton = new JButton("Change color");
        colorButton.addActionListener(new ColorListener());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel drawPanel = new DrawPanel();
        label = new JLabel("I'm a label");

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);

        frame.setSize(500, 500);
        frame.setVisible(true);

        for(int i = 0; i < 130; i++)
        {
            x++;
            y++;
            drawPanel.repaint();

            try
            {
                Thread.sleep(50);
            }
            catch (Exception ex){}
        }

    }

    class LabelListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch!");
        }
    }

    class ColorListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            frame.repaint();

        }
    }

    class DrawPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setPaint(Color.white);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

            int red = (int)(Math.random()*255);
            int green = (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            Color startColor = new Color(red, green, blue);


            red = (int)(Math.random()*255);
            green = (int)(Math.random()*255);
            blue = (int)(Math.random()*255);
            Color endColor = new Color(red, green, blue);

            GradientPaint gradientPaint = new GradientPaint(70, 70, startColor, 150, 150, endColor);
            g2d.setPaint(gradientPaint);
            g2d.fillOval(x, y, 40, 40);
        }
    }

}


