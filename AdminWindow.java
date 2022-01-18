/*
  this should give option to add and delete users
    what does deleting a user do to conversations they're involved in?
      maybe it says "[rip] sent: "
  could have a list of users
  add/remove buttons
  might allow read of any convo O_o
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow {
  String windowName = "Admin";
  Server host;
  AdminWindow(Server h){
    host=h;
    JFrame frame = new JFrame(windowName);
    //JFrame.setDefaultLookAndFeelDecorated(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    frame.setLocationRelativeTo(null);
    JPanel p = new JPanel(new BorderLayout());

    JPanel buttonPanel = new JPanel();

    Button addUserB = new Button("Add User");
    addUserB.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        h.addUser(new User((String)JOptionPane.showInputDialog(frame,
            "Enter the user's name.",null),h ));
      }
    });
    Button deleteUserB = new Button("Delete User");
    deleteUserB.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String[] possibilities = h.ListUsernames();
        String s = (String)JOptionPane.showInputDialog(
            frame,
            "Choose user to delete.",
            "Delete User",
            JOptionPane.PLAIN_MESSAGE,
            null,
            possibilities,
            null);
            //tell windowmanager to close userr's windows
              //each jframe will close
        h.deleteUser(s);
      }
    });

    buttonPanel.add(addUserB);
    buttonPanel.add(deleteUserB);

    p.add(buttonPanel, BorderLayout.CENTER);



    JPanel userListPanel = new JPanel();
    TextField userList = new TextField("No one's here yet!");
    p.add(userListPanel, BorderLayout.WEST);




    frame.add(p);
    frame.setVisible(true);
  }
}
