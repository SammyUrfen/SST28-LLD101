import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repo;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceRepository repo) {
        this.repo = repo;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += (item.price * l.qty);
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        InvoiceFormatter formatter = new InvoiceFormatter();
        String printable = formatter.formatInvoice(invId, lines, menu, subtotal, tax, discount, total);

        System.out.print(printable);

        repo.save(invId, printable);
    }
}
