package textbasedadventurejj;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

public class TestLoader {

    public static void main(String[] argv) throws IOException {

        Constants constants = new Constants();

        //gui stuff is below
        PipedInputStream inPipe = new PipedInputStream();
        PipedInputStream outPipe = new PipedInputStream();
        System.setIn(inPipe);
        PrintWriter inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
        JTextArea jta = console(outPipe, inWriter);
        PrintStream con = new PrintStream(new TextAreaOutputStream(jta));
        System.setOut(con);
        System.setErr(con);
        JFrame frame = new JFrame("Text Adventure");
        frame.add(jta);
        frame.add(new JScrollPane(jta));

        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //end gui bullshit
        Interpreter interp = Interpreter.getInstance();
        interp.addCommand("make", new MakeCommand());
        interp.addCommand("set", new SetCommand());
        interp.addCommand("if", new IfCommand());
        interp.addCommand("say", new SayCommand());
        interp.addCommand("do", new DoCommand());

        RunGame.printNewGameText();
        RunGame.runGame();
    }

    public static JTextArea console(final InputStream out, final PrintWriter in) {
        final JTextArea area = new JTextArea();

        // handle "System.out"
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                Scanner s = new Scanner(out);
                while (s.hasNextLine()) {
                    publish(s.nextLine() + "\n");
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String line : chunks) {
                    area.append(line);
                }
    // handle "System.in"
    area.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
    area.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "none");
    area.addKeyListener(new KeyAdapter() {
        private StringBuffer line = new StringBuffer();
        @Override public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_ENTER) {
                in.println(line);
                line.setLength(0); 
            } else if (c == KeyEvent.VK_BACK_SPACE) {
            	if(line.length() != 0){
            		line.setLength(line.length() - 1);
                    String text = area.getText();
                    area.setText(text.substring(0, text.length() - line.length() - 1) + line);
            	}
            } else if (!Character.isISOControl(c)) {
                line.append(e.getKeyChar());
            }
        }.execute();

        // handle "System.in"
        area.addKeyListener(new KeyAdapter() {
            private StringBuffer line = new StringBuffer();

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_ENTER) {
                    in.println(line);
                    line.setLength(0);
                } else if (c == KeyEvent.VK_BACK_SPACE) {
                    line.setLength(line.length() - 1);
                } else if (!Character.isISOControl(c)) {
                    line.append(e.getKeyChar());
                }
            }
        });

        return area;
    }
}
