/**
 * Created by Yael on 19/03/2017.
 */
package com.yael.selfchat;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents a raw abstract message object.
 */
public abstract class AbsMessage implements Parcelable {
    public String content;
    public int side;

    /**
     * Constructor
     * @param content- string message content
     * @param side- integer indicates the side of the message on screen. 1=left, 0=right.
     */
    protected AbsMessage( String content, int side) {
        this.content = content;
        this.side = side;
    }

    /**
     * Writes to parcel output this Message object fields.
     * @param out- output parcel
     * @param flags
     */
    public void writeToParcel( Parcel out, int flags) {
        out.writeString(content);
        out.writeInt(side);
    }

    /**
     * Restore a new Message object from parcel input.
     * @param in- parcel
     */
    protected AbsMessage( Parcel in) {
        content = in.readString();
        side = in.readInt();
    }
}
