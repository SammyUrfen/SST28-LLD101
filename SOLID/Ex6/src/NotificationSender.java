public abstract class NotificationSender {
    public final SendResult send(Notification n) {
        if (n == null) {
            throw new IllegalArgumentException("Notification must not be null");
        }
        if (n.body == null) {
            throw new IllegalArgumentException("'body' must not be null");
        }

        SendResult result = doSend(n);

        if (result == null) {
            throw new IllegalStateException("Sender produced null result");
        }
        return result;
    }

    protected abstract SendResult doSend(Notification n);
}
