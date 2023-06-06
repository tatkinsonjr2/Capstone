package sanctuaryraider.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Component;
import sanctuaryraider.activity.*;
import sanctuaryraider.activity.requests.*;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetProfileActivity provideGetProfileActivity();

    CreateProfileActivity provideCreateProfileActivity();

    UpdateProfileActivity provideUpdateProfileActivity();

    GetCharacterActivity provideGetCharacterActivity();

    CreateCharacterActivity provideCreateCharacterActivity();

   UpdateCharacterActivity provideUpdateCharacterActivity();

   GetRaidActivity provideGetRaidActivity();
}
