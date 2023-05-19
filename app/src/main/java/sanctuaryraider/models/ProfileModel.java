package sanctuaryraider.models;

import java.util.Objects;

public class ProfileModel {
    private final String username;
    private final String guild;
    private final String publicNote;
    private final String officerNote;

    public ProfileModel(String username, String guild, String publicNote, String officerNote){
        this.username = username;
        this.guild = guild;
        this.publicNote = publicNote;
        this.officerNote = officerNote;
    }

    public String getUsername() {
        return username;
    }

    public String getGuild() {
        return guild;
    }

    public String getPublicNote() {
        return publicNote;
    }

    public String getOfficerNote() {
        return officerNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfileModel that = (ProfileModel) o;
        return Objects.equals(username, that.username) && Objects.equals(guild, that.guild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, guild);
    }


    public static final class Builder {
        private String username;
        private String guild;
        private String publicNote;
        private String officerNote;



        public static Builder builder() {
            return new Builder();
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withGuild(String guild) {
            this.guild = guild;
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

        public ProfileModel build() {
            return new ProfileModel(username, guild, publicNote, officerNote);
        }
    }
}
