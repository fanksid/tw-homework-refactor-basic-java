package practice2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        Map<Long, OrderItem> itemMap = items.stream().collect(Collectors.toMap(OrderItem::getCode, item -> item));

        BigDecimal subTotal = calculateSubtotal(products, itemMap);

        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    private OrderItem findOrderItemByProduct(Product product, Map<Long, OrderItem> itemMap) {
        if (itemMap.containsKey(product.getCode())) {
            return itemMap.get(product.getCode());
        }
        return null;
    }

    private BigDecimal calculateSubtotal(List<Product> products, Map<Long, OrderItem> itemMap) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(product, itemMap);
            BigDecimal percent = new BigDecimal(1).subtract(product.getDiscountRate());
            BigDecimal itemTotal = product.getPrice().multiply(percent).multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
