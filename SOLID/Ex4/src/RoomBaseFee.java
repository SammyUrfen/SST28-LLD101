import java.util.HashMap;
import java.util.Map;

public class RoomBaseFee implements FeeComponent {
    private final Map<Integer, Double> priceMap;

    public RoomBaseFee() {
        priceMap = new HashMap<>();
        priceMap.put(LegacyRoomTypes.SINGLE, 14000.0);
        priceMap.put(LegacyRoomTypes.DOUBLE, 15000.0);
        priceMap.put(LegacyRoomTypes.TRIPLE, 12000.0);
    }

    @Override
    public Money price(BookingRequest req) {
        double base = priceMap.getOrDefault(req.roomType, 16000.0);
        return new Money(base);
    }
}
