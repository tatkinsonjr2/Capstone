//package sanctuaryraider;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import sanctuaryraider.activity.UpdateCharacterActivity;
//import sanctuaryraider.activity.requests.UpdateCharacterRequest;
//import sanctuaryraider.activity.results.UpdateCharacterResult;
//import sanctuaryraider.converters.CharacterModelConverter;
//import sanctuaryraider.dynamodb.dao.CharacterDao;
//import sanctuaryraider.dynamodb.models.Character;
//import sanctuaryraider.models.CharacterModel;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//public class UpdateCharacterActivityTest {
//
//    @Mock
//    private CharacterDao characterDao;
//
//    @InjectMocks
//    private UpdateCharacterActivity updateCharacterActivity;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Mocking the CharacterDao
//        Character character = new Character();
//        character.setUsername("testuser");
//        character.setCharacterName("testcharacter");
//        // Set other properties of the character as needed
//        Mockito.when(characterDao.getCharacter(anyString(), anyString())).thenReturn(character);
//        Mockito.when(characterDao.saveCharacter(Mockito.any(Character.class))).thenReturn(character);
//
//        // Creating a request
//        UpdateCharacterRequest request = UpdateCharacterRequest.builder()
//                .withUsername("testuser")
//                .withCharacterName("testcharacter")
//                .withCharacterClass("Warrior")
//                .withSpec("Protection")
//                .withRace("Human")
//                .build();
//
//        // Invoking the handleRequest method
//        UpdateCharacterResult result = updateCharacterActivity.handleRequest(request);
//
//        // Verifying the result
//        CharacterModel expectedCharacterModel = new CharacterModelConverter().toCharacterModel(character);
//        Assertions.assertEquals(expectedCharacterModel, result.getCharacter());
//    }
//}
