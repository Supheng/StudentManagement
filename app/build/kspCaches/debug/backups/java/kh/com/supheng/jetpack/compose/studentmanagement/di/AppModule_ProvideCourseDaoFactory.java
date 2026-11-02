package kh.com.supheng.jetpack.compose.studentmanagement.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseDao;
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
public final class AppModule_ProvideCourseDaoFactory implements Factory<CourseDao> {
  private final Provider<StudentDatabase> databaseProvider;

  private AppModule_ProvideCourseDaoFactory(Provider<StudentDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CourseDao get() {
    return provideCourseDao(databaseProvider.get());
  }

  public static AppModule_ProvideCourseDaoFactory create(
      Provider<StudentDatabase> databaseProvider) {
    return new AppModule_ProvideCourseDaoFactory(databaseProvider);
  }

  public static CourseDao provideCourseDao(StudentDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCourseDao(database));
  }
}
