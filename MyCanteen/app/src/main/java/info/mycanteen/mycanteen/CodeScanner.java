package info.mycanteen.mycanteen;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CodeScanner extends AppCompatActivity implements View.OnClickListener{
    Button Scanbtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);
        Scanbtn = findViewById(R.id.Scan);
        Scanbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scancode();
    }
    public void scancode()
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

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if (result.getContents()!=null){
                String m = result.getContents();
                final SpannableString s = new SpannableString(m); // msg should have url to enable clicking
                Linkify.addLinks(s, Linkify.ALL);
                      String c = "MyCanteen";
                AlertDialog d = new AlertDialog.Builder((this),R.style.MyDialogTheme)
                        .setTitle(c)
                        .setCancelable(true)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("Cancel", null)
                        .setMessage( s )
                        .create();
                        d.show();

                // Make the textview clickable. Must be called after show()
                ((TextView)d.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                AlertDialog.Builder builder=new AlertDialog.Builder(this);

//                 final TextView textView = new TextView(this);
//                 textView.setText(result.getContents()); //Might need to typecast to string.
//                 textView.setMovementMethod(LinkMovementMethod.getInstance());


//                        builder.setMessage(result.getContents());
//                //Instead of the above line use the line below.
////                     builder.setView(textView);
//                builder.setTitle("Scanning Result");
//                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        scancode();
//                    }
//                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                AlertDialog dialog =builder.create();
//                dialog.show();
           }
            else {
                Toast.makeText(this,"NO Result",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
