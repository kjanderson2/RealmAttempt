package kjanderson2.realmattempt;

import io.realm.RealmObject;

/**
 * Created by kjanderson2 on 5/29/15.
 * This is the product model which has properties ID, name, and quantity.
 */
public class Product extends RealmObject{

    private int id;
    private String name;
    private int quantity;

    //Default constructor
    public Product(){

    }

    //Constructor when the ID is given.
    public Product(int id, String productname, int quantity) {
        this.id = id;
        this.name = productname;
        this.quantity = quantity;
    }

    //Constructor where the id is not given.
    public Product(String productname, int quantity) {
        this.name = productname;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String productname) {
        this.name = productname;
    }

    public String getName() {
        return this.name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
