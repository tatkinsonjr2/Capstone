package sanctuaryraider.activity.requests;

import com.amazonaws.services.dynamodbv2.xspec.B;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dagger.Subcomponent;


public class GetCharacterRequest {
    private final String username;
    private final String characterName;

    private GetCharacterRequest(String username, String characterName){
        this.username = username;
        this.characterName = characterName;
    }

    public String getUsername(){
        return username;
    }

    public String getCharacterName(){
        return characterName;
    }

    @Override
    public String toString(){
        return "GetProfileRequest{" + "username='" + username + '\'' + ", characterName=" + characterName + '\'' + '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String username;
        private String characterName;

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public Builder withCharacterName(String characterName){
            this.characterName = characterName;
            return this;
        }
        public GetCharacterRequest build(){
            return new GetCharacterRequest(username, characterName);
        }
    }
}
