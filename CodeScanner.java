package info.mycanteen.mycanteen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CodeScanner extends AppCompatActivity implements View.OnClickListener{
    Button Scanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);
        Scanbtn = findViewById(R.id.Scan);
        Scanbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
   scancode();
    }
    private void scancode()
    {
        IntentIntegrator Intigrator = new IntentIntegrator(this);
        Intigrator.setCaptureActivity(CaptureAct.class);
        Intigrator.setOrientationLocked(false);
        Intigrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        Intigrator.setPrompt("Scanning Code...");
        Intigrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
           final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
            if (result!=null)
            {
                if (result.getContents()!=null){
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage(result.getContents());
                    builder.setTitle("Scanning Result");
                    builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            scancode();
                        }
                    }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                       finish();
                        }
                    });
                    AlertDialog dialog =builder.create();
                    dialog.show();
                }
                else {
                    Toast.makeText(this,"NO Result",Toast.LENGTH_LONG).show();
                }
            }else {
                super.onActivityResult(requestCode,resultCode,data);
            }
    }
}