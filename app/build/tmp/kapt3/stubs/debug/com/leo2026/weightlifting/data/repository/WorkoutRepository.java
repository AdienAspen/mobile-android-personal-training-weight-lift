package com.leo2026.weightlifting.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0015\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010&J\u0016\u0010\'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010,J\u0016\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u0016\u00104\u001a\u00020#2\u0006\u00105\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u000e\u00106\u001a\u000207H\u0086@\u00a2\u0006\u0002\u00108J\u0018\u00109\u001a\u0004\u0018\u00010\u00142\u0006\u0010:\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u001a\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u000e2\u0006\u00101\u001a\u000202J\u0010\u0010<\u001a\u0004\u0018\u00010\u001dH\u0086@\u00a2\u0006\u0002\u00108J\u0018\u0010=\u001a\u0004\u0018\u00010%2\u0006\u0010>\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u0016\u0010?\u001a\u00020@2\u0006\u0010>\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u001a\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u000f0\u000e2\u0006\u00105\u001a\u000202J\u001c\u0010B\u001a\b\u0012\u0004\u0012\u00020%0\u000f2\u0006\u00105\u001a\u000202H\u0086@\u00a2\u0006\u0002\u00103J\u0016\u0010C\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010)J\u0016\u0010D\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010,J\u0016\u0010E\u001a\u00020#2\u0006\u0010.\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010/J\u0016\u0010F\u001a\u00020#2\u0006\u0010G\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010HJ\u0016\u0010I\u001a\u00020#2\u0006\u0010J\u001a\u000207H\u0086@\u00a2\u0006\u0002\u0010KJ$\u0010L\u001a\u00020#2\u0006\u0010M\u001a\u0002022\f\u0010N\u001a\b\u0012\u0004\u0012\u0002020\u000fH\u0086@\u00a2\u0006\u0002\u0010OJ\"\u0010P\u001a\u0002022\u0006\u0010M\u001a\u0002022\n\b\u0002\u00101\u001a\u0004\u0018\u000102H\u0086@\u00a2\u0006\u0002\u0010QJ\u0016\u0010R\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010,J\u001e\u0010S\u001a\u00020#2\u0006\u00101\u001a\u0002022\u0006\u0010T\u001a\u000202H\u0086@\u00a2\u0006\u0002\u0010QR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006U"}, d2 = {"Lcom/leo2026/weightlifting/data/repository/WorkoutRepository;", "", "exerciseDao", "Lcom/leo2026/weightlifting/data/dao/ExerciseDao;", "workoutDao", "Lcom/leo2026/weightlifting/data/dao/WorkoutDao;", "templateDao", "Lcom/leo2026/weightlifting/data/dao/TemplateDao;", "assetDao", "Lcom/leo2026/weightlifting/data/dao/AssetDao;", "userDao", "Lcom/leo2026/weightlifting/data/dao/UserDao;", "(Lcom/leo2026/weightlifting/data/dao/ExerciseDao;Lcom/leo2026/weightlifting/data/dao/WorkoutDao;Lcom/leo2026/weightlifting/data/dao/TemplateDao;Lcom/leo2026/weightlifting/data/dao/AssetDao;Lcom/leo2026/weightlifting/data/dao/UserDao;)V", "allBars", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/leo2026/weightlifting/data/entity/BarEntity;", "getAllBars", "()Lkotlinx/coroutines/flow/Flow;", "allExercises", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "getAllExercises", "allPlates", "Lcom/leo2026/weightlifting/data/entity/PlateEntity;", "getAllPlates", "allTemplates", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "getAllTemplates", "completedSessions", "Lcom/leo2026/weightlifting/data/entity/WorkoutSessionEntity;", "getCompletedSessions", "userProfile", "Lcom/leo2026/weightlifting/data/entity/UserEntity;", "getUserProfile", "addSet", "", "set", "Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;", "(Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBar", "bar", "(Lcom/leo2026/weightlifting/data/entity/BarEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExercise", "exercise", "(Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlate", "plate", "(Lcom/leo2026/weightlifting/data/entity/PlateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTemplate", "templateId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishSession", "sessionId", "getBackupBundle", "Lcom/leo2026/weightlifting/data/backup/BackupBundle;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExerciseById", "id", "getExercisesForTemplate", "getLastSession", "getLastSetForExercise", "exerciseId", "getPersonalRecord", "", "getSetsForSession", "getSetsForSessionList", "insertBar", "insertExercise", "insertPlate", "insertUser", "user", "(Lcom/leo2026/weightlifting/data/entity/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreBackupBundle", "bundle", "(Lcom/leo2026/weightlifting/data/backup/BackupBundle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveAsTemplate", "name", "exerciseIds", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startNewSession", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateExercise", "updateTemplateName", "newName", "app_debug"})
public final class WorkoutRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.dao.ExerciseDao exerciseDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.dao.WorkoutDao workoutDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.dao.TemplateDao templateDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.dao.AssetDao assetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> allExercises = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> completedSessions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> allTemplates = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.leo2026.weightlifting.data.entity.UserEntity> userProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.BarEntity>> allBars = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity>> allPlates = null;
    
    public WorkoutRepository(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.dao.ExerciseDao exerciseDao, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.dao.WorkoutDao workoutDao, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.dao.TemplateDao templateDao, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.dao.AssetDao assetDao, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.dao.UserDao userDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getAllExercises() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> getCompletedSessions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> getAllTemplates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.leo2026.weightlifting.data.entity.UserEntity> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.UserEntity user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.BarEntity>> getAllBars() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity>> getAllPlates() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertBar(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.BarEntity bar, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBar(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.BarEntity bar, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.PlateEntity plate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.PlateEntity plate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getExerciseById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.ExerciseEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object startNewSession(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object finishSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveAsTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> exerciseIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTemplateName(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    java.lang.String newName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLastSession(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.WorkoutSessionEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getExercisesForTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> getSetsForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSetsForSessionList(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSet(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.SetEntryEntity set, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLastSetForExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.SetEntryEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPersonalRecord(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBackupBundle(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.backup.BackupBundle> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object restoreBackupBundle(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.backup.BackupBundle bundle, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}