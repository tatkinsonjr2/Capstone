package sanctuaryraider.activity.requests;

import com.amazonaws.services.dynamodbv2.xspec.B;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

@JsonDeserialize(builder = UpdateCharacterRequest.Builder.class)
public class UpdateCharacterRequest {

    private final String username;
    private final String characterName;
    private final String characterClass;
    private final String spec;
    private final String role;
    private final String race;
    private final String publicNote;
    private final String officerNote;
    private final String professionOne;
    private final String professionTwo;
    private final Boolean alternateCharacter;
    private final Set<String> wishList;

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getSpec() {
        return spec;
    }

    public String getRole() {
        return role;
    }

    public String getRace() {
        return race;
    }

    public String getPublicNote() {
        return publicNote;
    }

    public String getOfficerNote() {
        return officerNote;
    }

    public String getProfessionOne() {
        return professionOne;
    }

    public String getProfessionTwo() {
        return professionTwo;
    }

    public Boolean getAlternateCharacter() {
        return alternateCharacter;
    }

    public Set<String> getWishList() {
        return wishList;
    }

    public UpdateCharacterRequest(String username, String characterName, String characterClass, String spec, String role, String race, String publicNote, String officerNote, String professionOne, String professionTwo, Boolean alternateCharacter, Set<String> wishList) {
        this.username = username;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.spec = spec;
        this.role = role;
        this.race = race;
        this.publicNote = publicNote;
        this.officerNote = officerNote;
        this.professionOne = professionOne;
        this.professionTwo = professionTwo;
        this.alternateCharacter = alternateCharacter;
        this.wishList = wishList;
    }

    @Override
    public String toString() {
        return "UpdateCharacterRequest{" +
                "username='" + username + '\'' +
                ", characterName='" + characterName + '\'' +
                ", publicNote='" + publicNote + '\'' +
                ", officerNote=" + officerNote + '\'' + ", characterClass=" + characterClass + '\'' +
                ", spec=" + spec +'\'' + ", role=" + role + '\'' + ", race=" + race + '\'' +
                ", professionOne=" + professionOne + '\'' + ", professionTwo=" + professionTwo +'\'' +
                ", alternateCharacter=" + alternateCharacter + '\'' + ", wishList=" + wishList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String username;
        private String characterName;
        private String characterClass;
        private String spec;
        private String role;
        private String race;
        private String publicNote;
        private String officerNote;
        private String professionOne;
        private String professionTwo;
        private Boolean alternateCharacter;
        private Set<String> wishList;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withCharacterName(String characterName) {
            this.characterName = characterName;
            return this;
        }

        public Builder withCharacterClass(String characterClass) {
            this.characterClass = characterClass;
            return this;
        }

        public Builder withSpec(String spec) {
            this.spec = spec;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public Builder withRace(String race) {
            this.race = race;
            return this;
        }

        public Builder withPublicNote(String publicNote) {
            this.publicNote = publicNote;
            return this;
        }

        public Builder withOfficerNote(String officerNote) {
            this.officerNote = officerNote;
            return this;
        }

        public Builder withProfessionOne(String professionOne) {
            this.professionOne = professionOne;
            return this;
        }

        public Builder withProfessionTwo(String professionTwo) {
            this.professionTwo = professionTwo;
            return this;
        }

        public Builder withAlternateCharacter(Boolean alternateCharacter) {
            this.alternateCharacter = alternateCharacter;
            return this;
        }

        public Builder withWishList(Set<String> wishList) {
            this.wishList = wishList;
            return this;
        }

        public UpdateCharacterRequest build() {
            return new UpdateCharacterRequest(username, characterName, characterClass, spec, role, race, publicNote, officerNote, professionOne, professionTwo, alternateCharacter, wishList);
        }
    }
}
