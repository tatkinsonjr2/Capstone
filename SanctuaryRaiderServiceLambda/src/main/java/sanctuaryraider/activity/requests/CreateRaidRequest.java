package sanctuaryraider.activity.requests;

import com.amazonaws.services.dynamodbv2.xspec.L;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.sun.source.doctree.SeeTree;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@JsonDeserialize(builder = CreateRaidRequest.Builder.class)
public class CreateRaidRequest {

    private final String raidName;
    private final LocalDate date;
    private final String publicNote;
    private final String officerNote;
    private final String status;
    private final String instanceName;
    private final Set<String> attendees;

    private CreateRaidRequest(String raidName, LocalDate date, String publicNote, String officerNote, String status, String instanceName, Set<String> attendees){
        this.raidName = raidName;
        this.date = date;
        this.officerNote = officerNote;
        this.publicNote = publicNote;
        this.status = status;
        this.instanceName = instanceName;
        this.attendees = attendees;
    }

    public String getRaidName() {
        return raidName;
    }

    public LocalDate getDate() {
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

    public Set<String> getAttendees() {
        return attendees;
    }

    @Override
    public String toString() {
        return "CreateProfileRequest{" +
                "raidName='" + raidName + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", attendees='" + attendees + '\'' +
                ", publicNote='" + publicNote + '\'' +
                ", officerNote=" + officerNote +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String raidName;
        private LocalDate date;
        private String publicNote;
        private String officerNote;
        private String status;
        private String instanceName;
        private Set<String> attendees;


        public Builder withRaidName(String raidName) {
            this.raidName = raidName;
            return this;
        }

        public Builder withDate(LocalDate date) {
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

        public Builder withAttendees(Set<String> attendees) {
            this.attendees = attendees;
            return this;
        }

        public CreateRaidRequest build() {
            return new CreateRaidRequest(raidName, date, publicNote, officerNote, status, instanceName, attendees);
        }
    }
}
