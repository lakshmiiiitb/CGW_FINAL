package com.example.cgw.controller;

import com.example.cgw.JPAData.*;
import com.example.cgw.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.TransactionRequiredException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CustomerPages {
    private static final Logger logger = LogManager.getLogger(Pages.class);

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PartnerRepo partnerRepo;

    @Autowired
    ItemsRepo itemsRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    DistanceBetweenAreasRepo distanceBetweenAreasRepo;

    @GetMapping(path = "/customer/viewitems/{id}")
    public List<Items> viewItemsInShop(@PathVariable("id") int shopid)
    {
        logger.info("---View items in shop---");
        logger.info("Entered shop id: "+shopid);
        Partner shop= partnerRepo.findById(shopid);
        List<Items>  items= itemsRepo.findAllByPartner(shop);
        logger.info("Retrieved items ");
        return items;
    }


    @PostMapping(path = "/customer/viewshops")
    public List<Partner> viewShopsbyLocation(@RequestBody LocationType loc)
    {
        //String location=loc.getLocation().substring(0,loc.getLocation().length()-1);
        logger.info("---View shops by location id ---");
        logger.info("enterd location "+loc.getLocation()+" "+loc.getType());
        List<Partner> shops=partnerRepo.findByStoreLoc(loc.getLocation(), loc.getType());

        System.out.println(loc);
        System.out.println(shops.size());
        if(shops.size() == 0)
            logger.error("No shops at this location");
        else
            logger.info("Shops found");
        for(Partner shp:shops)
        {
            System.out.println(shp);
            logger.info("Shop "+shp.getId()+" "+shp.getStoreName());
        }

        return shops;
    }


    @GetMapping(path = "/customer/addtocart/{userid}/{itemid}/{qty}")
    public String addToCart(@PathVariable("userid") int id, @PathVariable("itemid") int itemid, @PathVariable("qty") int qty)
    {
        Customer customer=customerRepo.findById(id);
        logger.info("---Add items to cart---");
        Items items=itemsRepo.findById(itemid);
        logger.info(items.getItem_name()+" added to "+customer.getName()+"'s cart");
        cartRepo.save(new Cart(items.getItem_name(), qty, qty*items.getPrice(),  items.getImage(),customer, itemid));
        return "added";
    }


    @Transactional
    @GetMapping(path = "/customer/addorders/{cartid}/{addid}/{shopid}/{qty}")
    public String addOrders(@PathVariable("cartid") int cartid,@PathVariable("shopid") int shopid, @PathVariable("addid") int addid, @PathVariable("qty") int qty)
    {
        Cart cart= cartRepo.findById(cartid);
        Partner partner=partnerRepo.findById(shopid);
        Address address=addressRepo.findById(addid);
        Items items=itemsRepo.findByShopAndItemName(partner, cart.getItem_name());

        if(items!=null)
        {
            int status=itemsRepo.decrementQuantity(qty,partner,items.getId());
            System.out.println(status);
            if(status >0)
                System.out.println("Updated");
            else
                System.out.println("Not updated");


            Orders o=new Orders(cart,address,partner, 'o',null);
            ordersRepo.save(o);
            cartRepo.delete(cart);
        }
        return "done";
    }

    @GetMapping(path = "/customer/viewcart/{id}")
    public List<Cart> viewCart(@PathVariable("id") int id)
    {
        List<Cart> carts=cartRepo.findByCustomer(customerRepo.findById(id));
        for(Cart c:carts)
            System.out.println(c);
        return carts;
    }

    @GetMapping(path = "/customer/viewaddress/{id}")
    public List<Address> viewAddress(@PathVariable("id") int id)
    {
        System.out.println(id);
        List<Address> addresses = addressRepo.findByCustomer(customerRepo.findById(id));
        System.out.println(addresses.size());
        for(Address c:addresses)
            System.out.println(c);
        return addresses;
    }

    @GetMapping("/customer/item/{id}")
    public Cart getItem(@PathVariable("id") int id)
    {
        Items items=itemsRepo.findById(id);
        Cart cart=cartRepo.findByItemid(id);

        return cart;
    }

    @GetMapping("address/{id}")
    public List<Address> getAddress(@PathVariable("id") int id)
    {
        List<Address> address= addressRepo.findByCustomer(customerRepo.findById(id));
        return address;
    }


    @PostMapping("/customer/addAddress/{id}")
    public String addAddress(@PathVariable("id") int id, Address address)
    {
        address.setCustomer(customerRepo.findById(id));
        addressRepo.save(address);
        return "done";
    }

    @PostMapping(path = "/customer/addprofilephotoCustomer")
    public String  saveProfile(int id, MultipartFile file, String item_name, int qty, double price, String desc)
    {
        Customer customer=customerRepo.findById(id);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            customer.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerRepo.save(customer);
        return "done";
    }

    @GetMapping(path = "/customer/{id}")
    public Customer getCustomer(@PathVariable("id") int id)
    {
        System.out.println("id= "+id);
        return customerRepo.findById(id);
    }

    @PostMapping(path = "/customer/addprofilephoto")
    public String  saveProfile(int custid,MultipartFile file)
    {
        //Partner partner=partnerRepo.findById(custid);
        Customer customer = customerRepo.findById(custid);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            customer.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerRepo.save(customer);
        return "done";
    }

    @GetMapping ("/customer/inventory/{id}")
    public List<Items> viewInventoryToCustomer(@PathVariable("id") int id)
    {
        System.out.println("Inventory");
        Partner p=partnerRepo.findById(id);
        List<Items> items=itemsRepo.findAllByPartner(p);
        //for(Items i : items)
          //  System.out.println(i);
        return items;
    }

    @GetMapping ("/customer/orders/{id}")
    public List<Orders> viewOrdersToCustomer(@PathVariable("id") int id)
    {
        System.out.println("Orders");
        Customer customer=customerRepo.findById(id);
        List<Orders> orders= ordersRepo.findAllByCustomer(customer);
        for(Orders i : orders)
        {
            System.out.println(i.getCustomer().getUsername()+"   "+i.getItem_name()+"  "+i.getQty()+" "+i.getPrice());
        }
        return orders;
    }

    @GetMapping("/customer/buynow/{itemid}/{userid}")
    public List<Object> getAddressAndItems(@PathVariable("itemid") int itemid, @PathVariable("userid") int userid)
    {
        Items items=itemsRepo.findById(itemid);
        List<Address> addresses = addressRepo.findByCustomer(customerRepo.findById(userid));
        List<Object> result=new ArrayList<>();
        result.add(items);
        result.addAll(addresses);
        return result;
    }

    @Transactional
    @GetMapping("/customer/validorder/{shopid}/{addressid}")
    int checkOrderValidity( @PathVariable("shopid")int shopid, @PathVariable("addressid")int addressid) throws TransactionRequiredException {
        Address address= addressRepo.findById(addressid);
        Partner partner=partnerRepo.findById(shopid);
        if(partner.getPincode().equals(address.getPincode()))
        {
                return 1;//same area
        }
        else if(partner.getCity().equals(address.getCity()))
        {
            DistanceBetweenAreas distance= distanceBetweenAreasRepo.findByFromLocationAndToLocation(partner.getStoreLoc(), address.getArea());
            if(distance.getDist() > 20)
                return 0;
            else
            {

                    return 1;
            }
        }
        else return 0;//not in same place
    }


    @GetMapping("/customer/getCartId/{itemid}/{userid}")
    public int getCartId(@PathVariable("itemid") int itemid, @PathVariable("userid") int userid)
    {
        return cartRepo.findByItemidAndCustomer(itemid, customerRepo.findById(userid)).getId();
    }
}
