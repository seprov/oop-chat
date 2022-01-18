/*
  this should display a user's conversations
  is the controller and view in mvc
  sends to the server
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow {
  Server host;
  String windowName;
  User user; // the chat is from this user's perspective
  Chat chat;
  JTextArea chatArea = new JTextArea();

  // need participants

  ChatWindow() {
    // ChatWindow("default");
    // why doesn't this work?
  }

  ChatWindow(String wn, Chat c, Server h, User u) {
    host = h;
    windowName = wn;
    chat = c;
    user = u;
    h.wm.chatWindowSet.add(this);

    JPanel chatAreaPanel = new JPanel(new BorderLayout());
    JPanel messageAndSendPanel = new JPanel(new BorderLayout());
    Button sendButton = new Button("Send");

    chatArea.setEditable(false);
    JTextField messageField = new JTextField();
    JScrollPane scrollingChatArea = new JScrollPane(chatArea);
    JFrame frame = new JFrame(windowName);
    frame.setLayout(new BorderLayout());

    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        h.sendMessage(user, messageField.getText(), chat);
        messageField.setText("");
        updateChatFeed();


      }
    });
    //chatArea.setSize(500,600);
    //chatAreaPanel.setSize(500,600);
    //scrollingChatArea.setSize(500,600);
    updateChatFeed();

    chatAreaPanel.add(scrollingChatArea, BorderLayout.CENTER);
    frame.add(chatAreaPanel, BorderLayout.CENTER);

    //sendButton.setSize(200,200);
    messageAndSendPanel.add(messageField, BorderLayout.CENTER);
    messageAndSendPanel.add(sendButton, BorderLayout.EAST);
    frame.add(messageAndSendPanel, BorderLayout.SOUTH);

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        h.wm.chatWindowSet.remove(this);
        super.windowClosing(e);
      }
    });

    frame.setSize(500,600);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public void updateChatFeed() {
    chatArea.setText(chat.generateMessageFeed());
    chatArea.revalidate();
    chatArea.repaint();

  }

}