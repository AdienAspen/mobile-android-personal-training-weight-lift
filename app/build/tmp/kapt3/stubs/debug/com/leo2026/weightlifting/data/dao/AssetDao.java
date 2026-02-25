package com.leo2026.weightlifting.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\fH\'J\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lcom/leo2026/weightlifting/data/dao/AssetDao;", "", "deleteBar", "", "bar", "Lcom/leo2026/weightlifting/data/entity/BarEntity;", "(Lcom/leo2026/weightlifting/data/entity/BarEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlate", "plate", "Lcom/leo2026/weightlifting/data/entity/PlateEntity;", "(Lcom/leo2026/weightlifting/data/entity/PlateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBars", "Lkotlinx/coroutines/flow/Flow;", "", "getAllPlates", "insertBar", "insertPlate", "app_debug"})
@androidx.room.Dao()
public abstract interface AssetDao {
    
    @androidx.room.Query(value = "SELECT * FROM bars ORDER BY weight DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.BarEntity>> getAllBars();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBar(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.BarEntity bar, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBar(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.BarEntity bar, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM plates ORDER BY weight DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity>> getAllPlates();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPlate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.PlateEntity plate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePlate(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.PlateEntity plate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}