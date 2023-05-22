package sanctuaryraider.activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetProfileRequest;
import sanctuaryraider.activity.results.GetProfileResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.models.ProfileModel;

import javax.inject.Inject;

/**
 * Implementation of the GetProfileActivity for the Sanctuary Raider's GetProfile API.
 * This API allows a user to get a previously saved profile by its username.
 */

public class GetProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final ProfileDao profileDao;


    /**
     * Instantiating a new GetProfileActivity object.
     * @param profileDao ProfileDao to access the profiles table.
     */
    @Inject
    public GetProfileActivity(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    /**
     * This method handles the incoming request by retrieving the profile from the database.
     * It then returns the profile.
     * If the profile does not exist, this should throw a ProfileNotFoundException.
     *
     * @param getProfileRequest request an object containing the project ID.
     * @return getProfileResult result object containing the API defined {@link ProfileModel}.
     */

    public GetProfileResult handleRequest(final GetProfileRequest getProfileRequest){
        log.info("Received GetProfile Request {}", getProfileRequest);
        String requestedUsername = getProfileRequest.getUsername();
        Profile profile = profileDao.getProfile(requestedUsername);
        ProfileModel profileModel = new ProfileModelConverter().toProfileModel(profile);

        return GetProfileResult.builder().withProfile(profileModel).build();
    }
}
