import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ChatClient1 {

    JTextArea incoming;
    JTextField outcoming;
    PrintWriter writer;
    Socket socket;
    BufferedReader reader;
    int textAreaWidth;
    JTextField nameField;

    public static void main(String[] args)
    {
        ChatClient1 client = new ChatClient1();

        client.buildGUI();
    }

    public void setUpNetworking()
    {
        textAreaWidth = outcoming.getWidth();
        try
        {
            socket = new Socket("127.0.0.1", 5000);

            writer = new PrintWriter(socket.getOutputStream());


            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            System.out.println("networking writer established!");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void buildGUI()
    {
        JFrame frame = new JFrame("Simple Chat Client");
        JPanel panel = new JPanel();

        nameField = new JTextField(15);
        nameField.setName("Name");
        nameField.setText("Enter your name...");
        nameField.selectAll();

        outcoming = new JTextField(20);

        incoming = new JTextArea(15, 40);
        incoming.setEditable(false);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(incoming);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton sendButton = new JButton("Send");
        outcoming.addActionListener(new SendButtonListener());

        JButton nameChangeButton = new JButton("Change a name");
        nameChangeButton.addActionListener(new NameChangeButtonListener());
        sendButton.addKeyListener(new SendButtonKeyListener());
        panel.add(nameField);
        panel.add(nameChangeButton);
        panel.add(scrollPane);
        panel.add(outcoming);
        panel.add(sendButton);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    public String getCurrentTime()
    {
        Date curTime = new Date();
        DateFormat dtfrm = DateFormat.getTimeInstance();
        String dateTime = dtfrm.format(curTime);

        return dateTime;
    }


    class SendButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                nameField.setEditable(false);
                String currentTime = getCurrentTime();

                writer.println(nameField.getText() + "(" + currentTime + "): " + outcoming.getText());
                writer.flush();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            outcoming.setText("");
            outcoming.requestFocus();
        }
    }

    class SendButtonKeyListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                try
                {
                    nameField.setEditable(false);
                    String currentTime = getCurrentTime();
                    //Style style = new


                    writer.println(nameField.getText() + "(" + currentTime + "): " + outcoming.getText());
                    writer.flush();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                outcoming.setText("");
                outcoming.requestFocus();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class NameChangeButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            nameField.setEditable(true);
        }
    }

    public class IncomingReader implements Runnable
    {
        public void run()
        {
            String message;


            try
            {
                while ((message = reader.readLine()) != null)
                {

                    System.out.println("read " + message);

                    //String name = workWithNames(message);

                    //Color currentColor = colorChooser.colorChoosing(name, nameList);

                    //incoming.setForeground(currentColor);
                    incoming.append(message + "\n\n");
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        //also for tests
        /*
        public String workWithNames(String message)
        {
            int namesCounter = 0;
            int endNumber = message.indexOf("(");
            String name = message.substring(0, endNumber);
            for (String currentName: nameList)
            {
                if(!currentName.equals(name))
                {
                    namesCounter++;
                }
            }

            if (namesCounter == nameList.size())
            {
                nameList.add(name);
            }

            return name;
        }*/


    }

    //for tests
    /*public class ColorChooser
    {
        ArrayList<Color> colorList = new ArrayList<Color>();

        ColorChooser()
        {
            colorList.add(Color.BLACK);
            colorList.add(Color.YELLOW);
            colorList.add(Color.PINK);
            colorList.add(Color.GREEN);
            colorList.add(Color.blue);
        }

        public void colorAdder(Color color)
        {
            colorList.add(color);
        }

        public Color colorChoosing(String name, ArrayList<String> nameList)
        {
            Color currentColor = null;

            for (int x = 0; x < nameList.size(); x++)
            {
                if(nameList.get(x).equals(name))
                {
                    currentColor = colorList.get(x);
                }
            }

            return currentColor;
        }

    }*/
}
