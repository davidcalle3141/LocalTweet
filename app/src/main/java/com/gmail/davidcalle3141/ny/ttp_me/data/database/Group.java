package com.gmail.davidcalle3141.ny.ttp_me.data.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Group")
public class Group {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String hashtag;
    private String date_created;

    public Group(int id, String hashtag, String date_created){
        this.id = id;
        this.hashtag = hashtag;
        this.date_created = date_created;
    }
    @Ignore
    public Group(String hashtag, String date_created){
        this.hashtag = hashtag;
        this.date_created = date_created;
    }


    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}
