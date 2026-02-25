package com.leo2026.weightlifting.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.leo2026.weightlifting.data.dao.AssetDao;
import com.leo2026.weightlifting.data.dao.AssetDao_Impl;
import com.leo2026.weightlifting.data.dao.ExerciseDao;
import com.leo2026.weightlifting.data.dao.ExerciseDao_Impl;
import com.leo2026.weightlifting.data.dao.TemplateDao;
import com.leo2026.weightlifting.data.dao.TemplateDao_Impl;
import com.leo2026.weightlifting.data.dao.UserDao;
import com.leo2026.weightlifting.data.dao.UserDao_Impl;
import com.leo2026.weightlifting.data.dao.WorkoutDao;
import com.leo2026.weightlifting.data.dao.WorkoutDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ExerciseDao _exerciseDao;

  private volatile WorkoutDao _workoutDao;

  private volatile TemplateDao _templateDao;

  private volatile AssetDao _assetDao;

  private volatile UserDao _userDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercises` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `defaultRestSeconds` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_sessions` (`id` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER, `name` TEXT NOT NULL, `templateId` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `set_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sessionId` TEXT NOT NULL, `exerciseId` TEXT NOT NULL, `weight` REAL NOT NULL, `reps` INTEGER NOT NULL, `setType` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `rpe` INTEGER, `exerciseNameSnapshot` TEXT NOT NULL, `exerciseCategorySnapshot` TEXT NOT NULL, `exerciseRestSnapshot` INTEGER NOT NULL, `durationMillis` INTEGER NOT NULL, FOREIGN KEY(`sessionId`) REFERENCES `workout_sessions`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_set_entries_sessionId` ON `set_entries` (`sessionId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_set_entries_exerciseId` ON `set_entries` (`exerciseId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `templates` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `isDeleted` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `template_exercises` (`templateId` TEXT NOT NULL, `exerciseId` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, PRIMARY KEY(`templateId`, `exerciseId`, `orderIndex`), FOREIGN KEY(`templateId`) REFERENCES `templates`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bars` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `weight` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `plates` (`id` TEXT NOT NULL, `weight` REAL NOT NULL, `quantity` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `age` INTEGER, `weight` REAL, `height` REAL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9470a27218c38f9b35b8184c23948f41')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `exercises`");
        db.execSQL("DROP TABLE IF EXISTS `workout_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `set_entries`");
        db.execSQL("DROP TABLE IF EXISTS `templates`");
        db.execSQL("DROP TABLE IF EXISTS `template_exercises`");
        db.execSQL("DROP TABLE IF EXISTS `bars`");
        db.execSQL("DROP TABLE IF EXISTS `plates`");
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsExercises = new HashMap<String, TableInfo.Column>(5);
        _columnsExercises.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("defaultRestSeconds", new TableInfo.Column("defaultRestSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercises = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExercises = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExercises = new TableInfo("exercises", _columnsExercises, _foreignKeysExercises, _indicesExercises);
        final TableInfo _existingExercises = TableInfo.read(db, "exercises");
        if (!_infoExercises.equals(_existingExercises)) {
          return new RoomOpenHelper.ValidationResult(false, "exercises(com.leo2026.weightlifting.data.entity.ExerciseEntity).\n"
                  + " Expected:\n" + _infoExercises + "\n"
                  + " Found:\n" + _existingExercises);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutSessions = new HashMap<String, TableInfo.Column>(5);
        _columnsWorkoutSessions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("templateId", new TableInfo.Column("templateId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutSessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkoutSessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkoutSessions = new TableInfo("workout_sessions", _columnsWorkoutSessions, _foreignKeysWorkoutSessions, _indicesWorkoutSessions);
        final TableInfo _existingWorkoutSessions = TableInfo.read(db, "workout_sessions");
        if (!_infoWorkoutSessions.equals(_existingWorkoutSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_sessions(com.leo2026.weightlifting.data.entity.WorkoutSessionEntity).\n"
                  + " Expected:\n" + _infoWorkoutSessions + "\n"
                  + " Found:\n" + _existingWorkoutSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsSetEntries = new HashMap<String, TableInfo.Column>(12);
        _columnsSetEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("exerciseId", new TableInfo.Column("exerciseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("reps", new TableInfo.Column("reps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("setType", new TableInfo.Column("setType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("rpe", new TableInfo.Column("rpe", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("exerciseNameSnapshot", new TableInfo.Column("exerciseNameSnapshot", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("exerciseCategorySnapshot", new TableInfo.Column("exerciseCategorySnapshot", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("exerciseRestSnapshot", new TableInfo.Column("exerciseRestSnapshot", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetEntries.put("durationMillis", new TableInfo.Column("durationMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSetEntries = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysSetEntries.add(new TableInfo.ForeignKey("workout_sessions", "CASCADE", "NO ACTION", Arrays.asList("sessionId"), Arrays.asList("id")));
        _foreignKeysSetEntries.add(new TableInfo.ForeignKey("exercises", "CASCADE", "NO ACTION", Arrays.asList("exerciseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesSetEntries = new HashSet<TableInfo.Index>(2);
        _indicesSetEntries.add(new TableInfo.Index("index_set_entries_sessionId", false, Arrays.asList("sessionId"), Arrays.asList("ASC")));
        _indicesSetEntries.add(new TableInfo.Index("index_set_entries_exerciseId", false, Arrays.asList("exerciseId"), Arrays.asList("ASC")));
        final TableInfo _infoSetEntries = new TableInfo("set_entries", _columnsSetEntries, _foreignKeysSetEntries, _indicesSetEntries);
        final TableInfo _existingSetEntries = TableInfo.read(db, "set_entries");
        if (!_infoSetEntries.equals(_existingSetEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "set_entries(com.leo2026.weightlifting.data.entity.SetEntryEntity).\n"
                  + " Expected:\n" + _infoSetEntries + "\n"
                  + " Found:\n" + _existingSetEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsTemplates = new HashMap<String, TableInfo.Column>(4);
        _columnsTemplates.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTemplates.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTemplates.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTemplates.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTemplates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTemplates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTemplates = new TableInfo("templates", _columnsTemplates, _foreignKeysTemplates, _indicesTemplates);
        final TableInfo _existingTemplates = TableInfo.read(db, "templates");
        if (!_infoTemplates.equals(_existingTemplates)) {
          return new RoomOpenHelper.ValidationResult(false, "templates(com.leo2026.weightlifting.data.entity.TemplateEntity).\n"
                  + " Expected:\n" + _infoTemplates + "\n"
                  + " Found:\n" + _existingTemplates);
        }
        final HashMap<String, TableInfo.Column> _columnsTemplateExercises = new HashMap<String, TableInfo.Column>(3);
        _columnsTemplateExercises.put("templateId", new TableInfo.Column("templateId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTemplateExercises.put("exerciseId", new TableInfo.Column("exerciseId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTemplateExercises.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTemplateExercises = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTemplateExercises.add(new TableInfo.ForeignKey("templates", "CASCADE", "NO ACTION", Arrays.asList("templateId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTemplateExercises = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTemplateExercises = new TableInfo("template_exercises", _columnsTemplateExercises, _foreignKeysTemplateExercises, _indicesTemplateExercises);
        final TableInfo _existingTemplateExercises = TableInfo.read(db, "template_exercises");
        if (!_infoTemplateExercises.equals(_existingTemplateExercises)) {
          return new RoomOpenHelper.ValidationResult(false, "template_exercises(com.leo2026.weightlifting.data.entity.TemplateExerciseEntity).\n"
                  + " Expected:\n" + _infoTemplateExercises + "\n"
                  + " Found:\n" + _existingTemplateExercises);
        }
        final HashMap<String, TableInfo.Column> _columnsBars = new HashMap<String, TableInfo.Column>(3);
        _columnsBars.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBars.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBars.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBars = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBars = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBars = new TableInfo("bars", _columnsBars, _foreignKeysBars, _indicesBars);
        final TableInfo _existingBars = TableInfo.read(db, "bars");
        if (!_infoBars.equals(_existingBars)) {
          return new RoomOpenHelper.ValidationResult(false, "bars(com.leo2026.weightlifting.data.entity.BarEntity).\n"
                  + " Expected:\n" + _infoBars + "\n"
                  + " Found:\n" + _existingBars);
        }
        final HashMap<String, TableInfo.Column> _columnsPlates = new HashMap<String, TableInfo.Column>(3);
        _columnsPlates.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlates.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlates.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlates = new TableInfo("plates", _columnsPlates, _foreignKeysPlates, _indicesPlates);
        final TableInfo _existingPlates = TableInfo.read(db, "plates");
        if (!_infoPlates.equals(_existingPlates)) {
          return new RoomOpenHelper.ValidationResult(false, "plates(com.leo2026.weightlifting.data.entity.PlateEntity).\n"
                  + " Expected:\n" + _infoPlates + "\n"
                  + " Found:\n" + _existingPlates);
        }
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(6);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("firstName", new TableInfo.Column("firstName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("lastName", new TableInfo.Column("lastName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("age", new TableInfo.Column("age", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("weight", new TableInfo.Column("weight", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("height", new TableInfo.Column("height", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.leo2026.weightlifting.data.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9470a27218c38f9b35b8184c23948f41", "7070aadac34cd36f9feed8418c24cdd4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "exercises","workout_sessions","set_entries","templates","template_exercises","bars","plates","user_profile");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `exercises`");
      _db.execSQL("DELETE FROM `workout_sessions`");
      _db.execSQL("DELETE FROM `set_entries`");
      _db.execSQL("DELETE FROM `templates`");
      _db.execSQL("DELETE FROM `template_exercises`");
      _db.execSQL("DELETE FROM `bars`");
      _db.execSQL("DELETE FROM `plates`");
      _db.execSQL("DELETE FROM `user_profile`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ExerciseDao.class, ExerciseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkoutDao.class, WorkoutDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TemplateDao.class, TemplateDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AssetDao.class, AssetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ExerciseDao exerciseDao() {
    if (_exerciseDao != null) {
      return _exerciseDao;
    } else {
      synchronized(this) {
        if(_exerciseDao == null) {
          _exerciseDao = new ExerciseDao_Impl(this);
        }
        return _exerciseDao;
      }
    }
  }

  @Override
  public WorkoutDao workoutDao() {
    if (_workoutDao != null) {
      return _workoutDao;
    } else {
      synchronized(this) {
        if(_workoutDao == null) {
          _workoutDao = new WorkoutDao_Impl(this);
        }
        return _workoutDao;
      }
    }
  }

  @Override
  public TemplateDao templateDao() {
    if (_templateDao != null) {
      return _templateDao;
    } else {
      synchronized(this) {
        if(_templateDao == null) {
          _templateDao = new TemplateDao_Impl(this);
        }
        return _templateDao;
      }
    }
  }

  @Override
  public AssetDao assetDao() {
    if (_assetDao != null) {
      return _assetDao;
    } else {
      synchronized(this) {
        if(_assetDao == null) {
          _assetDao = new AssetDao_Impl(this);
        }
        return _assetDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
