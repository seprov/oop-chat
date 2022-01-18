/*
  this should be a conversation between two users
  no perspective
  should generate when new conversation starts
    user a and user b
    users a, b, and c
    etc
 */

import java.util.*;
public class Chat {

  LinkedList<Message> messages = new LinkedList<>();
  ParticipantGroup participants;

  Chat(Server h) {
    participants = new ParticipantGroup();
  }

  Chat(Server h, ParticipantGroup p) {
    participants = p;
  }

  public void addMessage(Message m) { messages.addLast(m); }

  public String generateMessageFeed() {
    String mf = "";
    for(Message m : messages) {
      mf += m.getAuthor().getName() + ", " + m.getTimeSent() + ": \n" + m.getContent() + "\n\n";
    }
    return mf;
  }


}
