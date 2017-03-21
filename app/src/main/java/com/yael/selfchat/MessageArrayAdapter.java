/**
 * Created by Yael on 19/03/2017.
 */
package com.yael.selfchat;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * This class represents array of messages sent.
 */
public class MessageArrayAdapter extends ArrayAdapter<Message> {
	private TextView chatText;
	// list of messages sent in the chat
	private List<Message> MessageList = new ArrayList<Message>();
    private LinearLayout layout;

	/**
	 * Constructor
	 * @param context
	 * @param textViewResourceId
	 */
    public MessageArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	/**
	* Add a new message object into the list of messages
	* @param object - message instance
	* */
	public void add( Message object) {
		MessageList.add(object);
		super.add(object);
	}

	/**
	 * @return list of message objects
	 */
	public List<Message> getMessageList(){
		return this.MessageList;
	}

	/**
	 * returns amount of messages sent
	 * @return int - number of messages
	 */
	public int getCount() {
		return this.MessageList.size();
	}

	/**
	 * gets the message object by it's index in the list
	 * @return Message - message object
	 */
	public Message getItem(int index) {
		return this.MessageList.get(index);
	}

	/**
	 * gets the view of the new message
	 * @return View -
	 */
	public View getView( int position, View ConvertView, ViewGroup parent) {
		View currentView = ConvertView;
		if( currentView == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			currentView = inflater.inflate(R.layout.chat, parent,false);
		}

		layout = (LinearLayout)currentView.findViewById(R.id.Message1);
		Message message = getItem( position);
		chatText =(TextView)currentView.findViewById(R.id.SingleMessage);
		chatText.setText( message.content);
		chatText.setBackgroundResource(  message.left ? R.drawable.bubble_a : R.drawable.bubble_b);
		layout.setGravity( message.left ? Gravity.LEFT : Gravity.RIGHT);
		return currentView;
	}
	  
	public Bitmap decodeToBitmap( byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
	  
}
