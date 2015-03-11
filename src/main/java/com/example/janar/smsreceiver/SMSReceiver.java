package com.example.janar.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by janar on 3/11/15.
 */
public class SMSReceiver extends BroadcastReceiver
{

    public static final String LOG_TAG = SMSReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(LOG_TAG,"Received intent: " + intent.getAction());

        SmsMessage[] msgs = null;
        String str = "";
        Bundle bundle = intent.getExtras();
        if( bundle != null){
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                str += "SMS from " + msgs[i].getOriginatingAddress();
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";

                //addToReceivedSMS(context,msgs[i].getMessageBody().toString());
            }
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    /*
    protected void addToReceivedSMS(Context context,String message)
    {
        Intent main = new Intent(context,SMSActivity.class);
        context.startActivity(main);
    }
   */
}
