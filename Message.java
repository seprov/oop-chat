
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Message {
  User author;
  String content;
  Instant timeSent;

  Message(User a, String c) {
    author=a;
    content=c;
    timeSent = Instant.now();
  }
  public User getAuthor(){
    return author;
  }
  public String getContent() {
    return content;
  }
  public String getTimeSent() {
    DateTimeFormatter dtf = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault());

    return dtf.format(timeSent);

  }
}
