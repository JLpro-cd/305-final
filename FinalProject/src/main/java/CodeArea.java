import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class CodeArea extends JPanel {

    private JScrollPane directoryPanel;
    private JScrollPane sourceCodePanel;
    private JTextPane directoryText;
    private JTextArea sourceCodeText;
    private boolean isRunPressed = false;
    private Thread hyperLinkListenerThread;

    public CodeArea() {

        setLayout(new BorderLayout());
        directoryPanel = new JScrollPane();
        sourceCodePanel = new JScrollPane();
        directoryText = new JTextPane();
        sourceCodeText = new JTextArea();

        directoryText.setEditable(false);
        sourceCodeText.setEditable(true);

        directoryText.setAutoscrolls(true);
        sourceCodeText.setAutoscrolls(true);

        directoryText.setContentType("text/html");
        //directoryText.setText("<html><a href='file1'>File 1</a><br><a href='file2'>File 2</a><br><a href='file3'>File 3</a></html>");

        sourceCodePanel.setViewportView(sourceCodeText);//, BorderLayout.EAST);
        directoryPanel.setViewportView(directoryText);//, BorderLayout.WEST);

        sourceCodePanel.setVisible(true);
        directoryPanel.setVisible(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, directoryPanel, sourceCodePanel);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(600);
        splitPane.setResizeWeight(0.5);

        setBackground(Color.WHITE);
        setVisible(true);

    }

    public void detectActions(){

        directoryText.setText(setDirectoryLinks());

        if(!isRunPressed){

            startThread();
            isRunPressed = true;
        }

    }

    public String setDirectoryLinks(){

        String htmlString = "";

        for(int i = 0; i < Blackboard.getInstance().getNodes().size(); i++){

            Blackboard.getInstance().getClassCode().put(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel(), new ArrayList<String>());

            htmlString += "<html><a href='file"+i+"'>"
                    + Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel() + ".java"
                    + "</a><br><a";

        }
        htmlString += "</a></html>";

        System.out.println(htmlString);

        return htmlString;

    }


    //sorry you have to read this code bro
    public void startThread(){

        hyperLinkListenerThread = new Thread(() -> {
            while (isRunPressed) {
                directoryText.addHyperlinkListener(e -> {
                    if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                        String clickedText = e.getDescription();

                        ObserverHandler handler = new ObserverHandler();

                        for (int i = 0; i < Blackboard.getInstance().getNodes().size(); i++) {
                            String fileRef = "file" + i;

                            if (fileRef.equals(clickedText)) {
                                String code = "public class " + Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel() + "\n"; // doesn't put first semicolon
                                //loop through decorator list, handle each decorator, build up a huge string, setText to that string
                                for(int j = 0; j < Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getDecorators().size(); j++){
                                    handler.Handle(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getDecorators().get(j));
                                }

                                for(int j = 0; j < Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel()).size(); j++){
                                    if(!code.contains(Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel()).get(j))) {
                                        code += Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel()).get(j);
                                    }
                                }


                                sourceCodeText.setText(code);

                            }
                        }
                    }
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        hyperLinkListenerThread.start();

    }

    public void runResponse(){

        detectActions();

    }


}
