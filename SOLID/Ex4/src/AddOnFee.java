import java.util.HashMap;
import java.util.Map;

public class AddOnFee implements FeeComponent {
    private final Map<AddOn, Double> priceMap;

    public AddOnFee() {
        priceMap = new HashMap<>();
        priceMap.put(AddOn.MESS, 500.0);
        priceMap.put(AddOn.LAUNDRY, 500.0);
        priceMap.put(AddOn.GYM, 300.0);
    }

    @Override
    public Money price(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += priceMap.getOrDefault(a, 0.0);
        }
        return new Money(total);
    }
}
