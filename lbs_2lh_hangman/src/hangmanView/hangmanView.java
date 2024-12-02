package hangmanView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class hangmanView {
    private JTextField guessWordtxt;
    private JFormattedTextField guessTxt;
    private JTextField letterFieldTxt;
    private JPanel hangmanPanel;
    private JPanel pictureView;
    static JMenuBar mb;
    static JMenu x;
    static JMenuItem m1, m2, m3;
    static JFrame f;
    private JButton guessButton;
    public JLabel pictureViewTest;
    private static hangmanGame game;
    private JMenuBar menuBar;
    private JMenu settingsMenu;
    private JMenuItem setAttemptsMenuItem;
    private JCheckBoxMenuItem showHistoryMenuItem;
    private List<String> words;


    public hangmanView() {
        game = new hangmanGame();
        guessWordtxt.setText(game.getGuessedWord());
        letterFieldTxt.setText(game.getGuessedLetters());
        updateImage(game.getAttemptsLeft());

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });
        guessTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });


    }

    private void updateImage(int attemptsLeft) {
        pictureViewTest.setIcon(new ImageIcon("lbs_2lh_hangman/src/img/" + (9- attemptsLeft) + ".png"));
    }

    private void makeGuess() {
        String guessInput = guessTxt.getText().toLowerCase();

        // Überprüfen, ob ein Buchstabe eingegeben wurde
        if (guessInput.isEmpty()) {
            JOptionPane.showMessageDialog(hangmanPanel, "gib nen buchtsaben ein", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char guess = guessInput.charAt(0);
        String result = game.makeGuess(guess);

        // Aktualisiere das angezeigte Wort
        guessWordtxt.setText(game.getGuessedWord());
        letterFieldTxt.setText(game.getGuessedLetters());

        // Zeige das Ergebnis in einem Pop-up-Fenster an
        JOptionPane.showMessageDialog(hangmanPanel, result, "Hinweis", JOptionPane.INFORMATION_MESSAGE);

        updateImage(game.getAttemptsLeft());

        // Überprüfen, ob das Spiel zu Ende ist
        if (game.isWordGuessed() || game.getAttemptsLeft() <= 0) {
            guessButton.setEnabled(false); // Deaktiviere den Button, wenn das Spiel zu Ende ist
        }

        guessTxt.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("hangmanView");
        frame.setContentPane(new hangmanView().hangmanPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("lbs_2lh_hangman/src/img/titlepic.png");
        frame.setIconImage(icon.getImage());
        frame.pack();
        frame.setVisible(true);

        mb = new JMenuBar();
        x = new JMenu("Menu");

        m1 = new JMenuItem("Wörter");
        m2 = new JMenuItem("Anzahl Versuche");
        m3 = new JMenuItem("History der Buchstaben");

        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Hier kannst du die Wörter verwalten.\n" + Arrays.toString(game.getWords()), "Wörter", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Versuche:\n" + game.getAttemptsLeft(), "Anzahl Versuche", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        m3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "History der Buchstaben:\n", "History der Buchstaben", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        x.add(m1);
        x.add(m2);
        x.add(m3);

        mb.add(x);

        frame.setJMenuBar(mb);
        frame.setSize(500, 700);
        frame.setVisible(true);

    }
}
