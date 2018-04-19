package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate() {
        BigDecimal subTotal;

        subTotal = calculateTotal();
        subTotal = subTotal.subtract(calculateDiscounts());

        // calculate tax
        BigDecimal tax = subTotal.multiply(this.tax);

        // calculate GrandTotal
        BigDecimal grandTotal = subTotal.add(tax);

        return grandTotal;
    }

    private BigDecimal calculateDiscounts() {
        BigDecimal totalDiscount = new BigDecimal(0);
        for (BigDecimal discount : discounts) {
            totalDiscount = totalDiscount.add(discount);
        }
        return totalDiscount;
    }

    private BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        for (OrderLineItem lineItem : orderLineItemList) {
            total = total.add(lineItem.getPrice());
        }
        return total;
    }
}
