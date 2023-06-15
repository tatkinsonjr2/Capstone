package sanctuaryraider.activity.requests;

public class GetAllRaidsByCharacterNameRequest {
    private final String characterName;

    private GetAllRaidsByCharacterNameRequest(String characterName){
        this.characterName = characterName;
    }

    public String getCharacterName(){
        return characterName;
    }

    @Override
    public String toString() {
        return "GetAllRaidsByCharacterNameRequest{" +
                "characterName='" + characterName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String characterName;


        public Builder withCharacterName(String characterName) {
            this.characterName = characterName;
            return this;
        }

        public GetAllRaidsByCharacterNameRequest build() {
            return new GetAllRaidsByCharacterNameRequest(characterName);
        }
    }
}
