package com.leo2026.weightlifting.ui.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u001aV\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0005\u0012\u0004\u0012\u00020\u00010\fH\u0007\u001a:\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\u001e\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u00a8\u0006\u0014"}, d2 = {"RoutineEditorDialog", "", "template", "Lcom/leo2026/weightlifting/data/entity/TemplateEntity;", "allExercises", "", "Lcom/leo2026/weightlifting/data/entity/ExerciseEntity;", "viewModel", "Lcom/leo2026/weightlifting/ui/viewmodel/WorkoutViewModel;", "onDismiss", "Lkotlin/Function0;", "onSave", "Lkotlin/Function2;", "", "TemplateItem", "onEdit", "onDelete", "onClick", "TemplatesScreen", "onStartWorkout", "app_debug"})
public final class TemplatesScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void TemplatesScreen(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStartWorkout) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void RoutineEditorDialog(@org.jetbrains.annotations.Nullable()
    com.leo2026.weightlifting.data.entity.TemplateEntity template, @org.jetbrains.annotations.NotNull()
    java.util.List<com.leo2026.weightlifting.data.entity.ExerciseEntity> allExercises, @org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.util.List<java.lang.String>, kotlin.Unit> onSave) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TemplateItem(@org.jetbrains.annotations.NotNull()
    com.leo2026.weightlifting.data.entity.TemplateEntity template, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}