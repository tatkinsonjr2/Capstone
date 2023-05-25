package sanctuaryraider.converters;

import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.ProfileModel;
import sanctuaryraider.models.RaidModel;

public class ProfileModelConverter {

/**
 * Converts between Data and API models.
 */
    /**
     * Converts a provided {@link Profile} into a {@link ProfileModel} representation.
     *
     * @param profile the Profile to convert
     * @return the converted project
     */

    public ProfileModel toProfileModel(Profile profile){
        return ProfileModel.builder().withUsername(profile.getUsername())
                .withGuild(profile.getGuild())
                .withPublicNote(profile.getPublicNote())
                .withOfficerNote(profile.getOfficerNote())
                .build();
    }


}
