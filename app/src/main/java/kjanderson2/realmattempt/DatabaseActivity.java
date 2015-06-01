package kjanderson2.realmattempt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import io.realm.Realm;


public class DatabaseActivity extends ActionBarActivity{

    int primaryKey = 0;
    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 5 lines
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox = (EditText) findViewById(R.id.productQuantity);
    }

    public void newProduct(View view){ // 12 lines
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        int quantity = Integer.parseInt(quantityBox.getText().toString());
        Product product = new Product(primaryKey, productBox.getText().toString(), quantity);
        Product realmProduct = realm.createObject(Product.class);

        realmProduct.setId(product.getId());
        realmProduct.setName(product.getName());
        realmProduct.setQuantity(quantity);

        productBox.setText("");
        quantityBox.setText("");
        primaryKey++;

        realm.commitTransaction();
    }

    public void lookupProduct (View view) { //11 lines total
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        Product product = realm.where(Product.class)
                               .contains("name", productBox.getText().toString())
                               .findFirst();
        if(product != null){
            idView.setText(String.valueOf(product.getId()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

    public void removeProduct(View view) { // 14 lines total
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Product product = realm.where(Product.class)
                .contains("name", productBox.getText().toString())
                .findFirst();

        if(product!=null){
            product.removeFromRealm();
            idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

}
