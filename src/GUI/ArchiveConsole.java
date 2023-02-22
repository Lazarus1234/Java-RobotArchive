package GUI;
import CDArchiveProject.CDRecord;
import CDArchiveProject.RecordStorage;
import Experiments.CDRecordTableModel;
import Sockets.AutomationConsole;
import Sockets.Client;
import Sockets.MessageListener;
import Sockets.MessageSender;
import Sorting.BubbleSort;
import Sorting.Insertion;
import Sorting.Selection;

import javax.swing.*;
import javax.swing.plaf.synth.Region;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Trees.BinaryTree;
import Lists.DoublyLinkedList;

public class ArchiveConsole {
    //<editor-fold desc="Member Fields">
    JFrame window;
    List<CDRecord> records;
    BinaryTree tree;
    DoublyLinkedList Dll = new DoublyLinkedList();
    JTable cdRecordTable;
    JTextArea processList;
    Client client;
    AutomationConsole Auto;
    CDRecord selectedRecord;
    RecordStorage save;
    HashMap<Integer, String> hashMap = new HashMap<>();


    AbstractTableModel tableData;
    JTextField txttitle, txtauthor, txtxLoc,txtsection,txtyLoc,txtBarcode,txtdesc;
    JButton Save, newitem, saveupdate, Processbtn;
    int selectedIndex = -1;
    JList Process;
    boolean prcActive = false;
    //ArrayList<String> processRecord = new ArrayList<>();
    //</editor-fold>


    //<editor-fold desc="Archive Console">
    public ArchiveConsole() {
        records = RecordStorage.loadRecordList("records.Data");


        window = new JFrame("Archive Management Console");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridBagLayout());

        createUI();
        client = new Client("localhost:20000", new MessageListener() {
            @Override
            public void message(String msg, MessageSender sender) {
                Dll.append(new DoublyLinkedList.Node("Log entry text:  " + msg + "\n"));
                processList.setText(Dll.toString() +"\n");
                processList.setLineWrap(true);
            }
        });

        window.pack();
        window.setMinimumSize(new Dimension(700,400));
        window.setSize(new Dimension(900,500));
        window.setVisible(true);
    }
    //</editor-fold>


    //<editor-fold desc="User Interface">
    private void createUI(){
        JLabel searchLbl = new JLabel("Search String: ");
        addComponent(window.getContentPane(),searchLbl,GridBagConstraints.BOTH,0,0,1,1,0.0f,0.0f);

        JTextField searchString = new JTextField("");
        addComponent(window.getContentPane(),searchString, GridBagConstraints.BOTH, 1,0,1,1,40.0f,0.0f);

        JButton search = new JButton("Search");
        addComponent(window.getContentPane(),search,GridBagConstraints.VERTICAL,2,0,1,1,10.0f,0.0f);

        final TableRowSorter<CDRecordTableModel> sorter = new TableRowSorter(tableData);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String search = searchString.getText();
                try{
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)"+search));
                } catch (Exception e){
                    System.out.println("Error in search: "+e.toString());
                }
            }
        });
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

        JLabel Messagelbl = new JLabel("Show Message Labels: ");
        addComponent(window.getContentPane(),Messagelbl,GridBagConstraints.BOTH,0,3,1,1,5f,0f);
        JCheckBox Check = new JCheckBox();
        addComponent(window.getContentPane(),Check,GridBagConstraints.BOTH,1,3,1,1,0f,00f);

        JButton Exit = new JButton("Exit");
        addComponent(window.getContentPane(),Exit,GridBagConstraints.BOTH,3,3,2,1,0f,0f);

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
                System.exit(0);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc="Archive List Panel">
    private JPanel createArchiveListPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.CYAN);

        JLabel titleLbl = new JLabel("Archive CDs");
        addComponent(panel, titleLbl, GridBagConstraints.BOTH, 0,0,4,1,100.0f,0.0f);




        cdRecordTable = new JTable();
        cdRecordTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                selectedIndex = cdRecordTable.getSelectedRow();
                selectedRecord = records.get(selectedIndex);
                //txtTitle.setText(selectedRecord.getTitle())
                txttitle.setText(selectedRecord.getTitle());
                txtauthor.setText(selectedRecord.getAuthor());
                txtsection.setText(selectedRecord.getSection());
                txtxLoc.setText(Integer.toString(selectedRecord.getxLocation()));
                txtyLoc.setText(Integer.toString(selectedRecord.getyLocation()));
                txtBarcode.setText(Integer.toString(selectedRecord.getBarcode()));
                txtdesc.setText(selectedRecord.getDescription());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


        tableData = new CDRecordTableModel(records);
        cdRecordTable.setModel(tableData);
        cdRecordTable.setFillsViewportHeight(true);

        JScrollPane cdRecordTableScrollPane = new JScrollPane(cdRecordTable);
        addComponent(panel,cdRecordTableScrollPane,GridBagConstraints.BOTH,0,1,4,1,100.0f,10.0f);



        JLabel sortLbl = new JLabel("Sort");
        addComponent(panel,sortLbl,GridBagConstraints.BOTH,0,2,1,1,0.0f,0.0f);
        JButton sortByTitleButton = new JButton("By Title");
        sortByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BubbleSort.sort(records);
                // This button also seeds the binary tree in order to randomize the barcodes
                tree=new BinaryTree();
                for (CDRecord record: records){
                    tree.insert(new Trees.BinaryTree.Node(record.getBarcode(), record));
                }
                tableData.fireTableDataChanged();
            }
        });
        addComponent(panel,sortByTitleButton, GridBagConstraints.VERTICAL,1,2,1,1,0.0f,0.0f, new Insets(0,0,0,10),GridBagConstraints.EAST);

        JButton sortByAuthorButton = new JButton("By Author");
        sortByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Insertion.sort(records);
                tableData.fireTableDataChanged();
            }
        });
        addComponent(panel,sortByAuthorButton, GridBagConstraints.VERTICAL,2,2,1,1,0.0f,0.0f,new Insets(0,10,0,0),GridBagConstraints.WEST);
        JButton sortByBarcodeButton = new JButton("By Barcode");
        sortByBarcodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Selection.sort(records);
                tableData.fireTableDataChanged();
            }
        });
        addComponent(panel,sortByBarcodeButton, GridBagConstraints.VERTICAL,3,2,1,1,0.0f,0.0f, new Insets(0,10,0,0), GridBagConstraints.WEST);
        return panel;
    }
    //</editor-fold>

    //<editor-fold desc="Process Log Panel">
    private JPanel createProcessLogPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.YELLOW);

        JLabel ProcessLbl = new JLabel("Process Log: ");
        addComponent(panel, ProcessLbl, GridBagConstraints.BOTH, 0,0,4,1,100.0f,0.0f);
        Processbtn = new JButton("Process Log");
        addComponent(panel,Processbtn,GridBagConstraints.BOTH , 4,0,1,1,15.0f,0f);
        Processbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {



                processList.setText(Dll.toString());


            }
        });

        //this.Process=new JList();
        processList =new JTextArea(8,20);
        processList.setLineWrap(true);
        JScrollPane process=new JScrollPane(processList);
        addComponent(panel,process,GridBagConstraints.BOTH,1,1,5,4,20.0f,1.0f);
        JLabel Binary = new JLabel("Display Binary Tree:");
        addComponent(panel,Binary,GridBagConstraints.BOTH,0,9,1,1,15.0f,0f);
        JButton Preorder= new JButton("Pre-Order");
        // Pre order
        Preorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                //processList.setText(tree.traversePreOrder().toString());

                tree=new BinaryTree();
                for (CDRecord record: records){
                    tree.insert(new Trees.BinaryTree.Node(record.getBarcode(), record));

                }
                ArrayList<BinaryTree.Node> treeArray = tree.traversePreOrder();
                for (int i = 0; i<treeArray.size(); i++){

                    processList.append(treeArray.get(i).toString()+  "\n");


                }


            }
        });

        addComponent(panel,Preorder,GridBagConstraints.BOTH,1,9,1,1,15f,0f);
        JButton Inorder= new JButton("In-Order");
        // In order
        Inorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tree=new BinaryTree();
                for (CDRecord record: records){
                    tree.insert(new Trees.BinaryTree.Node(record.getBarcode(), record));

                }
                ArrayList<BinaryTree.Node> treeArray = tree.traverseInOrder();
                for (int i = 0; i<treeArray.size(); i++){

                    processList.append(treeArray.get(i).toString()+  "\n");

                }
            }
        });


        addComponent(panel,Inorder,GridBagConstraints.BOTH,2,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JButton Postorder= new JButton("Post-Order");
        // Post Order
        Postorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tree=new BinaryTree();
                for (CDRecord record: records){
                    tree.insert(new Trees.BinaryTree.Node(record.getBarcode(), record));

                }
                ArrayList<BinaryTree.Node> treeArray = tree.traversePostOrder();
                for (int i = 0; i<treeArray.size(); i++){

                    processList.append(treeArray.get(i).toString() +  "\n");

                }
            }
        });
        addComponent(panel,Postorder,GridBagConstraints.BOTH,3,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JButton Graphical= new JButton("Graphical");
        addComponent(panel,Graphical,GridBagConstraints.BOTH,4,9,1,1,15f,0f,new Insets(0,5,0,0),GridBagConstraints.WEST);
        JLabel HashMap = new JLabel("HashMap / Set:");
        addComponent(panel,HashMap,GridBagConstraints.BOTH,0,10,1,1,15.0f,0f);
        JButton Save= new JButton("Save");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    HashMap<Integer, String> hashMap =new HashMap<>();
                    for (int i= 0; i<records.size(); i++){
                        hashMap.put(records.get(i).getBarcode(), records.get(i).getTitle() + "\n");
                    }
                    FileWriter fw =new FileWriter("HashMapFile.Data");
                    fw.write(hashMap.toString());
                    fw.close();



                      //Simple Solution to Save Hash Map as Text file, using another method
                    // from RecordStorage.
                    //RecordStorage.saveHashMap("recordsHashMap3.Data", processList.getText());



                } catch (Exception e){
                    System.err.println("Failed to save records:" +e.toString());
                }





            }
        });
        addComponent(panel,Save,GridBagConstraints.BOTH,1,10,1,1,15f,0f);
        JButton Display= new JButton("Display");
        Display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                 hashMap = new HashMap<>();
                for (int i=0; i< records.size(); i++){
                    hashMap.put(records.get(i).getBarcode(), records.get(i).getTitle() +"\n");
                    processList.setLineWrap(true);
                }
                processList.setText("");
                processList.append("HashMap: <<" + hashMap.toString() + ">>" + "\n");
                processList.setLineWrap(true);
            }
        });
        addComponent(panel,Display,GridBagConstraints.BOTH,2,10,1,1,15f,0f);

        return panel;
    }
    //</editor-fold>


    //<editor-fold desc="Record Panel">
    private JPanel createRecordPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.MAGENTA);




        JLabel lbltitle = new JLabel("Title:");
        addComponent(panel, lbltitle,GridBagConstraints.BOTH, 0,0,1,1,1f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txttitle = new JTextField();
        addComponent(panel, txttitle,GridBagConstraints.HORIZONTAL,1,0,1,1,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblAuthor = new JLabel("Author");
       addComponent(panel, lblAuthor, GridBagConstraints.BOTH,0,1,1,1,0.5f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtauthor = new JTextField();
        addComponent(panel, txtauthor,GridBagConstraints.HORIZONTAL,1,1,1,1,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblSection = new JLabel("Section");
        addComponent(panel,lblSection, GridBagConstraints.BOTH, 0,2,1,1,0.5f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtsection = new JTextField();
        addComponent(panel, txtsection,GridBagConstraints.HORIZONTAL,1,2,1,1,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblxLocation = new JLabel("X");
        addComponent(panel, lblxLocation,GridBagConstraints.BOTH, 0,3,1,1,0.5f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtxLoc = new JTextField();
        addComponent(panel, txtxLoc,GridBagConstraints.HORIZONTAL,1,3,1,1,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblyLocation = new JLabel("Y");
        addComponent(panel, lblyLocation,GridBagConstraints.BOTH, 0,4,1,1,0.5f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtyLoc = new JTextField();
        addComponent(panel, txtyLoc,GridBagConstraints.HORIZONTAL,1,4,1,1,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblBarcode = new JLabel("Barcode");
        addComponent(panel, lblBarcode, GridBagConstraints.BOTH,0,5,1,1,0.5f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtBarcode = new JTextField();
        addComponent(panel, txtBarcode,GridBagConstraints.HORIZONTAL,1,5,1,1,10f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        JLabel lblDesc = new JLabel("Description");
        addComponent(panel, lblDesc,GridBagConstraints.BOTH, 0,7,1,1,0f,20.01f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        txtdesc = new JTextField();
        addComponent(panel, txtdesc,GridBagConstraints.BOTH,1,7,1,2,10f,20f,new Insets(0,0,1,0),GridBagConstraints.NORTH);
        newitem = new JButton("New Item");
        newitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txttitle.setText("");
                txtauthor.setText("");
                txtsection.setText("");
                txtxLoc.setText("");
                txtyLoc.setText("");
                txtBarcode.setText("");
                txtdesc.setText("");
                selectedIndex =-1;

            }
        });
        addComponent(panel, newitem,GridBagConstraints.BOTH,0,8,1,2,0f,0.1f, new Insets(0,0,25,0),GridBagConstraints.NORTH);
        saveupdate = new JButton("Save/Update");
        addComponent(panel, saveupdate,GridBagConstraints.BOTH,2,8,1,2,0f,0.1f,new Insets(0,0,25,0),GridBagConstraints.NORTH);

        saveupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //int selectedIndex = cdRecordTable.getSelectedRow();



                if (selectedIndex == -1){
                    CDRecord focusRecord = new CDRecord(
                            txttitle.getText(),
                            txtauthor.getText(),
                            txtsection.getText(),
                            Integer.parseInt(txtxLoc.getText()),
                            Integer.parseInt(txtyLoc.getText()),
                            Integer.parseInt(txtBarcode.getText()),
                            txtdesc.getText(),
                            false);

                    RecordStorage.saveRecordList("recordsNEW.Data", records);






                            records.add(focusRecord);



                } else {
                    tableData.setValueAt(txttitle.getText(), selectedIndex,0);
                    tableData.setValueAt(txtauthor.getText(), selectedIndex,1);
                    tableData.setValueAt(txtxLoc.getText(), selectedIndex,2);
                    tableData.setValueAt(txtyLoc.getText(), selectedIndex,3);
                    tableData.setValueAt(txtBarcode.getText(), selectedIndex,4);
                    tableData.setValueAt(txtdesc.getText(), selectedIndex,5);
                    records.get(selectedIndex).setTitle(txttitle.getText());
                    records.get(selectedIndex).setAuthor(txtauthor.getText());
                    records.get(selectedIndex).setSection(txtsection.getText());
                    records.get(selectedIndex).setxLocation(Integer.parseInt(txtxLoc.getText()));
                    records.get(selectedIndex).setyLocation(Integer.parseInt(txtyLoc.getText()));
                    records.get(selectedIndex).setBarcode(Integer.parseInt(txtBarcode.getText()));
                    records.get(selectedIndex).setDescription(txtdesc.getText());


                    //focusRecord.setTitle(txttitle.getText());

                }

                tableData.fireTableDataChanged();
                RecordStorage.saveRecordList("recordsNEW.Data", records);




            }
        });


        return panel;
    }
    //</editor-fold>



    //<editor-fold desc="Action Request Panel">
    private JPanel createActionRequestPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.RED);
        JLabel titleLbl = new JLabel("Automation Action Request for the item above:");
        addComponent(panel, titleLbl, GridBagConstraints.BOTH, 2,0,8,1,70.0f,10f);
        JButton Retrieve = new JButton("Retrieve");

        Retrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedRecord ==null){
                    client.sendMessage("Select a record to retrieve");

                }

                client.sendMessage(   "Retrieve:" + selectedRecord.getBarcode()+ ":" +selectedRecord.getSection() );
            }
        });
        addComponent(panel, Retrieve,GridBagConstraints.HORIZONTAL,2,1,2,1,50.0f,10f);
        JButton Remove = new JButton("Remove");
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedRecord ==null){
                    client.sendMessage("Select a record to remove");
                }
                client.sendMessage(  "Remove:"  + selectedRecord.getBarcode()+ ":" +selectedRecord.getSection() );
            }
        });
        addComponent(panel, Remove,GridBagConstraints.HORIZONTAL,4,1,1,1,30f,10f, new Insets(0,0,0,80), GridBagConstraints.NORTH);
        JButton Return = new JButton("Return");
        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedRecord ==null){
                    client.sendMessage("Select a record to return");
                }
                client.sendMessage(   "Return:" +selectedRecord.getBarcode()+ ":" +selectedRecord.getSection() );
            }
        });
        addComponent(panel, Return,GridBagConstraints.HORIZONTAL,2,2,2,1,50.0f,10f);
        JButton addCollection = new JButton("Add to Collection");
        addCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectedRecord ==null){
                    client.sendMessage("Select a record to add");
                }
                client.sendMessage(   "Add:" +selectedRecord.getBarcode()+ ":" +selectedRecord.getSection() );


            }
        });
        addComponent(panel, addCollection,GridBagConstraints.HORIZONTAL,4,2,1,1,30f,10f,new Insets(0,0,0,80), GridBagConstraints.NORTH);

        JLabel SortS = new JLabel("Sort Section: ");
        addComponent(panel,SortS,GridBagConstraints.HORIZONTAL,3,3,2,1,15.0f,50f );
        JTextField SectionTxt = new JTextField();
        addComponent(panel, SectionTxt,GridBagConstraints.HORIZONTAL,4,3,2,1,15.0f,50f,new Insets(0,0,0,10), GridBagConstraints.NORTH);
        JButton Random = new JButton("Random Collection Sort");
        Random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(window, "Sorting a collection");
                client.sendMessage(   "Sort:" +selectedRecord.getBarcode()+ ":" +selectedRecord.getSection() );
            }
        });
        addComponent(panel,Random,GridBagConstraints.HORIZONTAL,4,4,1,1,15.0f,50f,new Insets(0,0,0,10), GridBagConstraints.NORTH);
        JButton Mostly = new JButton("Mostly Sorted Sort");
        Mostly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(window, "Mostly sorting");
            }
        });
        addComponent(panel,Mostly,GridBagConstraints.HORIZONTAL,4,5,1,1,15.0f,50f,new Insets(0,0,0,10), GridBagConstraints.NORTH);
        JButton Reverse = new JButton("Reverse Order Sort");
        Reverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(window, "Reversing the order Sort");
            }
        });
        addComponent(panel,Reverse,GridBagConstraints.HORIZONTAL,4,6,1,1,15.0f,50f,new Insets(0,0,0,10), GridBagConstraints.NORTH);

        return panel;
    }
    //</editor-fold>

    //<editor-fold desc="Grid Bag Layout">
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
    //</editor-fold>

    //<editor-fold desc="Frame">
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArchiveConsole();
                new AutomationConsole();
            }

        });
    }


void AutomationConsole(int row) {
    this.CDAutomation();
}

    void CDAutomation(){
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run(){
            new AutomationConsole();

        }
    });
   }
    //</editor-fold>


}
