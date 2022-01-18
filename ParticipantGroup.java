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
    /*ParticipantGroup(User... p) {

    for(User u : p) {
      participants.add(u);
    }

  }
  ParticipantGroup(String... usernames) {
    for(String s : usernames) {

    }
  }*/
/*
  public ParticipantGroup checkIfUnique() {
    ParticipantGroup pg = this;

    return pg;
  }*/

  /*public boolean isTheSameParticipantGroupAs(ParticipantGroup that) {
    boolean participantsAreTheSame = true;

    for (User u : that.participants) {
      if (!this.hasUser(u)) {
        participantsAreTheSame = false;
      }
    }
    for (User u : this.participants) {
      if(!that.hasUser(u)) {
        participantsAreTheSame = false;
      }
    }

    return participantsAreTheSame;

  }*/
}
