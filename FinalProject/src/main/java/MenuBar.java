import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Menu at the top of the program which holds various options and calls methods accordingly
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class MenuBar extends JPanel {
    JButton fileButton;
    JButton boxButton;
    JButton toolsButton;
    JButton helpButton;
    private CodeArea codeArea;
    private FileCode fileCode;

    public MenuBar(CodeArea codeArea, FileCode fileCode) {

        this.codeArea = codeArea;
        this.fileCode = fileCode;
        JPopupMenu fileMenu = new JPopupMenu("File");

        JMenuItem newItem = new JMenuItem("New...");
        JMenuItem openItem = new JMenuItem("Open...");
        JMenuItem saveAsItem = new JMenuItem("Save As...");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveAsItem);

        JPopupMenu boxConnectorMenu = new JPopupMenu("Box Connector");

        JMenuItem aggregationItem = new JMenuItem("Aggregation");
        JMenuItem compositionItem = new JMenuItem("Composition");
        JMenuItem associationItem = new JMenuItem("Association");
        JMenuItem inheritanceItem = new JMenuItem("Inheritance");
        JMenuItem RealizationItem = new JMenuItem("Realization");

        aggregationItem.addActionListener(e -> Blackboard.getInstance().setCurrentNodeConnectionType("Aggregation"));
        compositionItem.addActionListener(e -> Blackboard.getInstance().setCurrentNodeConnectionType("Composition"));
        associationItem.addActionListener(e -> Blackboard.getInstance().setCurrentNodeConnectionType("Association"));
        inheritanceItem.addActionListener(e -> Blackboard.getInstance().setCurrentNodeConnectionType("Inheritance"));
        RealizationItem.addActionListener(e -> Blackboard.getInstance().setCurrentNodeConnectionType("Realization"));

        boxConnectorMenu.add(aggregationItem);
        boxConnectorMenu.add(compositionItem);
        boxConnectorMenu.add(associationItem);
        boxConnectorMenu.add(inheritanceItem);
        boxConnectorMenu.add(RealizationItem);

        JPopupMenu toolsMenu = new JPopupMenu("Tools");

        JMenuItem runItem = new JMenuItem("Run");

        toolsMenu.add(runItem);

        JPopupMenu helpMenu = new JPopupMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");

        helpMenu.add(aboutItem);


        fileButton = new JButton("File");
        boxButton = new JButton("Box Connector");
        toolsButton = new JButton("Tools");
        helpButton = new JButton("Help");


        fileButton.addActionListener(e -> fileMenu.show(fileButton, 0, fileButton.getHeight()));
        boxButton.addActionListener(e -> boxConnectorMenu.show(boxButton, 0, boxButton.getHeight()));
        toolsButton.addActionListener(e -> {
            toolsMenu.show(toolsButton, 0, toolsButton.getHeight());

        });
        helpButton.addActionListener(e -> helpMenu.show(helpButton, 0, helpButton.getHeight()));

        runItem.addActionListener(e -> {
            codeArea.runResponse();
        });

        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"Written by Josue Lopez and Brendan Holt");
        });

        newItem.addActionListener(e -> {
           fileCode.newFile();
        });

        saveAsItem.addActionListener(e -> {
           String fileName = JOptionPane.showInputDialog(this, "Please enter name of file");
           fileCode.fileSave(fileName);
        });

        openItem.addActionListener(e->{
            String fileName = JOptionPane.showInputDialog(this, "Please enter name of file");
            try {
                fileCode.openFile(fileName);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(fileButton);
        buttonPanel.add(boxButton);
        buttonPanel.add(toolsButton);
        buttonPanel.add(helpButton);

        add(buttonPanel, BorderLayout.PAGE_START);


    }



}
