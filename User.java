/*
hmm what should this have?

 */
public class User {
  String username;
  Client client;
  Server host;
  User(String un, Server h) {
    username = un;
    host = h;
  }
  public String getName() {
    return username;
  }
  void setName(String nn) {
    username = nn;
  }
}
