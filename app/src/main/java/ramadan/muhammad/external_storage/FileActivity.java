package ramadan.muhammad.external_storage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        name = (TextView) findViewById(R.id.nameTextView);
        email = (TextView) findViewById(R.id.emailTextView);
        password = (TextView) findViewById(R.id.passwordTextView);

    }


    public void back(View view) {

        Intent intent = new Intent(FileActivity.this, FirstActivity.class);
        startActivity(intent);
        finish();

    }

    public void InternalStorage(View view) throws IOException, IOException{
        loadData(null, "Default");
    }

    public void ExternalStoragePrivate(View view) throws IOException, IOException{

        File folderDir = getExternalFilesDir("TestDataStorage");
        File file = new File(folderDir, "Test_External_Private_Storage.txt");
        loadData(file, "Private");
    }

    public void ExternalStoragePublic(View view) throws IOException, IOException{

        File folderDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folderDir, "Test_External_Public_Storage.txt");
        loadData(file, "Public");
    }


    public void loadData(File file, String status) throws IOException, IOException {

        FileInputStream inputStream = null;

        try {
            if (status.equals("Default")) {
                inputStream = openFileInput("Test_Internal_Storage.txt");
            } else {
                inputStream = new FileInputStream(file);
            }

            Toast.makeText(this, "File Status is : " + status, Toast.LENGTH_LONG).show();
            StringBuffer CharactersBuffer = new StringBuffer();

            int read = inputStream.read();

            if (read == -1) {
                Toast.makeText(this, "File is Empty", Toast.LENGTH_SHORT).show();
            } else {

                while (read != -1) {
                    CharactersBuffer.append((char) read);
                    read = inputStream.read();
                }

                String allStringBufferContent = CharactersBuffer.toString();
                String[] tokens = allStringBufferContent.split("\n");

                name.setText(tokens[0]);
                email.setText(tokens[1]);
                password.setText(tokens[2]);

                Toast.makeText(this, "Data Load Successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File is not found !!! \nPlease create it firstly before reading from it .", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception :\n" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            inputStream.close();
        }
    }

    /*public void InternalStorage(View view) throws IOException, IOException {

        FileInputStream fileInputStream = null;
        File file = null;

        try {
            fileInputStream = openFileInput("TestFileIO.txt");
            int read = fileInputStream.read();
            file = getFilesDir().getAbsoluteFile();

            StringBuffer stringBuffer = new StringBuffer();


            while (read != -1) {
                stringBuffer.append((char) read);
                read = fileInputStream.read();
            }
            String allStringBufferContent = stringBuffer.toString();

            Toast.makeText(this, "Reading from the file is done successfully .", Toast.LENGTH_LONG).show();

            String[] tokens = allStringBufferContent.split("\n");
            name.setText(tokens[0]);
            email.setText(tokens[1]);
            password.setText(tokens[2]);

            /*while (read != -1) {

                stringBuffer.append(read);
            }
            Log.d(stringBuffer.toString(), "Roaa");
*/
            /*String Name = stringBuffer.substring(0, stringBuffer.indexOf("\n"));
            String Email = stringBuffer.substring(stringBuffer.indexOf("\n")+1, stringBuffer.indexOf("\n"));
            String Password = stringBuffer.substring(stringBuffer.indexOf("\n")+1, stringBuffer.indexOf("\n"));*/
/*
            String s = stringBuffer.toString();
            String[] array = s.split("\n");

            name.setText(array[0]);
            email.setText(array[1]);
            password.setText(array[2]);
*/

      /*  } catch (Exception e) {
            Toast.makeText(this, "IO Exception \n" + e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            fileInputStream.close();
        }

        Toast.makeText(this, "Data Load Successfully", Toast.LENGTH_SHORT).show();

    }*/

}
