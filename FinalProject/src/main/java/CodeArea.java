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

            Blackboard.getInstance().getClassCode().put(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel(), new CodeSections());

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

                        ObservableHandler observableHandler = new ObservableHandler();
                        ObserverHandler observerHandler = new ObserverHandler();
                        ChainMemberHandler chainMemberHandler = new ChainMemberHandler();
                        DecoratableHandler decoratableHandler = new DecoratableHandler();
                        DecorationHandler decorationHandler = new DecorationHandler();
                        StrategyHandler strategyHandler = new StrategyHandler();
                        SingletonHandler singletonHandler = new SingletonHandler();
                        FactoryHandler factoryHandler = new FactoryHandler();
                        ProductHandler productHandler = new ProductHandler();


                        observableHandler.setSuccessor(observerHandler);
                        observerHandler.setSuccessor(chainMemberHandler);
                        chainMemberHandler.setSuccessor(decoratableHandler);
                        decoratableHandler.setSuccessor(decorationHandler);
                        decorationHandler.setSuccessor(strategyHandler);
                        strategyHandler.setSuccessor(singletonHandler);
                        singletonHandler.setSuccessor(factoryHandler);
                        factoryHandler.setSuccessor(productHandler);



                        for (int i = 0; i < Blackboard.getInstance().getNodes().size(); i++) {
                            String fileRef = "file" + i;

                            if (fileRef.equals(clickedText)) {
                                Node baseNode = Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i));
                                String extensionCode = "public class " + baseNode.getLabel() + "\n"; // doesn't put first semicolon
                                String classCode = "";
                                Decorator[] decorations = baseNode.getDecoratorHolder().getDecorators();

                                //loop through decorator list, handle each decorator, build up a huge string, setText to that string
                                for (int j = 0; j < decorations.length; j++) {
                                    Decorator currentDecorator = decorations[j];
                                    if (currentDecorator == null) { continue; }
                                    observableHandler.Handle(currentDecorator);
                                }

                                for (int j = 0; j < Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getExtensions().size(); j++) { // extensions first
                                    if(!extensionCode.contains(Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getExtensions().get(j))) {
                                        extensionCode += Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getExtensions().get(j);
                                    }
                                }

                                extensionCode += "{\n\n";

                                for(int j = 0; j < Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getClassCode().size(); j++){ // class code next
                                    if(!classCode.contains(Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getClassCode().get(j))) {
                                        classCode += Blackboard.getInstance().getClassCode().get(baseNode.getLabel()).getClassCode().get(j);
                                    }
                                }

                                classCode += "}";


                                sourceCodeText.setText(extensionCode + classCode);

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
