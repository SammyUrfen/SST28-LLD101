import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");

        List<SendResult> audit = new ArrayList<>();

        Notification emailNotif = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", null);
        NotificationSender emailSender = new EmailSender();
        audit.add(emailSender.send(emailNotif));

        Notification smsNotif = new Notification("Welcome", "Hello and welcome to SST!", null, "9876543210");
        NotificationSender smsSender = new SmsSender();
        audit.add(smsSender.send(smsNotif));

        Notification waNotif = new Notification("Welcome", "Hello and welcome to SST!", null, "9876543210");
        NotificationSender waSender = new WhatsAppSender();
        audit.add(waSender.send(waNotif));

        System.out.println("AUDIT entries=" + audit.size());
    }
}
