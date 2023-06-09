package sanctuaryraider.activity.requests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateProfileRequest.Builder.class)
public class CreateProfileRequest {
    private final String username;
    private final String guild;
    private final String publicNote;
    private final String officerNote;

    private CreateProfileRequest(String username, String guild, String publicNote, String officerNote){
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
    public String toString() {
        return "CreateProfileRequest{" +
                "username='" + username + '\'' +
                ", guild='" + guild + '\'' +
                ", publicNote='" + publicNote + '\'' +
                ", officerNote=" + officerNote +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String username;
        private String guild;
        private String publicNote;
        private String officerNote;

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

        public CreateProfileRequest build() {
            return new CreateProfileRequest(username, guild, publicNote, officerNote);
        }
    }
}
