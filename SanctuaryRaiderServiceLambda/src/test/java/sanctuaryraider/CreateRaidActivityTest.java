package sanctuaryraider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sanctuaryraider.activity.CreateRaidActivity;
import sanctuaryraider.activity.requests.CreateRaidRequest;
import sanctuaryraider.activity.results.CreateRaidResult;
import sanctuaryraider.dynamodb.dao.RaidDao;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.models.RaidModel;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class CreateRaidActivityTest {
    private CreateRaidActivity createRaidActivity;
    private RaidDao raidDao;

    @BeforeEach
    public void setUp() {
        raidDao = mock(RaidDao.class);
        createRaidActivity = new CreateRaidActivity(raidDao);
    }

    @Test
    public void testHandleRequest() {
        // Mock data
        String raidName = "Raid 1";
        LocalDate date = LocalDate.now();
        String publicNote = "Public note";
        String officerNote = "Officer note";
        String status = "Scheduled";
        String instanceName = "Instance 1";
        Set<String> attendees = new HashSet<>();
        attendees.add("Player 1");
        attendees.add("Player 2");

        // CreateRaidRequest
        CreateRaidRequest createRaidRequest = CreateRaidRequest.builder()
                .withRaidName(raidName)
                .withDate(date)
                .withPublicNote(publicNote)
                .withOfficerNote(officerNote)
                .withStatus(status)
                .withInstanceName(instanceName)
                .withAttendees(attendees)
                .build();

        // CreateRaidResult
        Raid raid = new Raid();
        raid.setRaidName(raidName);
        raid.setDate(date);
        raid.setPublicNote(publicNote);
        raid.setOfficerNote(officerNote);
        raid.setStatus(status);
        raid.setInstanceName(instanceName);
        raid.setAttendees(attendees);

        when(raidDao.saveRaid(any(Raid.class))).thenReturn(raid);

        // Perform the activity
        CreateRaidResult result = createRaidActivity.handleRequest(createRaidRequest);

        // Verify the result
        Assertions.assertNotNull(result);
        RaidModel raidModel = result.getRaid();
        Assertions.assertEquals(raidName, raidModel.getRaidName());
        Assertions.assertEquals(date, raidModel.getDate());
        Assertions.assertEquals(publicNote, raidModel.getPublicNote());
        Assertions.assertEquals(officerNote, raidModel.getOfficerNote());
        Assertions.assertEquals(status, raidModel.getStatus());
        Assertions.assertEquals(instanceName, raidModel.getInstanceName());
        Assertions.assertEquals(attendees, raidModel.getAttendees());

        // Verify that saveRaid was called with the correct argument
        verify(raidDao, times(1)).saveRaid(any(Raid.class));
    }
}
