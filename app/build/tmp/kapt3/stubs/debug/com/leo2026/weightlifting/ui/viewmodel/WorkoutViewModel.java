package com.leo2026.weightlifting.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020<J\u001e\u0010=\u001a\u00020>2\u0006\u0010:\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u000fJ(\u0010A\u001a\u0004\u0018\u00010\u00072\u0006\u0010:\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u0002092\u0006\u0010;\u001a\u00020<2\u0006\u0010D\u001a\u00020\u000fJ.\u0010E\u001a\u0002092\u0006\u0010F\u001a\u00020\u00072\u0006\u0010;\u001a\u00020<2\u0006\u0010G\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020IJ\u0006\u0010J\u001a\u000209J\u000e\u0010K\u001a\u0002092\u0006\u0010L\u001a\u00020\u0012J\u000e\u0010M\u001a\u0002092\u0006\u0010N\u001a\u00020\u0016J\u000e\u0010O\u001a\u0002092\u0006\u0010P\u001a\u00020\u0019J\u000e\u0010Q\u001a\u0002092\u0006\u0010R\u001a\u00020\u0007J\u0006\u0010S\u001a\u000209J\u000e\u0010T\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010UJ\u0006\u0010V\u001a\u000209J\u000e\u0010W\u001a\u0002092\u0006\u0010:\u001a\u00020\u0007J\u001a\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0Y2\u0006\u0010R\u001a\u00020\u0007J\u0018\u0010Z\u001a\u0004\u0018\u00010%2\u0006\u0010F\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010[J\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0Y2\u0006\u0010^\u001a\u00020\u0007J\u001a\u0010_\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\n0Y2\u0006\u0010^\u001a\u00020\u0007J\u001c\u0010`\u001a\u0002092\u0006\u0010a\u001a\u00020\u00072\f\u0010b\u001a\b\u0012\u0004\u0012\u0002090cJ\u000e\u0010d\u001a\u00020>2\u0006\u0010e\u001a\u00020<J\u0006\u0010f\u001a\u000209J\u0006\u0010g\u001a\u000209J(\u0010h\u001a\u0002092\u0006\u0010:\u001a\u00020\u00072\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\u0007J\u0006\u0010k\u001a\u000209J\u001c\u0010l\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020\u00072\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u0007J\u000e\u0010m\u001a\u0002092\u0006\u0010N\u001a\u00020\u0016J\u000e\u0010n\u001a\u00020>2\u0006\u0010N\u001a\u00020\u0016J\u0016\u0010o\u001a\u0002092\u0006\u0010p\u001a\u00020\u00072\u0006\u0010q\u001a\u00020\u0007J\u0016\u0010r\u001a\u00020>2\u0006\u0010R\u001a\u00020\u00072\u0006\u0010s\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014R(\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\n0\u00118\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\'\u001a\u0004\b(\u0010\u0014R(\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\n0\u00118\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\'\u001a\u0004\b+\u0010\u0014R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0014R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\n0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0014R\u0019\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0014R\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001060\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0014\u00a8\u0006t"}, d2 = {"Lcom/leo2026/weightlifting/ui/viewmodel/WorkoutViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/leo2026/weightlifting/data/repository/WorkoutRepository;", "(Lcom/leo2026/weightlifting/data/repository/WorkoutRepository;)V", "_currentSessionId", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentSessionName", "_manualSessionExerciseIds", "", "_newPREvents", "_selectedTemplateId", "_showPartialTemplateDialog", "_timerTrigger", "", "allBars", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/leo2026/weightlifting/data/entity/BarEntity;", "getAllBars", "()Lkotlinx/coroutines/flow/StateFlow;", "allExercises", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "getAllExercises", "allPlates", "Lcom/leo2026/weightlifting/data/entity/PlateEntity;", "getAllPlates", "backupManager", "Lcom/leo2026/weightlifting/data/backup/BackupManager;", "completedSessions", "Lcom/leo2026/weightlifting/data/entity/WorkoutSessionEntity;", "getCompletedSessions", "currentSessionId", "getCurrentSessionId", "currentSessionName", "getCurrentSessionName", "currentSets", "Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;", "getCurrentSets$annotations", "()V", "getCurrentSets", "exercises", "getExercises$annotations", "getExercises", "newPREvents", "getNewPREvents", "showPartialTemplateDialog", "getShowPartialTemplateDialog", "templates", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "getTemplates", "timerTrigger", "getTimerTrigger", "userProfile", "Lcom/leo2026/weightlifting/data/entity/UserEntity;", "getUserProfile", "addBar", "", "name", "weight", "", "addExercise", "", "category", "restSeconds", "addExerciseAndGetId", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addPlate", "quantity", "addSet", "exerciseId", "reps", "durationMillis", "", "clearPREvent", "deleteBar", "bar", "deleteExercise", "exercise", "deletePlate", "plate", "deleteTemplate", "templateId", "dismissPartialDialog", "exportData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishWorkout", "finishWorkoutAndSavePartial", "getExerciseIdsForTemplate", "Lkotlinx/coroutines/flow/Flow;", "getLastSetForExercise", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSessionSummary", "Lcom/leo2026/weightlifting/ui/viewmodel/SessionSummary;", "sessionId", "getSetsForSession", "importData", "json", "onComplete", "Lkotlin/Function0;", "isWeightPossible", "targetWeight", "onTimerStarted", "resetToWorkoutLauncher", "saveRoutine", "exerciseIds", "existingId", "startFromLastSession", "startWorkout", "startWorkoutWithExercise", "updateExercise", "updateProfile", "firstName", "lastName", "updateTemplateName", "newName", "app_debug"})
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
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _showPartialTemplateDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> showPartialTemplateDialog = null;
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
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getShowPartialTemplateDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getExercises() {
        return null;
    }
    
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @java.lang.Deprecated()
    public static void getExercises$annotations() {
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
    
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @java.lang.Deprecated()
    public static void getCurrentSets$annotations() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.leo2026.weightlifting.ui.viewmodel.SessionSummary> getSessionSummary(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
        return null;
    }
    
    public final void resetToWorkoutLauncher() {
    }
    
    public final void saveRoutine(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> exerciseIds, @org.jetbrains.annotations.Nullable()
    java.lang.String existingId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getExerciseIdsForTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addExerciseAndGetId(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String category, int restSeconds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLastSetForExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.SetEntryEntity> $completion) {
        return null;
    }
    
    public final boolean isWeightPossible(double targetWeight) {
        return false;
    }
    
    public final void finishWorkout() {
    }
    
    public final void dismissPartialDialog() {
    }
    
    public final void finishWorkoutAndSavePartial(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
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