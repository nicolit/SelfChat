/**
 * Created by Yael on 19/03/2017.
 */
package com.yael.selfchat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	  
    private static final String TAG = "ChatActivity";
    private MessageArrayAdapter messageArray; // array adapter of messages
    private ListView list; // list of view
    private EditText chatText; // the box where user input is set
    private Button send;
    Intent intent;
    private boolean side = false;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent appIntent = getIntent();
        setContentView( R.layout.main);
        // returns View, but Button extends View
        send = (Button) findViewById(R.id.btn);
        // returns View, but ListView extends View
        list = (ListView) findViewById(R.id.listview);

        messageArray = new MessageArrayAdapter(getApplicationContext(), R.layout.chat);
        list.setAdapter(messageArray);

        chatText = (EditText) findViewById(R.id.chat_text);
        chatText.setOnKeyListener( new OnKeyListener() {
            public boolean onKey( View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendMessage();
                }
                return false;
            }
        });

        // add listener in case of SEND button is clicked or ENTER button.
        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View arg0) {
                sendMessage();
            }
        });

        list.setTranscriptMode( AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(messageArray);

        messageArray.registerDataSetObserver( new DataSetObserver() {
            // has no Override because it's abstract and not interface
        	public void OnChanged() {
        		super.onChanged();
                // show on screen only the last messages
        		list.setSelection(messageArray.getCount() -1);
        	}
		});
        
    }


    /**
     * Creates a Popup message to the user when there is alert or an error.
     * @param message- the message to the user.
     */
    private void alertView( String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage( message); // set the message to user
        alertDialog.setButton( AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    /**
     * Send actual message object - adds to message array adapter and clears
     * the input text box.
     * @return true if was successful.
     */
    private boolean sendMessage() {
        int left = (side ? 1 : 0);
        Message message = new Message( chatText.getText().toString(), left);
        try {
            messageArray.add( message);
            chatText.setText("");
            side = !side;
            throw new Exception();
        } catch ( Exception err) {
            alertView("Error: Cannot add message. " + err.getMessage());
        }
        return true;
    }

}