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

    BigDecimal calculate() {

        Calculator calculator = new Calculator();
        BigDecimal subTotal = calculator.getSubTotal();
        BigDecimal tax = calculator.getTax();

        return subTotal.add(tax);
    }

    private class Calculator {
        private BigDecimal subTotal;

        BigDecimal getSubTotal() {
            subTotal = new BigDecimal(0);

            for (OrderLineItem lineItem : orderLineItemList) {
                subTotal = subTotal.add(lineItem.getPrice());
            }

            for (BigDecimal discount : discounts) {
                subTotal = subTotal.subtract(discount);
            }

            return subTotal;
        }

        BigDecimal getTax() {
            return subTotal.multiply(Order.this.tax);
        }

    }
}
