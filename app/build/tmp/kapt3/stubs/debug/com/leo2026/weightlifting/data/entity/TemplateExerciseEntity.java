package com.leo2026.weightlifting.data.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/leo2026/weightlifting/data/entity/TemplateExerciseEntity;", "", "templateId", "", "exerciseId", "orderIndex", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getExerciseId", "()Ljava/lang/String;", "getOrderIndex", "()I", "getTemplateId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "template_exercises", foreignKeys = {@androidx.room.ForeignKey(entity = com.leo2026.weightlifting.data.entity.TemplateEntity.class, parentColumns = {"id"}, childColumns = {"templateId"}, onDelete = 5)}, primaryKeys = {"templateId", "exerciseId", "orderIndex"})
public final class TemplateExerciseEntity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String templateId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exerciseId = null;
    private final int orderIndex = 0;
    
    public TemplateExerciseEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, int orderIndex) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTemplateId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExerciseId() {
        return null;
    }
    
    public final int getOrderIndex() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.leo2026.weightlifting.data.entity.TemplateExerciseEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String templateId, @org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, int orderIndex) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}