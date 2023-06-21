//package sanctuaryraider;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import sanctuaryraider.activity.GetCharacterActivity;
//import sanctuaryraider.activity.requests.GetCharacterRequest;
//import sanctuaryraider.activity.results.GetCharacterResult;
//import sanctuaryraider.converters.CharacterModelConverter;
//import sanctuaryraider.dynamodb.dao.CharacterDao;
//import sanctuaryraider.dynamodb.models.Character;
//import sanctuaryraider.models.CharacterModel;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//public class GetCharacterActivityTest {
//
//    @Mock
//    private CharacterDao characterDao;
//
//    @InjectMocks
//    private GetCharacterActivity getCharacterActivity;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Mocking the CharacterDao
//        Character expectedCharacter = new Character();
//        expectedCharacter.setUsername("testuser");
//        expectedCharacter.setCharacterName("testcharacter");
//        Mockito.when(characterDao.getCharacter(anyString(), anyString())).thenReturn(expectedCharacter);
//
//        // Creating a request
//        GetCharacterRequest request = GetCharacterRequest.builder()
//                .withUsername("testuser")
//                .withCharacterName("testcharacter")
//                .build();
//
//        // Invoking the handleRequest method
//        GetCharacterResult result = getCharacterActivity.handleRequest(request);
//
//        // Verifying the result
//        CharacterModel expectedCharacterModel = new CharacterModelConverter().toCharacterModel(expectedCharacter);
//        Assertions.assertEquals(expectedCharacterModel, result.getCharacter());
//    }
//}
