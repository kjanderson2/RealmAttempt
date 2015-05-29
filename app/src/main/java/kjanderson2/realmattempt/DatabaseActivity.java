package kjanderson2.realmattempt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;


public class DatabaseActivity extends ActionBarActivity{

    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox = (EditText) findViewById(R.id.productQuantity);
    }

    public void newProduct(View view){
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        int quantity = Integer.parseInt(quantityBox.getText().toString());
        Product product = new Product(productBox.getText().toString(), quantity);
        Product realmProduct = realm.createObject(Product.class);

        realmProduct.setId(product.getId());
        realmProduct.setName(product.getName());
        realmProduct.setQuantity(quantity);

        realm.commitTransaction();

        productBox.setText("");
        quantityBox.setText("");
    }

    public void lookupProduct (View view) {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Product product = realm.where(Product.class)
                               .contains("name", productBox.getText().toString()) //Not sure about this first parameter
                               .findFirst();

        if(product != null){
            idView.setText(String.valueOf(product.getId()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText("No Match Found");
        }

        realm.commitTransaction();
    }

    public void removeProduct(View view) {


        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Product product = realm.where(Product.class)
                .contains("name", productBox.getText().toString()) //Not sure about this first parameter
                .findFirst();

        boolean result = (product != null); //Unsure if a realm query will output null if none is found.

        if(result){
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
