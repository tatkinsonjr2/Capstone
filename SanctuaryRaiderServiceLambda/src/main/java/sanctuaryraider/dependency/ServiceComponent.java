package sanctuaryraider.dependency;

import dagger.Component;
import sanctuaryraider.activity.*;

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

  CreateRaidActivity provideCreateRaidActivity();
  GetRaidActivity provideGetRaidActivity();

  UpdateRaidActivity provideUpdateRaidActivity();

  GetAllCharactersActivity provideGetAllCharactersActivity();

  GetAllRaidsByCharacterNameActivity provideGetAllRaidsByCharacterNameActivity();

  DeleteCharacterActivity provideDeleteCharacterActivity();
}
