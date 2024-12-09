import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MenuBar extends JPanel {
    JButton fileButton;
    JButton boxButton;
    JButton toolsButton;
    JButton helpButton;

    public MenuBar(){

        JPopupMenu fileMenu = new JPopupMenu("File");

        JMenuItem newItem = new JMenuItem("New...");
        JMenuItem openItem = new JMenuItem("Open...");
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem saveItem = new JMenuItem("Save");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(saveItem);

        JPopupMenu boxConnectorMenu = new JPopupMenu("Box Connector");

        JMenuItem aggregationItem = new JMenuItem("Aggregation");
        JMenuItem compositionItem = new JMenuItem("Composition");
        JMenuItem associationItem = new JMenuItem("Association");
        JMenuItem inheritanceItem = new JMenuItem("Inheritance");
        JMenuItem RealizationItem = new JMenuItem("Realization");

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
        toolsButton.addActionListener(e -> toolsMenu.show(toolsButton, 0, toolsButton.getHeight()));
        helpButton.addActionListener(e -> helpMenu.show(helpButton, 0, helpButton.getHeight()));

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(fileButton);
        buttonPanel.add(boxButton);
        buttonPanel.add(toolsButton);
        buttonPanel.add(helpButton);

        add(buttonPanel, BorderLayout.PAGE_START);


    }



}
