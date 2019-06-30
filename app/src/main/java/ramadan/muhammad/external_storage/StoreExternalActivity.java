package ramadan.muhammad.external_storage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoreExternalActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_external);

        name = (EditText) findViewById(R.id.ex_nameEditText);
        email = (EditText) findViewById(R.id.ex_emailEditText);
        password = (EditText) findViewById(R.id.ex_passwordEditText);

    }

    public void mainMenu(View view) {

        Intent intent = new Intent(StoreExternalActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();

    }

    public void database(View view) {

        Intent intent = new Intent(StoreExternalActivity.this, FileActivity.class);
        startActivity(intent);
        finish();
    }

    public void externalPublic(View view) throws IOException, FileNotFoundException  {

        String Name = name.getText().toString() +"\n";
        String Email = email.getText().toString() +"\n";
        String Password = password.getText().toString();
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file1 = new File(file, "Test_External_Public_Storage.txt");
        dataStore(Name , Email , Password , file1);
    }

    public void externalPrivate(View view) throws IOException, FileNotFoundException{
        String Name = name.getText().toString() +"\n";
        String Email = email.getText().toString() +"\n";
        String Password = password.getText().toString();
        File file = getExternalFilesDir("TestDataStorage");
        File file1 = new File(file, "Test_External_Private_Storage.txt");
        dataStore(Name , Email , Password , file1);
    }

    private void dataStore (String n , String e , String p ,File file1) throws IOException, FileNotFoundException {

        FileOutputStream fileOutputStream = null ;

        try {
             fileOutputStream = new FileOutputStream(file1);
             fileOutputStream.write(n.getBytes());
             fileOutputStream.write(e.getBytes());
             fileOutputStream.write(p.getBytes());

             Toast.makeText(this, "Stored Successfully ", Toast.LENGTH_SHORT).show();
         } catch (Exception ex) {
             ex.printStackTrace();
         }finally {
             fileOutputStream.close();
         }
    }

}
