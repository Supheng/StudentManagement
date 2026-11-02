package kh.com.supheng.jetpack.compose.studentmanagement.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDatabase;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideStudentDatabaseFactory implements Factory<StudentDatabase> {
  private final Provider<Context> contextProvider;

  private AppModule_ProvideStudentDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StudentDatabase get() {
    return provideStudentDatabase(contextProvider.get());
  }

  public static AppModule_ProvideStudentDatabaseFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideStudentDatabaseFactory(contextProvider);
  }

  public static StudentDatabase provideStudentDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStudentDatabase(context));
  }
}
