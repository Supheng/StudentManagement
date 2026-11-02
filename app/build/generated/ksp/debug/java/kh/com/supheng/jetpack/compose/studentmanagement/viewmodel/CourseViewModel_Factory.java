package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.CourseRepository;

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
public final class CourseViewModel_Factory implements Factory<CourseViewModel> {
  private final Provider<CourseRepository> repositoryProvider;

  private CourseViewModel_Factory(Provider<CourseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CourseViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CourseViewModel_Factory create(Provider<CourseRepository> repositoryProvider) {
    return new CourseViewModel_Factory(repositoryProvider);
  }

  public static CourseViewModel newInstance(CourseRepository repository) {
    return new CourseViewModel(repository);
  }
}
