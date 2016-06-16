import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.Style;


public class QuizCardPlayer {
    private JFrame frame;
    private ArrayList<QuizCard> cardList;
    private JTextArea textArea;
    private QuizCard currentCard;
    private int currentCardIndex;
    private boolean isShowAnswer;
    private JButton showAnswerButton;

    public void go()
    {
        buildGUI();

    }

    public void buildGUI() {
        frame = new JFrame("Quiz Card Player");
        JPanel panel = new JPanel();

        Font bigFont = new Font("serif", Font.BOLD, 24);

        textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(bigFont);
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        showAnswerButton = new JButton("Show Question");
        showAnswerButton.addActionListener(new AnswerButtonListener());

        panel.add(textArea);
        panel.add(showAnswerButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem loadCard = new JMenuItem("Load card set");

        loadCard.addActionListener(new LoadCardListener());

        menu.add(loadCard);


        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }



    private void fileLoad(File file)
    {
        cardList = new ArrayList<QuizCard>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine())!= null)
            {
                makeCard(line);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Couldn't read the card file");
        }

    }

    private void makeCard(String lineToParse)
    {
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");

    }

    private void showNextCard()
    {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        textArea.setText(currentCard.getQuestion());
        showAnswerButton.setText("Show Answer");
        isShowAnswer = true;
    }


    class LoadCardListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            fileLoad(fileChooser.getSelectedFile());
        }
    }


    class AnswerButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(isShowAnswer)
            {
                textArea.setText(currentCard.getAnswer());
                showAnswerButton.setText("Next Question");
                isShowAnswer = false;
            }
            else
            {
                if(currentCardIndex <= cardList.size())
                {
                    showNextCard();
                }
                else
                {
                    System.out.println("We don't have more questions yet!");
                    showAnswerButton.setEnabled(false);
                }
            }
        }
    }
}
