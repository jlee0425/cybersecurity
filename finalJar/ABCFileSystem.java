package finalJar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.showMessageDialog;

public class ABCFileSystem extends JFrame implements ActionListener {
    JFileChooser fc;
    JButton btnFind;
    JButton btnChoose;
    JButton btnSave;
    JButton btnUpload;
    JLabel lbFile;
    private String userName, userRole, userBranch;

    public void buildGUI() {
        setLayout(new BorderLayout());
        JPanel filePanel = new JPanel();
        JPanel lbPanel = new JPanel();
        JPanel actionPanel = new JPanel();

        btnFind = new JButton("Find a file from FS");
        btnChoose = new JButton("Select a file to upload");
        btnSave = new JButton("Download");
        btnUpload = new JButton("Upload");
        btnFind.addActionListener(this);
        btnChoose.addActionListener(this);
        btnSave.addActionListener(this);
        btnUpload.addActionListener(this);
        filePanel.add(btnFind);
        filePanel.add(btnChoose);
        lbFile = new JLabel("...");
        lbPanel.add(lbFile);
        actionPanel.add(btnSave);
        actionPanel.add(btnUpload);
        

        setLayout(new BorderLayout());
        add(filePanel, BorderLayout.PAGE_START);
        add(lbPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.PAGE_END);
        setTitle("ABC Airlines File System");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ABCFileSystem(String name, String branch, String role) {
        buildGUI();
        this.userName = name;
        this.userRole = role;
        this.userBranch = branch;
        fc = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int retVal;
        String accessiblePath = getAccessiblePath();
        Object src = e.getSource();
        if (src == btnFind) {
            File fileRoot = new File(accessiblePath);
            fc.setCurrentDirectory(fileRoot);
            if (fc.getComponentCount() == 4) fc.remove(0);
            retVal = fc.showDialog(ABCFileSystem.this, "Open");
            if (retVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                lbFile.setText(file.getName());
            }
        } else if (src == btnChoose) {
            fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Documents"));
            retVal = fc.showDialog(ABCFileSystem.this, "Save");
            if (retVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                lbFile.setText(file.getName());
            }
        } else if (src == btnUpload) {
            File file = fc.getSelectedFile();
            Path path = Paths.get(accessiblePath);
            try {
                Files.copy(file.toPath(), path.resolve(file.getName()));
                AccessControl.generateLog(userName, " Uploaded a file: " + file.getName());
            } catch (Exception e1) {
                showMessageDialog(null, "Please select a file");
            }
        } else if (src == btnSave) {
            File file = fc.getSelectedFile();
            Path path = Paths.get(System.getProperty("user.home") + "\\Downloads");
            try {
                Files.copy(file.toPath(), path.resolve(file.getName()));
                AccessControl.generateLog(userName, "Downloaded a file: " + file.getName());
            } catch (Exception e1) {
                showMessageDialog(null, "Please select a file");
            }
        }
    }
    private String getAccessiblePath(){
        String path = System.getProperty("user.dir") + "\\ABCFS";
        if (!userRole.equalsIgnoreCase(("Custodian")))
            path += "\\" + userBranch + "\\" + userRole;
        return path;
    }
    public static void main(String[] args) {
        new ABCFileSystem("user", "Vancouver", "custodian");
    }
}
