import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<FeeComponent> feeComponents;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.feeComponents = new ArrayList<>();
        this.feeComponents.add(new RoomBaseFee());
        this.feeComponents.add(new AddOnFee());
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0.0);
        for (FeeComponent component : feeComponents) {
            total = total.plus(component.price(req));
        }
        return total;
    }
}
