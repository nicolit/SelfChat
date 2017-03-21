/**
 * Created by Yael on 19/03/2017.
 */
package com.yael.selfchat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents a unique chat message object generated by user.
 */
public class Message extends AbsMessage {
	// side of object on screen. true = left, false=right
	public boolean left;

	/**
	 * Create a new Message object
	 * @param content- message text value
	 * @param side- is message wrote from the left
	 */
	public Message(String content, int side) {
		super( content, side);
		this.left = (side == 1);
	}


	public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>(){
		public Message createFromParcel(Parcel in) {
			return new Message(in);
		}
		public Message[] newArray(int size) {
			return new Message[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Writes to parcel output this Message object fields.
	 * @param out- output parcel
	 * @param flags
	 */
	@Override
	public void writeToParcel( Parcel out, int flags) {
		super.writeToParcel(out, flags);
		out.writeString(content);
		out.writeInt(side);
	}

	/**
	 * Restore a new Message object from parcel input.
	 * @param in- parcel
	 */
	private Message( Parcel in) {
		super(in);
		content = in.readString();
		side = in.readInt();
		left = (side == 1);
	}
}