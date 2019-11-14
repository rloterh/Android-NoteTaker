package com.loterh.robert.notekeeper;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;


public final class NoteInfo implements Parcelable {
    private TopicInfo mTopic;
    private String mTitle;
    private String mText;

    public NoteInfo(TopicInfo topic, String title, String text) {
        mTopic = topic;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel source) {
        mTopic = source.readParcelable(TopicInfo.class.getClassLoader());
        mTitle = source.readString();
        mText = source.readString();
    }

    public TopicInfo getTopic() {
        return mTopic;
    }

    public void setTopic(TopicInfo course) {
        mTopic = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mTopic.getCourseId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mTopic, 0);

        dest.writeString(mTitle);
        dest.writeString(mText);
    }

    public static final Parcelable.Creator<NoteInfo> CREATOR = new Parcelable.Creator<NoteInfo>() {
        @Override
        public NoteInfo createFromParcel(Parcel source) {
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
}
