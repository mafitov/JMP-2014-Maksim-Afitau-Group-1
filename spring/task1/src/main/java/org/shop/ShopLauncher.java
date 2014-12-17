package org.shop;

import org.shop.api.*;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        org.shop.api.ProductService productService = (ProductService) context.getBean("productService");

        OrderService orderService = (OrderService) context.getBean("orderService");

        ItemService itemService = (ItemService) context.getBean("itemService");

//        UserService userService = (UserService) context.getBean("clientService");
        UserService userService = (UserService) context.getBean("userService");

        ProposalService proposalService = context.getBean(ProposalService.class);

//        DataInitializer dataInitializer = context.getBean(DataInitializer.class);
//        dataInitializer.initData();

        Product galaxy = productService.getProductsByName("Samsung Galaxy Tab").get(0);
        Proposal proposal = proposalService.getProposalsByProduct(galaxy).get(0);

        orderService.createOrder(userService.getUserById((long) 1), proposal);

        for (Order order : orderService.getOrdersByUserId((long) 1)) {
            System.out.println(order);

            for (Item item : itemService.getItemsByOrderId(order.getId())) {
                System.out.println(item);
            }
        }
    }
}
