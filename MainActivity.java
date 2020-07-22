package info.mycanteen.mycanteen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnSignup, btnLogin;
    Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.Register);
        btnLogin = (Button) findViewById(R.id.Login);
        mSwitch = (Switch) findViewById(R.id.Map);
        progressBar.setVisibility(View.INVISIBLE);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}