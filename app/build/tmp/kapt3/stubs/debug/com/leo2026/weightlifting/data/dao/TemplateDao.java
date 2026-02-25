package com.leo2026.weightlifting.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\t0\rH\'J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\r2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006\""}, d2 = {"Lcom/leo2026/weightlifting/data/dao/TemplateDao;", "", "deleteExercisesFromTemplate", "", "templateId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTemplateById", "getAllTemplateExercisesList", "", "Lcom/leo2026/weightlifting/data/entity/TemplateExerciseEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTemplates", "Lkotlinx/coroutines/flow/Flow;", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "getAllTemplatesList", "getExercisesForTemplate", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "getTemplateWithExercises", "insertTemplate", "template", "(Lcom/leo2026/weightlifting/data/entity/TemplateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTemplateExercise", "templateExercise", "(Lcom/leo2026/weightlifting/data/entity/TemplateExerciseEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTemplateExercises", "templateExercises", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTemplates", "templates", "softDeleteTemplate", "updateTemplateName", "newName", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface TemplateDao {
    
    @androidx.room.Query(value = "SELECT * FROM templates WHERE isDeleted = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> getAllTemplates();
    
    @androidx.room.Query(value = "SELECT * FROM templates")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTemplatesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM template_exercises")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTemplateExercisesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.leo2026.weightlifting.data.entity.TemplateExerciseEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTemplates(@org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity> templates, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTemplateExercises(@org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.TemplateExerciseEntity> templateExercises, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTemplate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.TemplateEntity template, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTemplateExercise(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.TemplateExerciseEntity templateExercise, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE templates SET name = :newName WHERE id = :templateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTemplateName(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    java.lang.String newName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE templates SET isDeleted = 1 WHERE id = :templateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object softDeleteTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM templates WHERE id = :templateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTemplateById(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM template_exercises WHERE templateId = :templateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteExercisesFromTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT e.* FROM exercises e\n        INNER JOIN template_exercises te ON e.id = te.exerciseId\n        WHERE te.templateId = :templateId\n        ORDER BY te.orderIndex\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity>> getExercisesForTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId);
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM templates WHERE id = :templateId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTemplateWithExercises(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.leo2026.weightlifting.data.entity.TemplateEntity> $completion);
}