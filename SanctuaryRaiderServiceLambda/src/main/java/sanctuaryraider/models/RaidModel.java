package sanctuaryraider.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.w3c.dom.stylesheets.LinkStyle;
import sanctuaryraider.converters.LocalDateSerializer;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RaidModel {
    private final String raidName;
    private final String publicNote;
    private final LocalDate date;
    private final String officerNote;
    private final String status;
    private final String instanceName;
    private final Set<String> attendees;

    private RaidModel(String raidName, LocalDate date, String publicNote, String officerNote, String status, String instanceName, Set<String> attendees) {
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

//    public LocalDate getDate() {
//        return date;
//    }

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
        return Objects.hash(raidName,publicNote,date,officerNote,status,instanceName,attendees);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String raidName;
        private String publicNote;
        private LocalDate date;
        private String officerNote;
        private String status;
        private String instanceName;
        private Set<String> attendees;


        public Builder withRaidName(String raidName) {
            this.raidName = raidName;
            return this;
        }

        public Builder withPublicNote(String publicNote) {
            this.publicNote = publicNote;
            return this;
        }

        public Builder withDate(LocalDate date) {
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

        public Builder withAttendees(Set<String> attendees) {
            this.attendees = attendees;
            return this;
        }

        public RaidModel build() {
            return new RaidModel(raidName, date, publicNote, officerNote, status, instanceName, attendees);
        }
    }
}
