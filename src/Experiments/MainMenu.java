package Experiments;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainMenu {
    JFrame window;


    public MainMenu() {
        window = new JFrame("Main Menu");
        window.setMinimumSize(new Dimension(800,800));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = window.getContentPane();
        content.setLayout(new GridBagLayout());

        //ui elements
        createUI();

        window.pack();
        window.setVisible(true);
    }

    private void createUI() {
        JLabel searchLabel = new JLabel("Search String:");
        addComponent(window, searchLabel, 0, 0, 1, 1, 0.1f, 0.1f);

        JTextField searchstring = new JTextField("");
        addComponent(window, searchstring, 2, 0, 1, 1, 2f, 0.1f);

        JButton search = new JButton("Search");
        addComponent(window, search, 3, 1, 1, 1, 2f, 0.1f);

        JLabel label = new JLabel("Archive CDs");
        addComponent(window, label, 1, 2, 2, 1, 1f, 0f);


        String[] columnNames = {"Title","Author", "Section", "X","Y","Barcode","Description", "On-Loan"};
        DefaultTableModel mdl = new DefaultTableModel(columnNames, 0);
        JTable tbl = new JTable(mdl);
        JScrollPane ArchivePanel = new JScrollPane(tbl);
        ArchivePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addComponent(window, ArchivePanel, 0,3,6,1,100f,110);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(90);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(30);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(10);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(10);

        JPanel ItemPnl = new JPanel();
        ItemPnl.setLayout(new GridBagLayout());
        ItemPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addComponent(window,ItemPnl,7,1,1,1,10,0f );
        JLabel lbltitle = new JLabel("Title:");
        ItemPnl.add(lbltitle);
        lbltitle = addComponent(ItemPnl, lbltitle, 0,0,1,1,0.1f,0.1f);
        JLabel lblAuthor = new JLabel("Author");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,1,1,1,0.1f,0.1f);
        JLabel lblSection = new JLabel("Section");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,2,2,1,0.1f,0.1f);
        JLabel lblxLocation = new JLabel("X");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,3,1,1,0.1f,0.1f);
        JLabel lblyLocation = new JLabel("Y");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,4,1,1,0.1f,0.1f);
        JLabel lblBarcode = new JLabel("Barcode");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,5,5,1,0.1f,0.1f);
        JLabel lblDesc = new JLabel("Description");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,6,1,1,0.1f,0.1f);
        JLabel lblOnLoan = new JLabel("On-Loan");
        lblAuthor = addComponent(ItemPnl, lblAuthor, 0,7,1,1,0.1f,0.1f);



    }











    private <C extends Component> C addComponent(Container container,C component, int gridX, int gridY, int gridWidth,
                                                 int gridHeight, float weightX, float weightY){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        container.add(component, constraints);

        window.getContentPane().add(component, constraints);

        return component;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { MainMenu client = new MainMenu();}
        });

    }


}
