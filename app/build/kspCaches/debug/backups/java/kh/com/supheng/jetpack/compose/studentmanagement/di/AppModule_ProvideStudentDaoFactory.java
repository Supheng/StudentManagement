package kh.com.supheng.jetpack.compose.studentmanagement.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDao;
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentDatabase;

@ScopeMetadata
@QualifierMetadata
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
public final class AppModule_ProvideStudentDaoFactory implements Factory<StudentDao> {
  private final Provider<StudentDatabase> databaseProvider;

  private AppModule_ProvideStudentDaoFactory(Provider<StudentDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StudentDao get() {
    return provideStudentDao(databaseProvider.get());
  }

  public static AppModule_ProvideStudentDaoFactory create(
      Provider<StudentDatabase> databaseProvider) {
    return new AppModule_ProvideStudentDaoFactory(databaseProvider);
  }

  public static StudentDao provideStudentDao(StudentDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStudentDao(database));
  }
}
