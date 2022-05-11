package com.example.cgw.controller;

import com.example.cgw.Helper.JwtUtil;
import com.example.cgw.JPAData.*;
import com.example.cgw.Service.CustomUserDetailsService;
import com.example.cgw.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*")
@RestController
public class Pages {
    private static final Logger logger = LogManager.getLogger(Pages.class);

    public static String roleentered=null;
    public static int customerid=-1;

    @Autowired
    UserCredentialsRepo userCredentialsRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomerRepo cust;

    @Autowired
    DeliveryRepo del;

    @Autowired
    PartnerRepo part;

    @Autowired
    DistanceBetweenAreasRepo distanceBetweenAreasRepo;

    @Autowired
    ItemsRepo itemsRepo;
    @PostMapping("/authenticate")
    public ResponseEntity<?> generate(@RequestBody JWTRequest login) throws Exception {
        String append="";
        logger.info("---Authenticating user---");
        try
        {
            System.out.println("login "+login);

            if(login.getRole().equals("ROLE_CUSTOMER"))
            {
                append+="C_";
                roleentered = "ROLE_CUSTOMER";
            }
            else if(login.getRole().equals("ROLE_PARTNER"))
            {
                append+="P_";
                roleentered = "ROLE_PARTNER";
            }
            else if(login.getRole().equals("ROLE_DELIVERY"))
            {
                append+="D_";
                roleentered = "ROLE_DELIVERY";
            }
            System.out.println("appen "+append);
            System.out.println("appen "+append);
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(append+login.getUsername(), login.getPassword()));
        }
        catch (Exception e)
        {
            logger.error("Auhtentication failed , reason "+e);
            System.out.println(e);
            throw new Exception("Bad credentials");
        }
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(append+login.getUsername());
        String token=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token,customerid, roleentered));

    }



    @PostMapping(path = "/register/partner")
    public Partner partner(@RequestBody Partner p) {

        logger.info("---Registering partner---");
        System.out.println("entered");
        System.out.println(p);
        p.setPassword(bCryptPasswordEncoder.encode(p.getPassword()));
        part.save(p);
        UserCredentials userCredentials = new UserCredentials("P_"+p.getUsername(), p.getPassword(), "ROLE_PARTNER", part.findByUsername(p.getUsername()).getId());

        userCredentialsRepo.save(userCredentials);

        System.out.println(p);

        return p;
    }

    @PostMapping(path = "/register/customer")
    public Customer customer(@RequestBody Customer c) {
        logger.info("---Registering customer---");
        System.out.println("entered customer");
        System.out.println(c);
        c.setPassword(bCryptPasswordEncoder.encode(c.getPassword()));
        System.out.println(c);
        cust.save(c);
//only appended C_ as part of new update, nothing else.
        UserCredentials userCredentials = new UserCredentials("C_"+c.getUsername(), c.getPassword(), "ROLE_CUSTOMER", cust.findByUsername(c.getUsername()).getId());
        userCredentialsRepo.save(userCredentials);
        return c;
    }

    @PostMapping(path = "/register/delivery")
    public Delivery delivery(@RequestBody Delivery d) {
        logger.info("---Registering delivery---");
        System.out.println(d);
        d.setPassword(bCryptPasswordEncoder.encode(d.getPassword()));
        System.out.println(d);
        del.save(d);
        UserCredentials userCredentials = new UserCredentials("D_"+d.getUsername(), d.getPassword(), "ROLE_DELIVERY",del.findByUsername(d.getUsername()).getId());
        userCredentialsRepo.save(userCredentials);


        return d;
    }

   @PostMapping(path = "/addDistance")
    public DistanceBetweenAreas addDistance(@RequestBody  DistanceBetweenAreas distance)
    {
        logger.info("---Distance between "+distance.getFromLocation()+" "+distance.getToLocation()+" is "+distance.getDist()+" ---");
        distanceBetweenAreasRepo.save(distance);
        return distance;
    }

    @GetMapping(path = "/getpartnerfromitem/{id}")
    public int GetPartnerFromITem(@PathVariable("id") int itemid)
    {
        Items items=itemsRepo.findById(itemid);
        System.out.println("inside get partner, val= "+items.getPartner().getId());
        return items.getPartner().getId();
    }
}
