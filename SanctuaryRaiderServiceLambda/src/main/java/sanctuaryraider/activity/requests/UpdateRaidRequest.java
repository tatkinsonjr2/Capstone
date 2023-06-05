package sanctuaryraider.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.ZonedDateTime;
import java.util.List;

@JsonDeserialize(builder = UpdateRaidRequest.Builder.class)
public class UpdateRaidRequest {

    private final String raidName;
    private final ZonedDateTime date;
    private final String publicNote;
    private final String officerNote;
    private final String status;
    private final String instanceName;
    private final List<String> attendees;

    public String getRaidName() {
        return raidName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getPublicNote() {
        return publicNote;
    }

    public String getOfficerNote() {
        return officerNote;
    }

    public String getStatus() {
        return status;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public UpdateRaidRequest(String raidName, ZonedDateTime date, String publicNote, String officerNote, String status, String instanceName, List<String> attendees){
        this.raidName = raidName;
        this.officerNote = officerNote;
        this.date = date;
        this.publicNote = publicNote;
        this.instanceName = instanceName;
        this.attendees = attendees;
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateCharacterRequest{" +
                "raidName='" + raidName + '\'' +
                ", date='" + date + '\'' +
                ", publicNote='" + publicNote + '\'' +
                ", officerNote=" + officerNote + '\'' + ", instanceName=" + instanceName + '\'' +
                ", attendees=" + attendees +'\'' + ", status=" + status + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String raidName;
        private ZonedDateTime date;
        private String publicNote;
        private String officerNote;
        private String status;
        private String instanceName;
        private List<String> attendees;



        public Builder withRaidName(String raidName) {
            this.raidName = raidName;
            return this;
        }

        public Builder withDate(ZonedDateTime date) {
            this.date = date;
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

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withInstanceName(String instanceName) {
            this.instanceName = instanceName;
            return this;
        }

        public Builder withAttendees(List<String> attendees) {
            this.attendees = attendees;
            return this;
        }

        public UpdateRaidRequest build() {
            return new UpdateRaidRequest(raidName, date, publicNote, officerNote, status, instanceName, attendees);
        }
    }
}
