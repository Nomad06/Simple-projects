import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class QuizCardBuilder
{

    private JFrame frame;
    private JTextArea questionArea;
    private JTextArea answerArea;
    private ArrayList<QuizCard> cardsList;

    public static void main(String[] args)
    {
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.go();
    }

    public void go()
    {
        cardsList = new ArrayList<QuizCard>();
        frame = new JFrame();
        JPanel panel = new JPanel();

        Box box = new Box(BoxLayout.Y_AXIS);

        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        JLabel questionLabel = new JLabel("Question:");
        questionArea = new JTextArea(6, 20);
        JScrollPane questionScroll = new JScrollPane(questionArea);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(bigFont);
        questionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(questionLabel);
        panel.add(questionScroll);

        JLabel answerLabel = new JLabel("Answer:");
        answerArea = new JTextArea(6, 20);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setFont(bigFont);
        JScrollPane answerScroll = new JScrollPane(answerArea);
        answerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(answerLabel);
        panel.add(answerScroll);

        JButton nextCardButton = new JButton("Next Card");
        nextCardButton.addActionListener(new NextCardListener());

        JButton gameStarter = new JButton("Start Game");
        gameStarter.addActionListener(new GameStartListener());

        panel.add(nextCardButton);
        panel.add(gameStarter);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem newmenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        newmenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        menu.add(newmenuItem);
        menu.add(saveMenuItem);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 600);
        frame.setVisible(true);

    }

    private class NextCardListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            QuizCard card = new QuizCard(questionArea.getText(), answerArea.getText());
            cardsList.add(card);
            clearCard();
        }
    }

    class GameStartListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCardPlayer quizCardPlayer = new QuizCardPlayer();
            quizCardPlayer.go();
        }
    }

    public void clearCard()
    {
        answerArea.setText("");
        questionArea.setText("");
        questionArea.requestFocus();
    }

    private class SaveMenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File file)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (QuizCard card: cardsList)
            {
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        }
        catch (IOException e1) {
            System.out.println("Couldn't write the cardlist out");
            e1.printStackTrace();
        }
    }

    private class NewMenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            cardsList.clear();
            answerArea.setText("");
            questionArea.setText("");
        }
    }
}

class QuizCard implements Serializable
{
    private String question;
    private String answer;

    QuizCard(String q, String a)
    {
        question = q;
        answer   = a;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }
}
