package sanctuaryraider.models;

import org.w3c.dom.stylesheets.LinkStyle;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class RaidModel {
    private final String raidName;
    private final String publicNote;
    private final ZonedDateTime date;
    private final String officerNote;
    private final String status;
    private final String instanceName;
    private final List<String> attendees;

    private RaidModel(String raidName, ZonedDateTime date, String publicNote, String officerNote, String status, String instanceName, List<String> attendees) {
        this.raidName = raidName;
        this.publicNote = publicNote;
        this.date = date;
        this.officerNote = officerNote;
        this.status = status;
        this.instanceName = instanceName;
        this.attendees = attendees;
    }

    public String getRaidName() {
        return raidName;
    }

    public String getPublicNote() {
        return publicNote;
    }

    public ZonedDateTime getDate() {
        return date;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RaidModel raidModel = (RaidModel) o;
        return Objects.equals(raidName, raidModel.raidName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raidName);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String raidName;
        private String publicNote;
        private ZonedDateTime date;
        private String officerNote;
        private String status;
        private String instanceName;
        private List<String> attendees;




        public Builder withRaidName(String raidName) {
            this.raidName = raidName;
            return this;
        }

        public Builder withPublicNote(String publicNote) {
            this.publicNote = publicNote;
            return this;
        }

        public Builder withDate(ZonedDateTime date) {
            this.date = date;
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

        public RaidModel build() {
            return new RaidModel(raidName, date, publicNote, officerNote, status, instanceName, attendees);
        }
    }
}