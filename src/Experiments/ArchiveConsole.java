package Experiments;
import CDArchiveProject.CDRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
public class ArchiveConsole {
    JFrame window;

    public ArchiveConsole() {
        window = new JFrame("Archive Management Console");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridBagLayout());

        createUI();

        window.pack();
        window.setMinimumSize(new Dimension(700,400));
        window.setSize(new Dimension(900,600));
        window.setVisible(true);
    }

    private void createUI(){
        JLabel searchLbl = new JLabel("Search String: ");
        addComponent(window.getContentPane(),searchLbl,GridBagConstraints.BOTH,0,0,1,1,0.0f,0.0f);

        JTextField searchString = new JTextField("");
        addComponent(window.getContentPane(),searchString, GridBagConstraints.BOTH, 1,0,1,1,40.0f,0.0f);

        JButton search = new JButton("Search");
        addComponent(window.getContentPane(),search,GridBagConstraints.VERTICAL,2,0,1,1,10.0f,0.0f);

        JPanel archiveListPanel = createArchiveListPanel();
        addComponent(window.getContentPane(), archiveListPanel,
        GridBagConstraints.BOTH,0,1,3,1,70.0f,40.0f);

        JPanel archiveProcessPanel = createProcessLogPanel();
        addComponent(window.getContentPane(), archiveProcessPanel,
                GridBagConstraints.BOTH,0,2,3,1,70.0f,40.0f);

        JPanel archiveRecordPanel = createRecordPanel();
        addComponent(window.getContentPane(), archiveRecordPanel,
                GridBagConstraints.BOTH,3,0,1,2,30.0f,40.0f);

        JPanel archiveActionRequestPanel = createActionRequestPanel();
        addComponent(window.getContentPane(), archiveActionRequestPanel,
                GridBagConstraints.BOTH,3,2,1,1,30.0f,40.0f);

    }

    private JPanel createArchiveListPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.CYAN);

        JLabel titleLbl = new JLabel("Archive CDs");
        addComponent(panel, titleLbl, GridBagConstraints.BOTH, 0,0,4,1,100.0f,0.0f);



        //String[] columnNames = {"Title","Author", "Section", "X","Y","Barcode","Description", "On-Loan"};
        //DefaultTableModel recordTable = new DefaultTableModel(columnNames,0);
        JTable cdRecordTable = new JTable();
        //Set the table model here, Set Test data
        java.util.List<CDRecord> testData = CDRecord.getTestData();
        CDRecordTableModel tableData = new CDRecordTableModel(testData);
        cdRecordTable.setModel(tableData);
        cdRecordTable.setFillsViewportHeight(true);

        JScrollPane cdRecordTableScrollPane = new JScrollPane(cdRecordTable);
        addComponent(panel,cdRecordTableScrollPane,GridBagConstraints.BOTH,0,1,4,1,100.0f,10.0f);
        //cdRecordTable.getColumnModel().getColumn(0).setPreferredWidth(50);


        JLabel sortLbl = new JLabel("Sort");
        addComponent(panel,sortLbl,GridBagConstraints.BOTH,0,2,1,1,0.0f,0.0f);
        JButton sortByTitleButton = new JButton("By Title");
        addComponent(panel,sortByTitleButton, GridBagConstraints.VERTICAL,1,2,1,1,0.0f,0.0f, new Insets(0,0,0,10),GridBagConstraints.EAST);
        JButton sortByAuthorButton = new JButton("By Author");
        addComponent(panel,sortByAuthorButton, GridBagConstraints.VERTICAL,2,2,1,1,0.0f,0.0f,new Insets(0,10,0,0),GridBagConstraints.WEST);
        JButton sortByBarcodeButton = new JButton("By Barcode");
        addComponent(panel,sortByBarcodeButton, GridBagConstraints.VERTICAL,3,2,1,1,0.0f,0.0f, new Insets(0,10,0,0), GridBagConstraints.WEST);
        return panel;
    }

    private JPanel createProcessLogPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GREEN);

        JLabel ProcessLbl = new JLabel("Process Log: ");
        addComponent(panel, ProcessLbl, GridBagConstraints.BOTH, 0,0,4,1,100.0f,0.0f);
        JButton Processbtn = new JButton("Process Log");
        addComponent(panel,Processbtn,GridBagConstraints.BOTH , 4,0,1,1,15.0f,0f);
        JTextArea Process=new JTextArea();
        addComponent(panel,Process,GridBagConstraints.BOTH,0,1,5,4,20.0f,1.0f);
        JLabel Binary = new JLabel("Display Binary Tree:");
        addComponent(panel,Binary,GridBagConstraints.BOTH,0,9,1,1,15.0f,0f);
        JButton Preorder= new JButton("Pre-Order");
        addComponent(panel,Preorder,GridBagConstraints.BOTH,1,9,1,1,15f,0f);
        JButton Inorder= new JButton("In-Order");
        addComponent(panel,Inorder,GridBagConstraints.BOTH,2,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JButton Postorder= new JButton("Post-Order");
        addComponent(panel,Postorder,GridBagConstraints.BOTH,3,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JButton Graphical= new JButton("Graphical");
        addComponent(panel,Graphical,GridBagConstraints.BOTH,4,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JLabel HashMap = new JLabel("HashMap / Set:");
        addComponent(panel,HashMap,GridBagConstraints.BOTH,0,10,1,1,15.0f,0f);
        JButton Save= new JButton("Save");
        addComponent(panel,Save,GridBagConstraints.BOTH,1,10,1,1,15f,0f);
        JButton Display= new JButton("Display");
        addComponent(panel,Display,GridBagConstraints.BOTH,2,10,1,1,15f,0f);

        return panel;
    }

    private JPanel createRecordPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.MAGENTA);

        JLabel lbltitle = new JLabel("Title:");

        addComponent(panel, lbltitle,GridBagConstraints.BOTH, 0,0,0,1,0.1f,0.1f);
        JTextField txttitle = new JTextField();
        addComponent(panel, txttitle,GridBagConstraints.BOTH,1,0,2,1,1f,0.01f);
        JLabel lblAuthor = new JLabel("Author");
       addComponent(panel, lblAuthor, GridBagConstraints.BOTH,0,1,1,1,0.5f,0.01f);
        JTextField txtauthor = new JTextField();
        addComponent(panel, txtauthor,GridBagConstraints.BOTH,1,1,2,1,10f,0.01f);
        JLabel lblSection = new JLabel("Section");
        addComponent(panel,lblSection, GridBagConstraints.BOTH, 0,2,1,1,0.5f,0.1f);
        JTextField txtsection = new JTextField();
        addComponent(panel, txtsection,GridBagConstraints.BOTH,1,2,2,1,10f,0.01f);
        JLabel lblxLocation = new JLabel("X");
        addComponent(panel, lblxLocation,GridBagConstraints.BOTH, 0,3,1,1,0.5f,0.5f);
        JTextField txtxLoc = new JTextField();
        addComponent(panel, txtxLoc,GridBagConstraints.BOTH,1,3,2,1,10f,0.01f);
        JLabel lblyLocation = new JLabel("Y");
        addComponent(panel, lblyLocation,GridBagConstraints.BOTH, 0,4,1,1,0.5f,0.5f);
        JTextField txtyLoc = new JTextField();
        addComponent(panel, txtyLoc,GridBagConstraints.BOTH,1,4,2,1,10f,0.01f);
        JLabel lblBarcode = new JLabel("Barcode");
        addComponent(panel, lblBarcode, GridBagConstraints.BOTH,0,5,1,1,0.5f,0.5f);
        JTextField txtBarcode = new JTextField();
        addComponent(panel, txtBarcode,GridBagConstraints.BOTH,1,5,2,1,10f,0.01f);
        JLabel lblDesc = new JLabel("Description");
        addComponent(panel, lblDesc,GridBagConstraints.BOTH, 0,6,1,1,0.5f,0.05f);
        JTextField txtdesc = new JTextField();
        addComponent(panel, txtdesc,GridBagConstraints.BOTH,1,6,2,1,10f,0.01f);
        JButton newitem = new JButton("New Item");
        addComponent(panel, newitem,GridBagConstraints.BOTH,0,8,1,2,0f,0.1f);
        JButton saveupdate = new JButton("Save/Update");
        addComponent(panel, saveupdate,GridBagConstraints.BOTH,2,8,1,2,0f,0.1f);


        return panel;
    }

    private JPanel createActionRequestPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.RED);
        JLabel titleLbl = new JLabel("Automation Action Request for the item above:");
        addComponent(panel, titleLbl, GridBagConstraints.BOTH, 0,0,4,1,0.0f,0.0f);
        JButton Retrieve = new JButton("Retrieve");
        addComponent(panel, Retrieve,GridBagConstraints.BOTH,0,1,2,1,30.0f,0.0f);
        JButton Remove = new JButton("Remove");
        addComponent(panel, Remove,GridBagConstraints.BOTH,2,1,2,1,30.0f,0.0f);
        JButton Return = new JButton("Return");
        addComponent(panel, Return,GridBagConstraints.BOTH,0,2,2,1,30.0f,0.0f);
        JButton addCollection = new JButton("Add to Collection");
        addComponent(panel, addCollection,GridBagConstraints.BOTH,2,2,2,1,30.0f,0f);

        JLabel SortS = new JLabel("Sort Secion: ");
        addComponent(panel,SortS,GridBagConstraints.BOTH,1,3,2,1,15.0f,0f);
        JTextField SectionTxt = new JTextField();
        addComponent(panel, SectionTxt,GridBagConstraints.BOTH,3,3,2,1,20.0f,0f);
        JButton Random = new JButton("Random Collection Sort");
        addComponent(panel,Random,GridBagConstraints.BOTH,3,4,2,1,15.0f,0f);
        JButton Mostly = new JButton("Mostly Sorted Sort");
        addComponent(panel,Mostly,GridBagConstraints.BOTH,3,5,2,1,15.0f,0f);
        JButton Reverse = new JButton("Reverse Order Sort");
        addComponent(panel,Reverse,GridBagConstraints.BOTH,3,6,2,1,15.0f,0f);

        return panel;
    }

    private <C extends Component> void addComponent(
            Container contentPane, C component, int fill, int gridx, int gridy,
            int gridwidth, int gridheight, float weightx, float weighty
    ) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill=fill;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;

        contentPane.add(component, constraints);
    }

    private <C extends Component> void addComponent(
            Container contentPane, C component, int fill, int gridx, int gridy,
            int gridwidth, int gridheight, float weightx, float weighty,
            Insets insets, int anchor
    ) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill=fill;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.insets = insets;
        constraints.anchor = anchor;

        contentPane.add(component, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArchiveConsole();
            }
        });
    }

}
