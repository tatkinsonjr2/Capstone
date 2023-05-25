package sanctuaryraider.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Component;
import sanctuaryraider.activity.CreateProfileActivity;
import sanctuaryraider.activity.GetProfileActivity;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.requests.GetProfileRequest;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetProfileActivity provideGetProfileActivity();

    CreateProfileActivity provideCreateProfileActivity();

}
