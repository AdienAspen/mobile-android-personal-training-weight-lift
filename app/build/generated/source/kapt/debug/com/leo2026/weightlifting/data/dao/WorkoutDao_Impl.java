package com.leo2026.weightlifting.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.leo2026.weightlifting.data.entity.SetEntryEntity;
import com.leo2026.weightlifting.data.entity.WorkoutSessionEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkoutDao_Impl implements WorkoutDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutSessionEntity> __insertionAdapterOfWorkoutSessionEntity;

  private final EntityInsertionAdapter<SetEntryEntity> __insertionAdapterOfSetEntryEntity;

  private final EntityDeletionOrUpdateAdapter<SetEntryEntity> __deletionAdapterOfSetEntryEntity;

  private final SharedSQLiteStatement __preparedStmtOfEndSession;

  public WorkoutDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutSessionEntity = new EntityInsertionAdapter<WorkoutSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_sessions` (`id`,`startTime`,`endTime`,`name`,`templateId`,`sourceType`,`singleExerciseId`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutSessionEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        statement.bindLong(2, entity.getStartTime());
        if (entity.getEndTime() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getEndTime());
        }
        if (entity.getName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getName());
        }
        if (entity.getTemplateId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTemplateId());
        }
        if (entity.getSourceType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSourceType());
        }
        if (entity.getSingleExerciseId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSingleExerciseId());
        }
      }
    };
    this.__insertionAdapterOfSetEntryEntity = new EntityInsertionAdapter<SetEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `set_entries` (`id`,`sessionId`,`exerciseId`,`weight`,`reps`,`setType`,`timestamp`,`rpe`,`exerciseNameSnapshot`,`exerciseCategorySnapshot`,`exerciseRestSnapshot`,`durationMillis`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SetEntryEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSessionId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSessionId());
        }
        if (entity.getExerciseId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getExerciseId());
        }
        statement.bindDouble(4, entity.getWeight());
        statement.bindLong(5, entity.getReps());
        if (entity.getSetType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSetType());
        }
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getRpe() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRpe());
        }
        if (entity.getExerciseNameSnapshot() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getExerciseNameSnapshot());
        }
        if (entity.getExerciseCategorySnapshot() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getExerciseCategorySnapshot());
        }
        statement.bindLong(11, entity.getExerciseRestSnapshot());
        statement.bindLong(12, entity.getDurationMillis());
      }
    };
    this.__deletionAdapterOfSetEntryEntity = new EntityDeletionOrUpdateAdapter<SetEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `set_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SetEntryEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfEndSession = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE workout_sessions SET endTime = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSessions(final List<WorkoutSessionEntity> sessions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWorkoutSessionEntity.insert(sessions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSets(final List<SetEntryEntity> sets,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSetEntryEntity.insert(sets);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSession(final WorkoutSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWorkoutSessionEntity.insert(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSet(final SetEntryEntity set, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSetEntryEntity.insert(set);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSet(final SetEntryEntity set, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSetEntryEntity.handle(set);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object endSession(final String sessionId, final long endTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfEndSession.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, endTime);
        _argIndex = 2;
        if (sessionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, sessionId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfEndSession.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllSessionsList(
      final Continuation<? super List<WorkoutSessionEntity>> $completion) {
    final String _sql = "SELECT * FROM workout_sessions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WorkoutSessionEntity>>() {
      @Override
      @NonNull
      public List<WorkoutSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTemplateId = CursorUtil.getColumnIndexOrThrow(_cursor, "templateId");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSingleExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "singleExerciseId");
          final List<WorkoutSessionEntity> _result = new ArrayList<WorkoutSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutSessionEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTemplateId;
            if (_cursor.isNull(_cursorIndexOfTemplateId)) {
              _tmpTemplateId = null;
            } else {
              _tmpTemplateId = _cursor.getString(_cursorIndexOfTemplateId);
            }
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSingleExerciseId;
            if (_cursor.isNull(_cursorIndexOfSingleExerciseId)) {
              _tmpSingleExerciseId = null;
            } else {
              _tmpSingleExerciseId = _cursor.getString(_cursorIndexOfSingleExerciseId);
            }
            _item = new WorkoutSessionEntity(_tmpId,_tmpStartTime,_tmpEndTime,_tmpName,_tmpTemplateId,_tmpSourceType,_tmpSingleExerciseId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllSetsList(final Continuation<? super List<SetEntryEntity>> $completion) {
    final String _sql = "SELECT * FROM set_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SetEntryEntity>>() {
      @Override
      @NonNull
      public List<SetEntryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfSetType = CursorUtil.getColumnIndexOrThrow(_cursor, "setType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfRpe = CursorUtil.getColumnIndexOrThrow(_cursor, "rpe");
          final int _cursorIndexOfExerciseNameSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseNameSnapshot");
          final int _cursorIndexOfExerciseCategorySnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseCategorySnapshot");
          final int _cursorIndexOfExerciseRestSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseRestSnapshot");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final List<SetEntryEntity> _result = new ArrayList<SetEntryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SetEntryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpExerciseId;
            if (_cursor.isNull(_cursorIndexOfExerciseId)) {
              _tmpExerciseId = null;
            } else {
              _tmpExerciseId = _cursor.getString(_cursorIndexOfExerciseId);
            }
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            final String _tmpSetType;
            if (_cursor.isNull(_cursorIndexOfSetType)) {
              _tmpSetType = null;
            } else {
              _tmpSetType = _cursor.getString(_cursorIndexOfSetType);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Integer _tmpRpe;
            if (_cursor.isNull(_cursorIndexOfRpe)) {
              _tmpRpe = null;
            } else {
              _tmpRpe = _cursor.getInt(_cursorIndexOfRpe);
            }
            final String _tmpExerciseNameSnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseNameSnapshot)) {
              _tmpExerciseNameSnapshot = null;
            } else {
              _tmpExerciseNameSnapshot = _cursor.getString(_cursorIndexOfExerciseNameSnapshot);
            }
            final String _tmpExerciseCategorySnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseCategorySnapshot)) {
              _tmpExerciseCategorySnapshot = null;
            } else {
              _tmpExerciseCategorySnapshot = _cursor.getString(_cursorIndexOfExerciseCategorySnapshot);
            }
            final int _tmpExerciseRestSnapshot;
            _tmpExerciseRestSnapshot = _cursor.getInt(_cursorIndexOfExerciseRestSnapshot);
            final long _tmpDurationMillis;
            _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            _item = new SetEntryEntity(_tmpId,_tmpSessionId,_tmpExerciseId,_tmpWeight,_tmpReps,_tmpSetType,_tmpTimestamp,_tmpRpe,_tmpExerciseNameSnapshot,_tmpExerciseCategorySnapshot,_tmpExerciseRestSnapshot,_tmpDurationMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WorkoutSessionEntity>> getCompletedSessions() {
    final String _sql = "SELECT * FROM workout_sessions WHERE endTime IS NOT NULL ORDER BY startTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_sessions"}, new Callable<List<WorkoutSessionEntity>>() {
      @Override
      @NonNull
      public List<WorkoutSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTemplateId = CursorUtil.getColumnIndexOrThrow(_cursor, "templateId");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSingleExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "singleExerciseId");
          final List<WorkoutSessionEntity> _result = new ArrayList<WorkoutSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutSessionEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTemplateId;
            if (_cursor.isNull(_cursorIndexOfTemplateId)) {
              _tmpTemplateId = null;
            } else {
              _tmpTemplateId = _cursor.getString(_cursorIndexOfTemplateId);
            }
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSingleExerciseId;
            if (_cursor.isNull(_cursorIndexOfSingleExerciseId)) {
              _tmpSingleExerciseId = null;
            } else {
              _tmpSingleExerciseId = _cursor.getString(_cursorIndexOfSingleExerciseId);
            }
            _item = new WorkoutSessionEntity(_tmpId,_tmpStartTime,_tmpEndTime,_tmpName,_tmpTemplateId,_tmpSourceType,_tmpSingleExerciseId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLatestSession(final Continuation<? super WorkoutSessionEntity> $completion) {
    final String _sql = "SELECT * FROM workout_sessions ORDER BY startTime DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WorkoutSessionEntity>() {
      @Override
      @Nullable
      public WorkoutSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTemplateId = CursorUtil.getColumnIndexOrThrow(_cursor, "templateId");
          final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
          final int _cursorIndexOfSingleExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "singleExerciseId");
          final WorkoutSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTemplateId;
            if (_cursor.isNull(_cursorIndexOfTemplateId)) {
              _tmpTemplateId = null;
            } else {
              _tmpTemplateId = _cursor.getString(_cursorIndexOfTemplateId);
            }
            final String _tmpSourceType;
            if (_cursor.isNull(_cursorIndexOfSourceType)) {
              _tmpSourceType = null;
            } else {
              _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
            }
            final String _tmpSingleExerciseId;
            if (_cursor.isNull(_cursorIndexOfSingleExerciseId)) {
              _tmpSingleExerciseId = null;
            } else {
              _tmpSingleExerciseId = _cursor.getString(_cursorIndexOfSingleExerciseId);
            }
            _result = new WorkoutSessionEntity(_tmpId,_tmpStartTime,_tmpEndTime,_tmpName,_tmpTemplateId,_tmpSourceType,_tmpSingleExerciseId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SetEntryEntity>> getSetsForSession(final String sessionId) {
    final String _sql = "SELECT * FROM set_entries WHERE sessionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sessionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sessionId);
    }
    return CoroutinesRoom.createFlow(__db, true, new String[] {"set_entries"}, new Callable<List<SetEntryEntity>>() {
      @Override
      @NonNull
      public List<SetEntryEntity> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
            final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
            final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
            final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
            final int _cursorIndexOfSetType = CursorUtil.getColumnIndexOrThrow(_cursor, "setType");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final int _cursorIndexOfRpe = CursorUtil.getColumnIndexOrThrow(_cursor, "rpe");
            final int _cursorIndexOfExerciseNameSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseNameSnapshot");
            final int _cursorIndexOfExerciseCategorySnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseCategorySnapshot");
            final int _cursorIndexOfExerciseRestSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseRestSnapshot");
            final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
            final List<SetEntryEntity> _result = new ArrayList<SetEntryEntity>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final SetEntryEntity _item;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpSessionId;
              if (_cursor.isNull(_cursorIndexOfSessionId)) {
                _tmpSessionId = null;
              } else {
                _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
              }
              final String _tmpExerciseId;
              if (_cursor.isNull(_cursorIndexOfExerciseId)) {
                _tmpExerciseId = null;
              } else {
                _tmpExerciseId = _cursor.getString(_cursorIndexOfExerciseId);
              }
              final double _tmpWeight;
              _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
              final int _tmpReps;
              _tmpReps = _cursor.getInt(_cursorIndexOfReps);
              final String _tmpSetType;
              if (_cursor.isNull(_cursorIndexOfSetType)) {
                _tmpSetType = null;
              } else {
                _tmpSetType = _cursor.getString(_cursorIndexOfSetType);
              }
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              final Integer _tmpRpe;
              if (_cursor.isNull(_cursorIndexOfRpe)) {
                _tmpRpe = null;
              } else {
                _tmpRpe = _cursor.getInt(_cursorIndexOfRpe);
              }
              final String _tmpExerciseNameSnapshot;
              if (_cursor.isNull(_cursorIndexOfExerciseNameSnapshot)) {
                _tmpExerciseNameSnapshot = null;
              } else {
                _tmpExerciseNameSnapshot = _cursor.getString(_cursorIndexOfExerciseNameSnapshot);
              }
              final String _tmpExerciseCategorySnapshot;
              if (_cursor.isNull(_cursorIndexOfExerciseCategorySnapshot)) {
                _tmpExerciseCategorySnapshot = null;
              } else {
                _tmpExerciseCategorySnapshot = _cursor.getString(_cursorIndexOfExerciseCategorySnapshot);
              }
              final int _tmpExerciseRestSnapshot;
              _tmpExerciseRestSnapshot = _cursor.getInt(_cursorIndexOfExerciseRestSnapshot);
              final long _tmpDurationMillis;
              _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
              _item = new SetEntryEntity(_tmpId,_tmpSessionId,_tmpExerciseId,_tmpWeight,_tmpReps,_tmpSetType,_tmpTimestamp,_tmpRpe,_tmpExerciseNameSnapshot,_tmpExerciseCategorySnapshot,_tmpExerciseRestSnapshot,_tmpDurationMillis);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSetsForSessionList(final String sessionId,
      final Continuation<? super List<SetEntryEntity>> $completion) {
    final String _sql = "SELECT * FROM set_entries WHERE sessionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sessionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sessionId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SetEntryEntity>>() {
      @Override
      @NonNull
      public List<SetEntryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfSetType = CursorUtil.getColumnIndexOrThrow(_cursor, "setType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfRpe = CursorUtil.getColumnIndexOrThrow(_cursor, "rpe");
          final int _cursorIndexOfExerciseNameSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseNameSnapshot");
          final int _cursorIndexOfExerciseCategorySnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseCategorySnapshot");
          final int _cursorIndexOfExerciseRestSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseRestSnapshot");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final List<SetEntryEntity> _result = new ArrayList<SetEntryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SetEntryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpExerciseId;
            if (_cursor.isNull(_cursorIndexOfExerciseId)) {
              _tmpExerciseId = null;
            } else {
              _tmpExerciseId = _cursor.getString(_cursorIndexOfExerciseId);
            }
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            final String _tmpSetType;
            if (_cursor.isNull(_cursorIndexOfSetType)) {
              _tmpSetType = null;
            } else {
              _tmpSetType = _cursor.getString(_cursorIndexOfSetType);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Integer _tmpRpe;
            if (_cursor.isNull(_cursorIndexOfRpe)) {
              _tmpRpe = null;
            } else {
              _tmpRpe = _cursor.getInt(_cursorIndexOfRpe);
            }
            final String _tmpExerciseNameSnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseNameSnapshot)) {
              _tmpExerciseNameSnapshot = null;
            } else {
              _tmpExerciseNameSnapshot = _cursor.getString(_cursorIndexOfExerciseNameSnapshot);
            }
            final String _tmpExerciseCategorySnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseCategorySnapshot)) {
              _tmpExerciseCategorySnapshot = null;
            } else {
              _tmpExerciseCategorySnapshot = _cursor.getString(_cursorIndexOfExerciseCategorySnapshot);
            }
            final int _tmpExerciseRestSnapshot;
            _tmpExerciseRestSnapshot = _cursor.getInt(_cursorIndexOfExerciseRestSnapshot);
            final long _tmpDurationMillis;
            _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            _item = new SetEntryEntity(_tmpId,_tmpSessionId,_tmpExerciseId,_tmpWeight,_tmpReps,_tmpSetType,_tmpTimestamp,_tmpRpe,_tmpExerciseNameSnapshot,_tmpExerciseCategorySnapshot,_tmpExerciseRestSnapshot,_tmpDurationMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastSetForExercise(final String exerciseId,
      final Continuation<? super SetEntryEntity> $completion) {
    final String _sql = "SELECT * FROM set_entries WHERE exerciseId = ? ORDER BY timestamp DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (exerciseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, exerciseId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SetEntryEntity>() {
      @Override
      @Nullable
      public SetEntryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final int _cursorIndexOfSetType = CursorUtil.getColumnIndexOrThrow(_cursor, "setType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfRpe = CursorUtil.getColumnIndexOrThrow(_cursor, "rpe");
          final int _cursorIndexOfExerciseNameSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseNameSnapshot");
          final int _cursorIndexOfExerciseCategorySnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseCategorySnapshot");
          final int _cursorIndexOfExerciseRestSnapshot = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseRestSnapshot");
          final int _cursorIndexOfDurationMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMillis");
          final SetEntryEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSessionId;
            if (_cursor.isNull(_cursorIndexOfSessionId)) {
              _tmpSessionId = null;
            } else {
              _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            }
            final String _tmpExerciseId;
            if (_cursor.isNull(_cursorIndexOfExerciseId)) {
              _tmpExerciseId = null;
            } else {
              _tmpExerciseId = _cursor.getString(_cursorIndexOfExerciseId);
            }
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            final String _tmpSetType;
            if (_cursor.isNull(_cursorIndexOfSetType)) {
              _tmpSetType = null;
            } else {
              _tmpSetType = _cursor.getString(_cursorIndexOfSetType);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Integer _tmpRpe;
            if (_cursor.isNull(_cursorIndexOfRpe)) {
              _tmpRpe = null;
            } else {
              _tmpRpe = _cursor.getInt(_cursorIndexOfRpe);
            }
            final String _tmpExerciseNameSnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseNameSnapshot)) {
              _tmpExerciseNameSnapshot = null;
            } else {
              _tmpExerciseNameSnapshot = _cursor.getString(_cursorIndexOfExerciseNameSnapshot);
            }
            final String _tmpExerciseCategorySnapshot;
            if (_cursor.isNull(_cursorIndexOfExerciseCategorySnapshot)) {
              _tmpExerciseCategorySnapshot = null;
            } else {
              _tmpExerciseCategorySnapshot = _cursor.getString(_cursorIndexOfExerciseCategorySnapshot);
            }
            final int _tmpExerciseRestSnapshot;
            _tmpExerciseRestSnapshot = _cursor.getInt(_cursorIndexOfExerciseRestSnapshot);
            final long _tmpDurationMillis;
            _tmpDurationMillis = _cursor.getLong(_cursorIndexOfDurationMillis);
            _result = new SetEntryEntity(_tmpId,_tmpSessionId,_tmpExerciseId,_tmpWeight,_tmpReps,_tmpSetType,_tmpTimestamp,_tmpRpe,_tmpExerciseNameSnapshot,_tmpExerciseCategorySnapshot,_tmpExerciseRestSnapshot,_tmpDurationMillis);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPersonalRecord(final String exerciseId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT MAX(weight) FROM set_entries WHERE exerciseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (exerciseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, exerciseId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
