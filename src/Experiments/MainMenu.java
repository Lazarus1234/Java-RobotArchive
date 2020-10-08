package Experiments;
import javax.swing.*;
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
        JLabel searchString = new JLabel("Search String:");
        addComponent(searchString, 0, 0, 1, 1, 0f, 0f);

        JTextField searchstring = new JTextField("");
        addComponent(searchstring, 1, 0, 1, 1, 1f, 0f);

        JButton search = new JButton("Search");
        addComponent(search, 2, 0, 0, 1, 0f, 0f);

        JLabel label = new JLabel("Archive CDs");
        addComponent(label, 1, 1, 4, 1, 1f, 0f);

        JTextArea textPane = new JTextArea();
        addComponent(textPane, 0, 2, 4, 2, 1f, 0f);

    }











    private <C extends Component> C addComponent(C component, int gridX, int gridY, int gridWidth,
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { MainMenu client = new MainMenu();}
        });

    }


}
