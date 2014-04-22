package com.example.sms_smsc.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;
import android.telephony.SmsManager;
import android.view.View.OnClickListener;
import android.app.Activity;

public class MainActivity extends ActionBarActivity {
    Button buttonSend;
    EditText textPhoneNo, textSMS, textSMSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);
        textSMSC = (EditText) findViewById(R.id.editTextSMSC);

        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();
                String smsc = textSMSC.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    if (smsc.isEmpty())
                    {
                        smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                        Toast.makeText(getApplicationContext(), "SMS Sent with default SMSC!",
                                Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        smsManager.sendTextMessage(phoneNo, smsc, sms, null, null);
                        Toast.makeText(getApplicationContext(), "SMS Sent with special SMSC!",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
