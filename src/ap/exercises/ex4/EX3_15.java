package ap.exercises.ex4;

public class EX3_15 {
    private String sender;
    private String recipient;
    private String body;
    public EX3_15(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        body = "";
    }
    public void addingLine(String line) {
        body = body.concat(line).concat("\n");
        //body += line + "\n";
    }
    public String getLetter() {
        String letter = "Dear " + recipient + ":\n\n";
        letter = letter.concat(body);
        letter = letter.concat("\nSincerely,\n\n");
        letter = letter.concat(sender);
        return letter;
    }
}
