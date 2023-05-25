package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.results.CreateProfileResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.exceptions.InvalidAttributeValueException;
import sanctuaryraider.models.ProfileModel;
import sanctuaryraider.utils.SanctuaryRaiderServiceUtils;

import java.util.function.Supplier;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the CreateProfileActivity for the SanctuaryRaiderService's CreateProfile API.
 * <p>
 * This API allows the customer to create a new profile with specified attributes.
 */

public class CreateProfileActivity {
        private final Logger log = LogManager.getLogger();
        private final ProfileDao profileDao;

        /**
         * Instantiates a new CreatePlaylistActivity object.
         *
         * @param profileDao ProfileDao to access the playlists table.
         */
        @Inject
        public CreateProfileActivity(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

    /**
     * This method handles the incoming request by persisting a new profile
     * with the provided profile name (username).
     * <p>
     * It then returns the newly created profile.
     * <p>
     * If the provided profile name has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createProfileRequest request object containing the profile name (username).
     * @return createProfileResult result object containing the API defined {@link ProfileModel}
     */

        public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest){
            log.info("Received CreateProfileRequest {}", createProfileRequest);

            if(!SanctuaryRaiderServiceUtils.isValidString(createProfileRequest.getUsername())){
                throw new InvalidAttributeValueException("Username [" + createProfileRequest.getUsername() + "] contains illegal characters");
            }

            Profile newProfile = new Profile();
            newProfile.setUsername(createProfileRequest.getUsername());
            newProfile.setGuild(createProfileRequest.getGuild());
            newProfile.setPublicNote(createProfileRequest.getPublicNote());
            newProfile.setOfficerNote(createProfileRequest.getOfficerNote());

            profileDao.saveProfile(newProfile);

            ProfileModel profileModel = new ProfileModelConverter().toProfileModel(newProfile);
            return CreateProfileResult.builder()
                    .withProfile(profileModel)
                    .build();
        }



}
