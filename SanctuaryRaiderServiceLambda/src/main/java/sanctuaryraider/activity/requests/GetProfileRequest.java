package sanctuaryraider.activity.requests;

import org.apache.commons.lang3.builder.Builder;
import sanctuaryraider.models.ProfileModel;

public class GetProfileRequest {
    private final String username;

    private GetProfileRequest(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString(){
        return "GetProfileRequest{" + "username=" + username + '\'' + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String username;

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public GetProfileRequest build(){
            return new GetProfileRequest(username);
        }
    }
}
