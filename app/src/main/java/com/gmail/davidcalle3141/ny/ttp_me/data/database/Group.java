package com.gmail.davidcalle3141.ny.ttp_me.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Group")
public class Group {
    @PrimaryKey
    @NonNull
    private String hashtag;
    private String date_created;

    public Group(@NonNull String hashtag, String date_created){
        this.hashtag = hashtag;
        this.date_created = date_created;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

}
