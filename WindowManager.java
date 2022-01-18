/*
 * Manages all of a user's windows:
 *   client
 *   settings window
 *   chat windows
 *
 */

import java.util.*;

public class WindowManager {
    //possibly merge with server
  Server host;
  Set<ChatWindow> chatWindowSet = new HashSet<>(); //need to remove when window is closed

  WindowManager(Server h) {
    host=h;
  }
  public void openAdminWindow() {
    AdminWindow a = new AdminWindow(host);
  }

  public void openChatWindow(Chat c, User initiator) {
    ChatWindow cw = new ChatWindow(generateChatWindowTitle(c),c,host,initiator);
  }
  public void redrawWindowsInvolvingUser(User u) {
    //look through all chats
    //find chats with participant groups including the user
    //update all chats associated with those participant groups
    for(ChatWindow cw : chatWindowSet) {
      if(cw.chat.participants.hasUser(u)) {
        cw.updateChatFeed();
      }
    }
  }

  public void CloseUserWindows(User u) {
    // should do this on delete
  }
  public String generateChatWindowTitle(Chat c) {
    ParticipantGroup p = c.participants;
    String[] participantNames = p.getParticipantNames();
    String chatWindowTitle = "";
    if(participantNames.length==1) {
      chatWindowTitle+= participantNames[0];
    }
    else if(participantNames.length==2) {
      chatWindowTitle += participantNames[0] + " and " + participantNames[1];
    }
    else {
      for (int i = 0; i < participantNames.length; i++) {
        String s = participantNames[i];
        if(i==participantNames.length-1 ) {
          chatWindowTitle += ", and ";
        }
        else if (i>0){
          chatWindowTitle += ", ";
        }
        chatWindowTitle += s;
      }
    }
    chatWindowTitle = "Chat with " + chatWindowTitle;
    return chatWindowTitle;
  }
}
