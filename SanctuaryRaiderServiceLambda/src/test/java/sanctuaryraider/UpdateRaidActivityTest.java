//package sanctuaryraider;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import sanctuaryraider.activity.UpdateRaidActivity;
//import sanctuaryraider.activity.requests.UpdateRaidRequest;
//import sanctuaryraider.activity.results.UpdateRaidResult;
//import sanctuaryraider.converters.RaidModelConverter;
//import sanctuaryraider.dynamodb.dao.RaidDao;
//import sanctuaryraider.dynamodb.models.Raid;
//import sanctuaryraider.models.RaidModel;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//public class UpdateRaidActivityTest {
//
//    @Mock
//    private RaidDao raidDao;
//
//    @InjectMocks
//    private UpdateRaidActivity updateRaidActivity;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Mocking the RaidDao
//        Raid raid = new Raid();
//        raid.setRaidName("Test Raid");
//        raid.setDate(LocalDate.now());
//        // Set other properties of the raid as needed
//        Mockito.when(raidDao.getRaid(anyString())).thenReturn(raid);
//        Mockito.when(raidDao.saveRaid(Mockito.any(Raid.class))).thenReturn(raid);
//
//        // Creating a request
//        Set<String> attendees = new HashSet<>();
//        attendees.add("Player1");
//        attendees.add("Player2");
//        attendees.add("Player3");
//        UpdateRaidRequest request = UpdateRaidRequest.builder()
//                .withRaidName("Test Raid")
//                .withDate(LocalDate.now())
//                .withPublicNote("Public Note")
//                .withOfficerNote("Officer Note")
//                .withStatus("In Progress")
//                .withInstanceName("Instance")
//                .withAttendees(attendees)
//                .build();
//
//        // Invoking the handleRequest method
//        UpdateRaidResult result = updateRaidActivity.handleRequest(request);
//
//        // Verifying the result
//        RaidModel expectedRaidModel = new RaidModelConverter().toRaidModel(raid);
//        Assertions.assertEquals(expectedRaidModel, result.getRaid());
//    }
//}
