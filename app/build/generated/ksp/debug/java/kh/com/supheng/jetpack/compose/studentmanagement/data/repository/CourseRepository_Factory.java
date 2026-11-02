package kh.com.supheng.jetpack.compose.studentmanagement.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseDao;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CourseRepository_Factory implements Factory<CourseRepository> {
  private final Provider<CourseDao> courseDaoProvider;

  private CourseRepository_Factory(Provider<CourseDao> courseDaoProvider) {
    this.courseDaoProvider = courseDaoProvider;
  }

  @Override
  public CourseRepository get() {
    return newInstance(courseDaoProvider.get());
  }

  public static CourseRepository_Factory create(Provider<CourseDao> courseDaoProvider) {
    return new CourseRepository_Factory(courseDaoProvider);
  }

  public static CourseRepository newInstance(CourseDao courseDao) {
    return new CourseRepository(courseDao);
  }
}
