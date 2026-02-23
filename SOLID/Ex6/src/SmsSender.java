public class SmsSender extends NotificationSender {
    @Override
    protected SendResult doSend(Notification n) {
        String output = "SMS -> to=" + n.phone + " body=" + n.body;
        System.out.println(output);
        return new SendResult(true, output);
    }
}
