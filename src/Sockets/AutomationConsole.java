package Sockets;
import CDArchiveProject.CDRecord;
import CDArchiveProject.RecordStorage;
import Experiments.CDRecordTableModel;
import Lists.DoublyLinkedList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;


public class AutomationConsole {
    JFrame window;
    Client client =new Client("localhost:20000", new MessageListener() {
        @Override
        public void message(String msg, MessageSender sender) {
            if (msg.contains("Sort")){
                JOptionPane.showMessageDialog(window, "Sorting");
            }

        }
    });
    int selectedIndex =-1;
    JTextField field, sectfield;
    JTable cdRecordTable;
    AbstractTableModel tableData;
    CDRecord selectedRecord;


    ArrayList<String> chatHistoryData;
    List<CDRecord> records;
    DoublyLinkedList Dll;
    String[] ActionList = {"Add", "Retrieve", "Return", "Remove", "Sort"};
    String outMessage = "";


    public AutomationConsole() {
        records = RecordStorage.loadRecordList("records.Data");
        window = new JFrame("Automation Console");
        window.setMinimumSize(new Dimension(400, 400));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = window.getContentPane();
        content.setLayout(new GridBagLayout());

        chatHistoryData = new ArrayList<>();

        //ui elements
        createUI();

        window.pack();
        window.setVisible(true);
    }

    private void createUI() {
        JLabel currentAction = new JLabel("Current Requested Action");
        addComponent(window.getContentPane(), currentAction, GridBagConstraints.BOTH, 0, 0, 2, 1, 20f, 0f);
        JComboBox cboAction = new JComboBox(ActionList);
        cboAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cboAction.getSelectedIndex();
            }
        });

        addComponent(window.getContentPane(), cboAction, GridBagConstraints.BOTH, 2, 0, 2, 1, 20f, 0f);
        JButton process = new JButton("Process");
        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                switch (cboAction.getSelectedIndex()){
                    case 0: outMessage += "Add: "; break;
                    case 1: outMessage += "Retrieve: "; break;
                    case 2: outMessage += "Return: "; break;
                    case 3: outMessage += "Remove: "; break;
                    case 4: outMessage += "Sort: "; break;
                }
                outMessage +=dateTime() +" " +  tableData.getValueAt(cdRecordTable.getSelectedRow(),0)   + "\n";

                if (client !=null) {
                    client.sendMessage(outMessage);
                }





            }
        });
        addComponent(window.getContentPane(), process, GridBagConstraints.BOTH, 5, 0, 2, 1, 1f, 0f);
        //process.addActionListener(new ActionListener() {
            /*@Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Connecting with " + serverAddress.getText());

                client = new Client(serverAddress.getText(), new MessageListener() {
                @Override
                public void message(String msg, MessageSender sender) {
                // add the new message to the chat history
                chatHistoryData.add(msg);
                chatHistory.setListData(chatHistoryData.toArray());

                 }
                });*/

           // }


        //});

        JLabel barcodelbl = new JLabel("Barcode of Selected Item: ");
        addComponent(window.getContentPane(), barcodelbl, GridBagConstraints.BOTH, 1, 1, 0, 1, 10f, 20f);
        field = new JTextField();
        addComponent(window.getContentPane(), field, GridBagConstraints.HORIZONTAL, 2, 1, 1, 1, 20f, 20f);
        JLabel section = new JLabel("Section");
        addComponent(window.getContentPane(), section, GridBagConstraints.BOTH, 4, 1, 1, 1, 20f, 20f);


        sectfield = new JTextField();
        addComponent(window.getContentPane(), sectfield, GridBagConstraints.HORIZONTAL, 5, 1, 2, 1, 1f, 1f);
        JButton addItem = new JButton("Add Item");
        addComponent(window.getContentPane(),addItem,GridBagConstraints.BOTH,7,1,1,1,0f,1f);
        JPanel chatPanel = ChatPanel();
        addComponent(window.getContentPane(), chatPanel, GridBagConstraints.BOTH, 0, 2, 4, 1, 10f, 10f);
        /*JList chatHistory = new JList();
        addComponent(chatHistory,0,2,2,1,1f,1f);

        JTextField chatBox = new JTextField();
        addComponent(chatBox,0,3,1,1,1f,0f);

        JButton send =new JButton("Send");
        addComponent(send, 1,3,1,1,0f,0f);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Sending..." + chatBox.getText());
                if (client !=null) {
                    client.sendMessage(chatBox.getText());
                }
                chatBox.setText("");
            }
        });*/


    }

    private JPanel ChatPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.CYAN);
        JLabel cdArchive = new JLabel("Archive CDs");
        addComponent(panel, cdArchive, GridBagConstraints.BOTH, 0, 0, 4, 1, 100f, 100f);

        cdRecordTable = new JTable();
        cdRecordTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                selectedIndex = cdRecordTable.getSelectedRow();
                selectedRecord = records.get(selectedIndex);
                field.setText(Integer.toString(selectedRecord.getBarcode()));
                sectfield.setText(selectedRecord.getSection());
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
        addComponent(panel,cdRecordTableScrollPane,GridBagConstraints.BOTH,0,1,4,1,40f,10.0f);



        return panel;
    }

    ;


    private <C extends Component> void addComponent(
            Container contentPane, C component, int fill, int gridx, int gridy,
            int gridwidth, int gridheight, float weightx, float weighty
    ) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = fill;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;

        contentPane.add(component, constraints);
    }

    /*private <C extends Component> C addComponent(C component, int gridX, int gridY, int gridWidth,
                                                 int gridHeight, float weightX, float weightY){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = weightX;
        constraints.weighty = weightY;

        window.getContentPane().add(component, constraints);

        return component;
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AutomationConsole client = new AutomationConsole();
            }


        });
    }




}
