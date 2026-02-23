public class WhatsAppSender extends NotificationSender {
    @Override
    protected SendResult doSend(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            String error = "WA ERROR: phone must start with + and country code";
            System.out.println(error);
            return new SendResult(false, error);
        }
        String output = "WA -> to=" + n.phone + " body=" + n.body;
        System.out.println(output);
        return new SendResult(true, output);
    }
}
