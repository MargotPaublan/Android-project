package android.project.spotitop.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TrackEntity.class}, version = 1)
public abstract class TopSongDatabase extends RoomDatabase {
    public abstract TopSongDAO topSongsDAO();
}