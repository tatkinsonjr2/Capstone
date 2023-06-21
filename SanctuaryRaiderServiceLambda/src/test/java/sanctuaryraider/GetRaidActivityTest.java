//package sanctuaryraider;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import sanctuaryraider.activity.GetRaidActivity;
//import sanctuaryraider.activity.requests.GetRaidRequest;
//import sanctuaryraider.activity.results.GetRaidResult;
//import sanctuaryraider.converters.RaidModelConverter;
//import sanctuaryraider.dynamodb.dao.RaidDao;
//import sanctuaryraider.dynamodb.models.Raid;
//import sanctuaryraider.models.RaidModel;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//public class GetRaidActivityTest {
//
//    @Mock
//    private RaidDao raidDao;
//
//    @InjectMocks
//    private GetRaidActivity getRaidActivity;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Mocking the RaidDao
//        Raid expectedRaid = new Raid();
//        expectedRaid.setRaidName("testraid");
//        // Set other properties of the raid as needed
//        Mockito.when(raidDao.getRaid(anyString())).thenReturn(expectedRaid);
//
//        // Creating a request
//        GetRaidRequest request = GetRaidRequest.builder()
//                .withRaidName("testraid")
//                .build();
//
//        // Invoking the handleRequest method
//        GetRaidResult result = getRaidActivity.handleRequest(request);
//
//        // Verifying the result
//        RaidModel expectedRaidModel = new RaidModelConverter().toRaidModel(expectedRaid);
//        Assertions.assertEquals(expectedRaidModel, result.getRaid());
//    }
//}
