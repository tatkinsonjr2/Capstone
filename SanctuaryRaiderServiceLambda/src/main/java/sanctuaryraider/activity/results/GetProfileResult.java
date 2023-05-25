package sanctuaryraider.activity.results;

import org.apache.commons.lang3.builder.Builder;
import sanctuaryraider.models.ProfileModel;

public class GetProfileResult {
    private final ProfileModel profile;

    private GetProfileResult(ProfileModel profile){
        this.profile = profile;
    }

    public ProfileModel getProfile(){
        return profile;
    }

    @Override
    public String toString(){
        return "GetProfileResult{" + "profile=" + profile + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private ProfileModel profile;

        public Builder withProfile(ProfileModel profile){
            this.profile = profile;
            return this;
        }

        public GetProfileResult build(){
            return new GetProfileResult(profile);
        }
    }
}
