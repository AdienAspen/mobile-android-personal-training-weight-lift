package com.leo2026.weightlifting.data.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/leo2026/weightlifting/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "assetDao", "Lcom/leo2026/weightlifting/data/dao/AssetDao;", "exerciseDao", "Lcom/leo2026/weightlifting/data/dao/ExerciseDao;", "templateDao", "Lcom/leo2026/weightlifting/data/dao/TemplateDao;", "userDao", "Lcom/leo2026/weightlifting/data/dao/UserDao;", "workoutDao", "Lcom/leo2026/weightlifting/data/dao/WorkoutDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.leo2026.weightlifting.data.entity.ExerciseEntity.class, com.leo2026.weightlifting.data.entity.WorkoutSessionEntity.class, com.leo2026.weightlifting.data.entity.SetEntryEntity.class, com.leo2026.weightlifting.data.entity.TemplateEntity.class, com.leo2026.weightlifting.data.entity.TemplateExerciseEntity.class, com.leo2026.weightlifting.data.entity.BarEntity.class, com.leo2026.weightlifting.data.entity.PlateEntity.class, com.leo2026.weightlifting.data.entity.UserEntity.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.leo2026.weightlifting.data.database.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.leo2026.weightlifting.data.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.leo2026.weightlifting.data.dao.ExerciseDao exerciseDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.leo2026.weightlifting.data.dao.WorkoutDao workoutDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.leo2026.weightlifting.data.dao.TemplateDao templateDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.leo2026.weightlifting.data.dao.AssetDao assetDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.leo2026.weightlifting.data.dao.UserDao userDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/leo2026/weightlifting/data/database/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/leo2026/weightlifting/data/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.leo2026.weightlifting.data.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}