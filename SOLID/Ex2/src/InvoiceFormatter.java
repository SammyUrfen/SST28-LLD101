import java.util.List;
import java.util.Map;

public class InvoiceFormatter {

    public String formatInvoice(String invoiceId,
                                List<OrderLine> lines,
                                Map<String, MenuItem> menu,
                                double subtotal,
                                double tax,
                                double discount,
                                double total) {

        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoiceId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        sb.append(String.format("Subtotal: %.2f\n", subtotal));

        double taxPct = (subtotal > 0) ? (tax / subtotal) * 100 : 0;
        sb.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));

        sb.append(String.format("Discount: -%.2f\n", discount));
        sb.append(String.format("TOTAL: %.2f\n", total));

        return sb.toString();
    }
}
