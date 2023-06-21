//package sanctuaryraider;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import sanctuaryraider.activity.GetAllRaidsByCharacterNameActivity;
//import sanctuaryraider.activity.requests.GetAllRaidsByCharacterNameRequest;
//import sanctuaryraider.activity.results.GetAllRaidsByCharacterNameResult;
//import sanctuaryraider.converters.RaidModelConverter;
//import sanctuaryraider.dynamodb.dao.RaidDao;
//import sanctuaryraider.dynamodb.models.Raid;
//import sanctuaryraider.models.RaidModel;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class GetAllRaidsByCharacterNameActivityTest {
//    private RaidDao raidDao;
//    private GetAllRaidsByCharacterNameActivity getAllRaidsByCharacterNameActivity;
//
//    @BeforeEach
//    public void setup() {
//        raidDao = mock(RaidDao.class);
//        getAllRaidsByCharacterNameActivity = new GetAllRaidsByCharacterNameActivity(raidDao);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Arrange
//        GetAllRaidsByCharacterNameRequest request = GetAllRaidsByCharacterNameRequest.builder()
//                .withCharacterName("fatheroftwo")
//                .build();
//
//        List<Raid> raids = new ArrayList<>();
//        Set<String> attendees = Set.of("fatheroftwo", "PadreJoe");
//        Set<String> attendees2 = Set.of("fatheroftwo", "dedicatedad");
//        Raid raid1 = new Raid();
//        Raid raid2 = new Raid();
//        raid1.setRaidName("Test1");
//        raid1.setAttendees(attendees);
//        raid2.setAttendees(attendees2);
//        raid2.setRaidName("Test2");
//
//        raids.add(raid1);
//        raids.add(raid2);
//
//        when(raidDao.getAllRaidsByCharacterName(request.getCharacterName())).thenReturn(raids);
//
//        RaidModelConverter raidModelConverter = new RaidModelConverter();
//        List<RaidModel> expectedRaidModels = raidModelConverter.toRaidModelList(raids);
//
//        GetAllRaidsByCharacterNameResult expected = GetAllRaidsByCharacterNameResult.builder()
//                .withRaids(expectedRaidModels)
//                .build();
//
//        // Act
//        GetAllRaidsByCharacterNameResult result = getAllRaidsByCharacterNameActivity.handleRequest(request);
//
//        // Assert
//        assertEquals(expected.getRaids(), result.getRaids());
//        verify(raidDao, times(1)).getAllRaidsByCharacterName(request.getCharacterName());
//    }
//}
