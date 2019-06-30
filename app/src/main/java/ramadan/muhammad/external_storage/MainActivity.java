package ramadan.muhammad.external_storage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
    }

    public void mainMenu(View view) {

        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();

    }

    public void database(View view) {

        Intent intent = new Intent(MainActivity.this, FileActivity.class);
        startActivity(intent);
        finish();

    }

    public void save(View view) throws FileNotFoundException, IOException {

        String Name = name.getText().toString() + "\n";
        String Email = email.getText().toString() + "\n";
        String Password = password.getText().toString();

        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "EditText Fields are Empty !", Toast.LENGTH_SHORT).show();

        } else {
            FileOutputStream fileOutputStream = null;
            File file = null;
            try {

                file = getFilesDir().getAbsoluteFile();
                fileOutputStream = openFileOutput("Test_Internal_Storage.txt", Context.MODE_PRIVATE);
                fileOutputStream.write(Name.getBytes());
                fileOutputStream.write(Email.getBytes());
                fileOutputStream.write(Password.getBytes());

            } catch (Exception e) {
                Toast.makeText(this, "IO Exception \n" + e.toString(), Toast.LENGTH_LONG).show();
            } finally {
                fileOutputStream.close();
            }

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
