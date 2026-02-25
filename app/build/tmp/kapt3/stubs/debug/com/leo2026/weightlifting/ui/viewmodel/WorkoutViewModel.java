package com.leo2026.weightlifting.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u000206J\u001e\u00107\u001a\u0002082\u0006\u00104\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u000eJ\u0016\u0010;\u001a\u0002032\u0006\u00105\u001a\u0002062\u0006\u0010<\u001a\u00020\u000eJ.\u0010=\u001a\u0002032\u0006\u0010>\u001a\u00020\u00072\u0006\u00105\u001a\u0002062\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020AJ\u0006\u0010B\u001a\u000203J\u000e\u0010C\u001a\u0002032\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010E\u001a\u0002032\u0006\u0010F\u001a\u00020\u0015J\u000e\u0010G\u001a\u0002032\u0006\u0010H\u001a\u00020\u0018J\u000e\u0010I\u001a\u0002032\u0006\u0010J\u001a\u00020\u0007J\u000e\u0010K\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010LJ\u0006\u0010M\u001a\u000203J\u001a\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\n0O2\u0006\u0010P\u001a\u00020\u0007J\u001c\u0010Q\u001a\u0002032\u0006\u0010R\u001a\u00020\u00072\f\u0010S\u001a\b\u0012\u0004\u0012\u0002030TJ\u0006\u0010U\u001a\u000203J\u000e\u0010V\u001a\u0002082\u0006\u00104\u001a\u00020\u0007J\u0006\u0010W\u001a\u000203J\u001c\u0010X\u001a\u0002032\b\b\u0002\u00104\u001a\u00020\u00072\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u0007J\u000e\u0010Y\u001a\u0002032\u0006\u0010F\u001a\u00020\u0015J\u000e\u0010Z\u001a\u0002082\u0006\u0010F\u001a\u00020\u0015J\u0016\u0010[\u001a\u0002032\u0006\u0010\\\u001a\u00020\u00072\u0006\u0010]\u001a\u00020\u0007J\u0016\u0010^\u001a\u0002082\u0006\u0010J\u001a\u00020\u00072\u0006\u0010_\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0013R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0013R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0013R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0013R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001000\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0013\u00a8\u0006`"}, d2 = {"Lcom/leo2026/weightlifting/ui/viewmodel/WorkoutViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/leo2026/weightlifting/data/repository/WorkoutRepository;", "(Lcom/leo2026/weightlifting/data/repository/WorkoutRepository;)V", "_currentSessionId", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentSessionName", "_manualSessionExerciseIds", "", "_newPREvents", "_selectedTemplateId", "_timerTrigger", "", "allBars", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/leo2026/weightlifting/data/entity/BarEntity;", "getAllBars", "()Lkotlinx/coroutines/flow/StateFlow;", "allExercises", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "getAllExercises", "allPlates", "Lcom/leo2026/weightlifting/data/entity/PlateEntity;", "getAllPlates", "backupManager", "Lcom/leo2026/weightlifting/data/backup/BackupManager;", "completedSessions", "Lcom/leo2026/weightlifting/data/entity/WorkoutSessionEntity;", "getCompletedSessions", "currentSessionId", "getCurrentSessionId", "currentSessionName", "getCurrentSessionName", "currentSets", "Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;", "getCurrentSets", "exercises", "getExercises", "newPREvents", "getNewPREvents", "templates", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "getTemplates", "timerTrigger", "getTimerTrigger", "userProfile", "Lcom/leo2026/weightlifting/data/entity/UserEntity;", "getUserProfile", "addBar", "", "name", "weight", "", "addExercise", "", "category", "restSeconds", "addPlate", "quantity", "addSet", "exerciseId", "reps", "durationMillis", "", "clearPREvent", "deleteBar", "bar", "deleteExercise", "exercise", "deletePlate", "plate", "deleteTemplate", "templateId", "exportData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishWorkout", "getSetsForSession", "Lkotlinx/coroutines/flow/Flow;", "sessionId", "importData", "json", "onComplete", "Lkotlin/Function0;", "onTimerStarted", "saveCurrentAsTemplate", "startFromLastSession", "startWorkout", "startWorkoutWithExercise", "updateExercise", "updateProfile", "firstName", "lastName", "updateTemplateName", "newName", "app_debug"})
public final class WorkoutViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.repository.WorkoutRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _currentSessionId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> currentSessionId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _currentSessionName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> currentSessionName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedTemplateId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _manualSessionExerciseIds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.leo2026.weightlifting.data.entity.UserEntity> userProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> exercises = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> allExercises = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> templates = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> completedSessions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.BarEntity>> allBars = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity>> allPlates = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _newPREvents = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> newPREvents = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> currentSets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _timerTrigger = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> timerTrigger = null;
    @org.jetbrains.annotations.NotNull()
    private final com.leo2026.weightlifting.data.backup.BackupManager backupManager = null;
    
    public WorkoutViewModel(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.repository.WorkoutRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getCurrentSessionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getCurrentSessionName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.leo2026.weightlifting.data.entity.UserEntity> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getExercises() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getAllExercises() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> getTemplates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.WorkoutSessionEntity>> getCompletedSessions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.BarEntity>> getAllBars() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity>> getAllPlates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getNewPREvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> getCurrentSets() {
        return null;
    }
    
    public final void updateProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity>> getSetsForSession(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
        return null;
    }
    
    public final void startWorkout(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String templateId) {
    }
    
    public final void startWorkoutWithExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise) {
    }
    
    public final void startFromLastSession() {
    }
    
    public final void finishWorkout() {
    }
    
    public final boolean saveCurrentAsTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return false;
    }
    
    public final boolean updateTemplateName(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    java.lang.String newName) {
        return false;
    }
    
    public final void deleteTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId) {
    }
    
    public final boolean updateExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise) {
        return false;
    }
    
    public final void deleteExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise) {
    }
    
    public final void addBar(@org.jetbrains.annotations.NotNull()
    java.lang.String name, double weight) {
    }
    
    public final void deleteBar(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.BarEntity bar) {
    }
    
    public final void addPlate(double weight, int quantity) {
    }
    
    public final void deletePlate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.PlateEntity plate) {
    }
    
    public final void addSet(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, double weight, int reps, int restSeconds, long durationMillis) {
    }
    
    public final void clearPREvent() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTimerTrigger() {
        return null;
    }
    
    public final void onTimerStarted() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    public final void importData(@org.jetbrains.annotations.NotNull()
    java.lang.String json, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    public final boolean addExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String category, int restSeconds) {
        return false;
    }
}