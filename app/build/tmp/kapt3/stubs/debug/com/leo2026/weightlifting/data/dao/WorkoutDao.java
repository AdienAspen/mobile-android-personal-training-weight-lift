package com.leo2026.weightlifting.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0013H\'J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0015\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00132\u0006\u0010\b\u001a\u00020\tH\'J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00a7@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010#\u001a\u00020\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006%"}, d2 = {"Lcom/leo2026/weightlifting/data/dao/WorkoutDao;", "", "deleteSet", "", "set", "Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;", "(Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endSession", "sessionId", "", "endTime", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSessionsList", "", "Lcom/leo2026/weightlifting/data/entity/WorkoutSessionEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSetsList", "getCompletedSessions", "Lkotlinx/coroutines/flow/Flow;", "getLastSetForExercise", "exerciseId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestSession", "getPersonalRecord", "", "getSetsForSession", "getSetsForSessionList", "insertSession", "session", "(Lcom/leo2026/weightlifting/data/entity/WorkoutSessionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSessions", "sessions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSet", "insertSets", "sets", "app_debug"})
@androidx.room.Dao()
public abstract interface WorkoutDao {
    
    @androidx.room.Query(value = "SELECT * FROM workout_sessions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSessionsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM set_entries")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSetsList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSessions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity> sessions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSets(@org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity> sets, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM workout_sessions WHERE endTime IS NOT NULL ORDER BY startTime DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> getCompletedSessions();
    
    @androidx.room.Query(value = "SELECT * FROM workout_sessions ORDER BY startTime DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestSession(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.WorkoutSessionEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSession(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.WorkoutSessionEntity session, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE workout_sessions SET endTime = :endTime WHERE id = :sessionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object endSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM set_entries WHERE sessionId = :sessionId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> getSetsForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId);
    
    @androidx.room.Query(value = "SELECT * FROM set_entries WHERE sessionId = :sessionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSetsForSessionList(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSet(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.SetEntryEntity set, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSet(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.SetEntryEntity set, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM set_entries WHERE exerciseId = :exerciseId ORDER BY timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLastSetForExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.SetEntryEntity> $completion);
    
    @androidx.room.Query(value = "SELECT MAX(weight) FROM set_entries WHERE exerciseId = :exerciseId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPersonalRecord(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
}