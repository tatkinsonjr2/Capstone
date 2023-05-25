package sanctuaryraider.activity.results;

import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.models.ProfileModel;

public class CreateProfileResult {
    private final ProfileModel profile;

    private CreateProfileResult(ProfileModel profile){
        this.profile = profile;
    }

    public ProfileModel getProfile(){
        return profile;
    }

    @Override
    public String toString() {
        return "CreateProfileResult{" +
                "profile=" + profile +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProfileModel profile;

        public Builder withProfile(ProfileModel profile) {
            this.profile = profile;
            return this;
        }

        public CreateProfileResult build() {
            return new CreateProfileResult(profile);
        }
    }
}
