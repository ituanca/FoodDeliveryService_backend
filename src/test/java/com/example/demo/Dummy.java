package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.dto.AdminDTO;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.model.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.AjAttribute;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Dummy {

    public final Customer expectedCustomer = new Customer(
            "ancaitu12",
            "$2a$10$EF14wGNJF8tfDO74hXJzjO/HrIxBKABV9aplfE73KvyZ1oOkuX.HC",
            "Itu Anca",
            "ancaitu12@gmail.com");

    public final CustomerDTO existentCustomerDTO = new CustomerDTO(
            "ancaitu12",
            "4567GKOPsy",
            "Itu Anca",
            "ancaitu12@gmail.com");

    public final CustomerDTO newCustomerDTO = new CustomerDTO(
            "larisaRat",
            "$2a$10$EF14wGNJF8tfDO74hXJzjO/HrIxBKABV9aplfE73KvyZ1oOkuX.HC",
            "Rat Larisa",
            "ratlarisa@gmail.com");

    public final CustomerDTO invalidEmailNewCustomerDTO = new CustomerDTO(
            "ioanaPopescu",
            "$2a$10$EF14wGNJF8tfDO74hXJzjO/HrIxBKABV9aplfE73KvyZ1oOkuX.HC",
            "Popescu Ioana",
            "xresx5dftyguhji");

    public final Customer nonExistentUsernameCustomer = new Customer(
            "ralucaDinescu",
            "$2a$10$EF14wGNJF8tfDO74hXJzjO/HrIxBKABV9aplfE73KvyZ1oOkuX.HC",
            "Raluca Dinescu",
            "dinescuRaluca@gmail.com");

    // the same as the existentCustomer, but the password is wrong
    public final Customer incorrectPasswordExistentCustomer = new Customer(
            "ancaitu12",
            "12345678",
            "Itu Anca",
            "ancaitu12@gmail.com");

    Customer c1 = new Customer("alindobrescuuu",
            "$2a$10$V2M046CpNOT5yVpIVgC6yuSgw4TN3b71VdqDiw55selZpxxxZUMP.",
            "Alin Dobrescu",
            "alindobrescu@gmail.com");
    Customer c2 = new Customer("mihaiMoise",
            "$2a$10$e9JKJYyBIGthxANjYz2h6Onrm0pbiBUL/hlhqQU4oX6COn5d.aA8S",
            "Mihai Moise",
            "mihaimoise@yahoo.com");
    Customer c3 = new Customer("mariaPop",
            "$2a$10$GGZX4yUynqnHD3sAI1rZz.tXjVhJOOMsjoSGe4VJxSnrb5vRV4HeK",
            "Maria Pop",
            "mariapop@yahoo.com");

    // adminService //

    Admin expectedAdmin = new Admin("lemnulverde@yahoo.com", "adminLemnulVerde", "$2a$10$f0WE0MRB8yNTUF8zy4FfTuKUzp18is.BbuuXdr/I06ADlDGNleCXi");
    AdminDTO nonExistentUsernameAdminDTO = new AdminDTO("adminLemnul", "$2a$10$f0WE0MRB8yNTUF8zy4FfTuKUzp18is.BbuuXdr/I06ADlDGNleCXi");
    AdminDTO incorrectPasswordExistentAdminDTO = new AdminDTO("adminLemnulVerde", "sdcvbhb45");
    AdminDTO existentAdminDTO = new AdminDTO("adminLemnulVerde", "SDGH98765");

    Admin newAdmin = new Admin("voila@yahoo.com", "adminVoila", "CVSDEFGEWG43dcsaf");
    Admin newAdminExistentUsername = new Admin("voila@yahoo.com", "adminLemnulVerde", "CVSDEFGEWG43dcsaf");
    Admin newAdminExistentEmail = new Admin("lemnulverde@yahoo.com", "adminVoila", "CVSDEFGEWG43dcsaf");
    Admin newAdminInvalidEmail = new Admin("voila@", "adminVoila", "CVSDEFGEWG43dcsaf");

    Admin a1 = new Admin("marty@yahoo.com", "adminMarty", "$2a$10$BO4XrzrWLQm4QABwNSFBAeVGgW1aIFL1hlGXKo29EN8pfMOt1kNQ.");
    Admin a2 = new Admin("lemnulverde@yahoo.com", "adminLemnulVerde", "$2a$10$f0WE0MRB8yNTUF8zy4FfTuKUzp18is.BbuuXdr/I06ADlDGNleCXi");
    Admin a3 = new Admin("samsara@yahoo.com", "adminSamsara", "$2a$10$00bn9HbqAkrYARG/l0.P7utZ71I409bpE6OziMJp/gw8Fwz7iYUuu");

    // restaurantService //

    Zone zone1CrinulAlb = new Zone("AndreiMuresanu");
    Zone zone2CrinulAlb = new Zone("Becas");
    Zone zone3CrinulAlb = new Zone("Borhanci");
    List<Zone> zonesCrinulAlb = new ArrayList<>(List.of(zone1CrinulAlb, zone2CrinulAlb, zone3CrinulAlb));
    Admin adminCrinulAlb = new Admin("crinulalb@yahoo.com", "adminCrinulAlb", "$2a$10$toufCvxvrj7IDDzxQAiIPuhHOiBK7GKb.lOuucksnA4yZ0O1CI4kS");

    Restaurant restaurant1 = new Restaurant("Crinul Alb", "str. Kogalniceanu, nr.83", zonesCrinulAlb, adminCrinulAlb);

    Zone zone1Spoon = new Zone("Sopor");
    Zone zone2Spoon = new Zone("Europa");
    Zone zone3Spoon = new Zone("Gheorgheni");
    List<Zone> zonesSpoon = new ArrayList<>(List.of(zone1Spoon, zone2Spoon, zone3Spoon));
    Admin adminSpoon = new Admin("spoon@yahoo.com", "adminSpoon", "$2a$10$8NjbJ28VWyawxVStvTcMN.lEk3x5yopb920bao105MzQ6WaZISiWq");

    Restaurant restaurant2 = new Restaurant("Spoon", "str. Mihai Viteazul, nr.8", zonesSpoon, adminSpoon);

    Zone zone1Samsara = new Zone("Gruia");
    Zone zone2Samsara = new Zone("Iris");
    Zone zone3Samsara = new Zone("Marasti");
    List<Zone> zonesSamsara = new ArrayList<>(List.of(zone1Samsara, zone2Samsara, zone3Samsara));
    Admin adminSamsara = new Admin("samsara@yahoo.com", "adminSamsara", "$2a$10$00bn9HbqAkrYARG/l0.P7utZ71I409bpE6OziMJp/gw8Fwz7iYUuu");

    Restaurant restaurant3 = new Restaurant("Samsara", "str. Kogalniceanu, nr.83", zonesSamsara, adminSamsara);


    Category category1 = new Category("Pizza");
    Category category2 = new Category("Pasta");
    Category category3 = new Category("Salad");
    Food food1 = new Food("junior", "corn, ham, mozzarella, tomato sauce", 23.0, category1);
    Food food2 = new Food("carbonara", "ham, white sauce", 25.0, category2);
    Food food3 = new Food("Exotica", "cucumber, tomato, corn, carrot, pineapple", 20.0, category3);
    List<Food> listOfFood = new ArrayList<>(List.of(food1, food2, food3));
    Menu menuCrinulAlb = new Menu(1, listOfFood);

    Restaurant expectedRestaurant = new Restaurant("Crinul Alb", "str. Kogalniceanu, nr.67", zonesCrinulAlb, menuCrinulAlb, adminCrinulAlb);

    // categoryService //

    Category category11 = category1;
    Category category22 = category2;
    Category category33 = category3;

    // foodService //

    Menu menu1 = new Menu(1);
    Menu menu2 = new Menu(2);
    Food food11 = new Food("junior", "corn, ham, mozzarella, tomato sauce", 23.0, category1, menu1);
    Food food22 = new Food("carbonara", "ham, white sauce", 25.0, category2, menu1);
    Food food33 = new Food("Exotica", "cucumber, tomato, corn, carrot, pineapple", 20.0, category3, menu1);
    Food food44 = new Food("Atena", "cucumber, tomato, onion, cheese", 22.0, category3, menu2);

    // zoneService //

    Zone z1 = new Zone("Gruia");
    Zone z2 = new Zone("Marasti");
    Zone z3 = new Zone("Manastur");

    Zone expectedZone = new Zone("Zorilor");

    public Dummy(){}


}
