package ramadan.muhammad.external_storage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void internal(View view) {

        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void external(View view) {

        Intent intent = new Intent(FirstActivity.this, StoreExternalActivity.class);
        startActivity(intent);
        finish();

    }
}
