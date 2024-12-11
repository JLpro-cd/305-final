import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * FileCode handles all file operations
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 * */

public class FileCode {

    private CodeArea codeArea;

    public FileCode(CodeArea c){
        this.codeArea = c;
    }

    /**
     * Clears repository and resets program visually
     * */

    public void newFile(){

        Blackboard.getInstance().clearBlackboard();
        if(codeArea.getHyperLinkListenerThread() != null){
            codeArea.getHyperLinkListenerThread().interrupt();
        }
        codeArea.setIsRunPressed(false);
        codeArea.getDirectoryText().setText("");
        codeArea.getSourceCodeText().setText("");
        Blackboard.getInstance().repaint();

    }

    /**
     * Serializes repository and saves .ser file on desktop with user passed fileName
     * */

    public void fileSave(String fileName){

            if(!fileName.equals("")){
                File file = FileSystemView.getFileSystemView().getHomeDirectory();
                String desktopPath = file.getPath();
                File serFile = new File(desktopPath + File.separator + fileName + ".ser");
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFile))){
                    oos.writeObject(Blackboard.getInstance().getNodes());
                    oos.writeObject(Blackboard.getInstance().getClassCode());
                    oos.writeObject(Blackboard.getInstance().getNodeLines());
                    oos.writeObject(Blackboard.getInstance().getDecoratorLines());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

    }

    /**
     * Opens file from Desktop of .ser type with user given fileName
     * */

    public void openFile(String fileName) throws FileNotFoundException {

            if(!fileName.equals("")){
                File file = FileSystemView.getFileSystemView().getHomeDirectory();
                String desktopPath = file.getPath();
                File serFile = new File(desktopPath + File.separator + fileName + ".ser");
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile))){

                    Blackboard.getInstance().clearBlackboard();
                    Blackboard.getInstance().setNodes((ArrayList<Component>) ois.readObject());
                    Blackboard.getInstance().setClassCode((HashMap<String, CodeSections>) ois.readObject());
                    Blackboard.getInstance().setNodeLines((ArrayList<NodeLine>) ois.readObject());
                    Blackboard.getInstance().setDecoratorLines((ArrayList<ArrayList<Point>>) ois.readObject());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

        Blackboard.getInstance().repaint();
    }




}
