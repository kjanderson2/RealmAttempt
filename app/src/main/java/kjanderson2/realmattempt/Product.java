package kjanderson2.realmattempt;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kjanderson2 on 5/29/15.
 */
public class Product extends RealmObject{
    @PrimaryKey
    private int id;

    private String name;
    private int quantity;

    public Product(){

    }

    public Product(int id, String productname, int quantity) {
        this.id = id;
        this.name = productname;
        this.quantity = quantity;
    }

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
