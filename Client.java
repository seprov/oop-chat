/*
this one may be unnecessary
but i think it could:

let users start chats and designate participants
let users access user settings
  (change name, delete account)
might even let users set stati, idk tho seems unnecessary

would be both controller and view?
 */

import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;

public class Client {
  String windowName;
  User user;
  Server host;
  Client(User u, Server h) {
    user = u;
    windowName = "User: " + user.getName();
    host = h;
    drawClient();
  }
  public void drawClient() {
    //do window stuff here
    JFrame frame = new JFrame(windowName);
    frame.setSize(500,100);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    JPanel p = new JPanel(new FlowLayout());

    Button startChatB = new Button("Start chat");

    startChatB.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ParticipantGroup participants = new ParticipantGroup();
        JFrame participantSelector = new JFrame("Start chat");
        participantSelector.setSize(500,300);
        //participantSelector.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //JPanel ps = new JPanel(new BorderLayout());
        JList<String> userList = new JList<>(host.ListUsernames(user));
        userList.setSize(100,100);
        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JOptionPane.showMessageDialog(null,new JScrollPane(userList));
        participants.addParticipant(user);
        for (String s : userList.getSelectedValuesList()) {
          participants.addParticipant(host.getUser(s));
        }
        //participants = participants.checkIfUnique();

        startChat(participants);
      }
    });
    p.add(startChatB);

    Button editUserSettingsB = new Button("Edit settings");
    editUserSettingsB.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame userSettings = new JFrame("User Settings: "+user.getName());
        JPanel us = new JPanel(new FlowLayout());
        userSettings.setSize(500,100);
        Button changeNameB = new Button("Change name");
        changeNameB.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String newName = JOptionPane.showInputDialog(frame,
                "Enter your changed name.",user.getName());
            user.setName(newName);
          }
        });
        Button deleteUserB = new Button("Delete account");
        deleteUserB.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if(0==JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete?",
                "Confirm delete",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null)) {
              host.deleteUser(user);
              //////////////////////
              //close user's windows
              //////////////////////
            }
          }
        });

        us.add(changeNameB);
        us.add(deleteUserB);
        userSettings.add(us);
        userSettings.setVisible(true);
      }
    });
    p.add(editUserSettingsB);


    frame.add(p);
    Random rand = new Random();
    int random_x = rand.nextInt(1000);
    int random_y = rand.nextInt(700);
    frame.setLocation(random_x,random_y);
    frame.setVisible(true);
  }
  public void startChat(ParticipantGroup p) {
    host.hostChat(p, user);
    //JFrame chatWindow = new JFrame("Chat with " + participantTitle);
    //chatWindow.setSize(500,800);
    //chatWindow.setVisible(true);
  }
}
