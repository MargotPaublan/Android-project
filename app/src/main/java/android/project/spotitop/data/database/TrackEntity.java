package android.project.spotitop.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "topSongsTable")
public class TrackEntity {
    @NonNull
    @PrimaryKey
    public String id;

    public String title;
    public String album;
    private List<String> artists;
    private Integer duration;

}