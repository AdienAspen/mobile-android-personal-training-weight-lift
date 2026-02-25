package com.leo2026.weightlifting.ui.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a6\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u008c\u0001\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00162$\u0010\u0017\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0016\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u001aN\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000e2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a*\u0010 \u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0016H\u0007\u001a8\u0010!\u001a\u00020\u00012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u0016H\u0007\u001a\u001e\u0010%\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00a8\u0006\'"}, d2 = {"AddExerciseDialog", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function3;", "", "", "ExerciseCard", "exercise", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "viewModel", "Lcom/leo2026/weightlifting/ui/viewmodel/WorkoutViewModel;", "exerciseSets", "", "Lcom/leo2026/weightlifting/data/entity/SetEntryEntity;", "isResting", "", "isActive", "onActivate", "onDeactivate", "onShowError", "Lkotlin/Function1;", "onCalculatePlates", "Lkotlin/Function2;", "", "PlateCalculatorDialog", "targetWeight", "availableBars", "Lcom/leo2026/weightlifting/data/entity/BarEntity;", "availablePlates", "Lcom/leo2026/weightlifting/data/entity/PlateEntity;", "SaveTemplateDialog", "TemplatePicker", "templates", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "onSelect", "WorkoutScreen", "onNavigateToHistory", "app_debug"})
public final class WorkoutScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void WorkoutScreen(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToHistory) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ExerciseCard(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.ExerciseEntity exercise, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel viewModel, @org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.SetEntryEntity> exerciseSets, boolean isResting, boolean isActive, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onActivate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeactivate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onShowError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super kotlin.jvm.functions.Function1<? super java.lang.Double, kotlin.Unit>, kotlin.Unit> onCalculatePlates) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddExerciseDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.Integer, kotlin.Unit> onConfirm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void PlateCalculatorDialog(double targetWeight, @org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.BarEntity> availableBars, @org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.PlateEntity> availablePlates, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Double, kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TemplatePicker(@org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.TemplateEntity> templates, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.leo2026.weightlifting.data.entity.TemplateEntity, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SaveTemplateDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onConfirm) {
    }
}