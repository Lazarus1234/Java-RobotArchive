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
        addComponent(window, searchLabel, 0, 0, 1, 1, 0.1f, 0f);

        JTextField searchstring = new JTextField("");
        addComponent(window, searchstring, 2, 0, 4, 1, 0.01f, 0f);

        JButton search = new JButton("Search");
        addComponent(window, search, 7, 0, 0, 1, 0f, 0f);

        JLabel label = new JLabel("Archive CDs");
        addComponent(window, label, 1, 2, 2, 1, 1f, 0f);


        String[] columnNames = {"Title","Author", "Section", "X","Y","Barcode","Description", "On-Loan"};
        DefaultTableModel mdl = new DefaultTableModel(columnNames, 0);
        JTable tbl = new JTable(mdl);
        JScrollPane ArchivePanel = new JScrollPane(tbl);
        ArchivePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addComponent(window, ArchivePanel, 0,3,8,2,1f,0);
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
        addComponent(window,ItemPnl,5,0,1,1,0f,0f );
        JLabel lbltitle = new JLabel();



    }











    private <C extends Component> C addComponent(Container container,C component, int gridX, int gridY, int gridWidth,
                                                 int gridHeight, float weightX, float weightY){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
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
