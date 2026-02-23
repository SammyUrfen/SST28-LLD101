public class EmailSender extends NotificationSender {
    @Override
    protected SendResult doSend(Notification n) {
        String output = "EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body;
        System.out.println(output);
        return new SendResult(true, output);
    }
}
