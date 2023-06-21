//package sanctuaryraider;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import sanctuaryraider.activity.GetAllCharactersActivity;
//import sanctuaryraider.activity.requests.GetAllCharactersRequest;
//import sanctuaryraider.activity.results.GetAllCharactersResult;
//import sanctuaryraider.converters.CharacterModelConverter;
//import sanctuaryraider.dynamodb.dao.CharacterDao;
//import sanctuaryraider.dynamodb.models.Character;
//import sanctuaryraider.models.CharacterModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class GetAllCharactersActivityTest {
//    private CharacterDao characterDao;
//    private GetAllCharactersActivity getAllCharactersActivity;
//
//    @BeforeEach
//    public void setup() {
//        characterDao = mock(CharacterDao.class);
//        getAllCharactersActivity = new GetAllCharactersActivity(characterDao);
//    }
//
//    @Test
//    public void testHandleRequest() {
//        // Arrange
//        GetAllCharactersRequest request = GetAllCharactersRequest.builder()
//                .withUsername("testUser")
//                .build();
//
//        List<Character> characters = new ArrayList<>();
//        Character firstCharacter = new Character();
//        Character secondCharacter = new Character();
//        firstCharacter.setCharacterName("fatheroftwo");
//        firstCharacter.setUsername("testUser");
//        secondCharacter.setUsername("testUser");
//        secondCharacter.setCharacterName("dedicateddad");
//
//        characters.add(firstCharacter);
//        characters.add(secondCharacter);
//
//        when(characterDao.getAllCharactersForUsername(request.getUsername())).thenReturn(characters);
//
//        CharacterModelConverter characterModelConverter = new CharacterModelConverter();
//        List<CharacterModel> expectedCharacterModels = characterModelConverter.toCharacterModelList(characters);
//
//        GetAllCharactersResult expected = GetAllCharactersResult.builder()
//                .withCharacters(expectedCharacterModels)
//                .build();
//
//        // Act
//        GetAllCharactersResult result = getAllCharactersActivity.handleRequest(request);
//        System.out.println(result.toString());
//
//        // Assert
//        assertEquals(expected.getCharacters(), result.getCharacters());
//        verify(characterDao, times(1)).getAllCharactersForUsername(request.getUsername());
//    }
//}
