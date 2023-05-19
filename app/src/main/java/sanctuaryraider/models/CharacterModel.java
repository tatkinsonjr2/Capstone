package sanctuaryraider.models;

import java.util.List;

public class CharacterModel {
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
    private final boolean alternateCharacter;
    private final boolean archive;
    private final List<String> wishList;

    public CharacterModel(String username, String characterName, String characterClass, String spec, String role, String race, String publicNote, String officerNote, String professionOne, String professionTwo, boolean alternateCharacter, boolean archive, List<String> wishList) {
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
        this.archive = archive;
        this.wishList = wishList;
    }


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

    public boolean isAlternateCharacter() {
        return alternateCharacter;
    }

    public boolean isArchive() {
        return archive;
    }

    public List<String> getWishList() {
        return wishList;
    }


    public static final class Builder {
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
        private boolean alternateCharacter;
        private boolean archive;
        private List<String> wishList;


        public static Builder builder() {
            return new Builder();
        }

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

        public Builder withAlternateCharacter(boolean alternateCharacter) {
            this.alternateCharacter = alternateCharacter;
            return this;
        }

        public Builder withArchive(boolean archive) {
            this.archive = archive;
            return this;
        }

        public Builder withWishList(List<String> wishList) {
            this.wishList = wishList;
            return this;
        }

        public CharacterModel build() {
            return new CharacterModel(username, characterName, characterClass, spec, role, race, publicNote, officerNote, professionOne, professionTwo, alternateCharacter, archive, wishList);
        }
    }
}
