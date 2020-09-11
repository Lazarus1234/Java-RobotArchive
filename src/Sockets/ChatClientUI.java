package Sockets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ChatClientUI {
    JFrame window;

    public ChatClientUI() {
        window = new JFrame("Chat Client");
        window.setMinimumSize(new Dimension(400,400));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = window.getContentPane();
        content.setLayout(new GridBagLayout());

        //ui elements
        createUI();

        window.pack();
        window.setVisible(true);
    }

    private void createUI() {
        JTextField serverAddress = new JTextField("Server Address...");
        addComponent(serverAddress, 0,0,2,1,1f,0f);

        JButton connect = new JButton("Connect");
        addComponent(connect,0,1,2,1,1f,0f);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Connecting with " + serverAddress.getText());

            }
        });

        JList chatHistory = new JList();
        addComponent(chatHistory,0,2,2,1,1f,1f);

        JTextField chatBox = new JTextField();
        addComponent(chatBox,0,3,1,1,1f,0f);

        JButton send =new JButton("Send");
        addComponent(send, 1,3,1,1,0f,0f);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Sending..." + chatBox.getText());
            }
        });

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
            public void run() {
                ChatClientUI client = new ChatClientUI();
            }


        });
    }

}
