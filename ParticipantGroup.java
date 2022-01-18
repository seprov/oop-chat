import java.util.*;

/*
i'm going to store a map of conversations,
so participants need to be encapsulated in something
 */

public class ParticipantGroup {
  Set<User> participants = new HashSet<>();
  ParticipantGroup(){}
  public boolean hasUser(User user_to_find) {
    for(User u : participants) {
      if (user_to_find == u) {
        return true;
      }
    }
    return false;
  }
  public void addParticipant(User u) {
    participants.add(u);
  }
  public String[] getParticipantNames() {
    ArrayList<String> pnames = new ArrayList<>();
    for (User u : participants) {
      pnames.add(u.getName());
    }
    return pnames.toArray(new String[0]);
  }
}
