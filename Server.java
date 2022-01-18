import java.util.*;

/*
model in mvc
stores all users and conversations
listens for sends
updates appropriate windows on send
  those windows are listening for update command

 */
public class Server {
  List<User> userList = new ArrayList<User>();
  HashMap<ParticipantGroup, Chat> chatList = new HashMap<>();
  WindowManager wm = new WindowManager(this);

  Server(){
    wm.openAdminWindow();
  }

  public void hostChat(ParticipantGroup p, User initiator) {
    Chat c;
    ParticipantGroup checkedPG = checkIfUniquePG(p);
    if (checkedPG == p) {
      c = new Chat(this, p);
      chatList.put(p, c);
    }
    else {
      p = checkedPG;
      c = chatList.get(p);
    }
    wm.openChatWindow(c, initiator);
  }

  private ParticipantGroup checkIfUniquePG(ParticipantGroup pgToCheck) {
    for(ParticipantGroup hostedGroup : chatList.keySet()) {
      if(hostedGroup.participants.equals(pgToCheck.participants)) {
        return hostedGroup;
      }
    }
    return pgToCheck;
  }

  public void sendMessage(User author, String content, Chat chat) {
    Message newMessage = new Message(author,content);
    chat.addMessage(newMessage);
    wm.redrawWindowsInvolvingUser(author);
  }

  public String[] ListUsernames(){
    ArrayList<String> usernames = new ArrayList<>();
    for (User u : userList) {
      usernames.add(u.getName());
    }
    return usernames.toArray(new String[0]);
  }
  public String[] ListUsernames(User loo){ // leaves one out
    ArrayList<String> unames = new ArrayList<>();
    for (User u : userList) {
      if(u != loo) {
        unames.add(u.getName());
      }
    }
    return unames.toArray(new String[0]);
  }

  public void addUser(User u) {
    userList.add(u);
    u.client = new Client(u, this);
  }
  public void deleteUser(User u) {
    deleteUser(u.getName());
  }
  public void deleteUser(String s) {
    for(User u : userList) {
      if(u.getName() == s) {
        updateDeletedUserChats(u);
        userList.remove(u);

      }
    }
  }

  public void updateDeletedUserChats(User u){

  }

  public User getUser(String s) {
    for (User u : userList) {
      if (u.getName() == s) {
        return u;
      }
    }
    return new User(s,this); // this should never happen
  }


}
