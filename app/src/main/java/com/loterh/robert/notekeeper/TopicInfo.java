package com.loterh.robert.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class TopicInfo implements Parcelable {
    private final String mTopicId;
    private final String mTitle;
    private final List<ModuleInfo> mModules;

    public TopicInfo(String topicId, String title, List<ModuleInfo> modules) {
        mTopicId = topicId;
        mTitle = title;
        mModules = modules;
    }

    private TopicInfo(Parcel source) {
        mTopicId = source.readString();
        mTitle = source.readString();
        mModules = new ArrayList<>();
        source.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    public String getCourseId() {
        return mTopicId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<ModuleInfo> getModules() {
        return mModules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[mModules.size()];

        for (int i = 0; i < mModules.size(); i++)
            status[i] = mModules.get(i).isComplete();

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for (int i = 0; i < mModules.size(); i++)
            mModules.get(i).setComplete(status[i]);
    }

    public ModuleInfo getModule(String moduleId) {
        for (ModuleInfo moduleInfo : mModules) {
            if (moduleId.equals(moduleInfo.getModuleId()))
                return moduleInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicInfo that = (TopicInfo) o;

        return mTopicId.equals(that.mTopicId);

    }

    @Override
    public int hashCode() {
        return mTopicId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTopicId);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);
    }

    public static final Creator<TopicInfo> CREATOR =
            new Creator<TopicInfo>() {

                @Override
                public TopicInfo createFromParcel(Parcel source) {
                    return new TopicInfo(source);
                }

                @Override
                public TopicInfo[] newArray(int size) {
                    return new TopicInfo[size];
                }
            };

}
