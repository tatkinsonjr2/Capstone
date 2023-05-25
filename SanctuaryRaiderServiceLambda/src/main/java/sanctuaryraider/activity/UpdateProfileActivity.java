package sanctuaryraider.activity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.UpdateProfileRequest;
import sanctuaryraider.activity.results.UpdateProfileResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;

import javax.inject.Inject;

public class UpdateProfileActivity {

    private final Logger log = LogManager.getLogger();

    private final ProfileDao profileDao;

    @Inject
    public UpdateProfileActivity(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    public UpdateProfileResult handleRequest(final UpdateProfileRequest updateProfileRequest){
        log.info("Received UpdateProfileRequest {}", updateProfileRequest);

        Profile profile = profileDao.getProfile(updateProfileRequest.getUsername());

        profile.setOfficerNote(updateProfileRequest.getOfficerNote());
        profile.setPublicNote(updateProfileRequest.getPublicNote());
        profile.setGuild(updateProfileRequest.getGuild());

        profileDao.saveProfile(profile);

        return UpdateProfileResult.builder().withProfile(new ProfileModelConverter().toProfileModel(profile)).build();
    }
}
