package sanctuaryraider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sanctuaryraider.activity.CreateCharacterActivity;
import sanctuaryraider.activity.requests.CreateCharacterRequest;
import sanctuaryraider.activity.results.CreateCharacterResult;
import sanctuaryraider.dynamodb.dao.CharacterDao;
import sanctuaryraider.dynamodb.models.Character;
import sanctuaryraider.models.CharacterModel;

import java.util.Collections;

import static org.mockito.Mockito.*;

class CreateCharacterActivityTest {
    @Mock
    private CharacterDao characterDao;

    private CreateCharacterActivity createCharacterActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createCharacterActivity = new CreateCharacterActivity(characterDao);
    }

    @Test
    void testHandleRequest_ValidRequest_Success() {
        // Mock input request
        CreateCharacterRequest request = CreateCharacterRequest.builder()
                .withUsername("testuser")
                .withCharacterName("testcharacter")
                .withCharacterClass("Warrior")
                .withSpec("Fury")
                .withRole("DPS")
                .withRace("Orc")
                .withPublicNote("Public note")
                .withOfficerNote("Officer note")
                .withProfessionOne("Mining")
                .withProfessionTwo("Blacksmithing")
                .withAlternateCharacter(false)
                .withWishList(Collections.emptySet())
                .build();

        // Invoke the handleRequest method
        CreateCharacterResult result = createCharacterActivity.handleRequest(request);

        // Verify that the saveCharacter method was called
        verify(characterDao, times(1)).saveCharacter(any(Character.class));

        // Verify the returned result
        Assertions.assertNotNull(result);
        CharacterModel characterModel = result.getCharacter();
        Assertions.assertNotNull(characterModel);
        Assertions.assertEquals("testuser", characterModel.getUsername());
        Assertions.assertEquals("testcharacter", characterModel.getCharacterName());

    }
}
//
//    @Test
//    void testHandleRequest_InvalidUsername_ExceptionThrown() {
//        // Mock input request with invalid username
//        CreateCharacterRequest request = CreateCharacterRequest.builder()
//                .withUsername("invalid@username")
//                .withCharacterName("testcharacter")
//                .withCharacterClass("Warrior")
//                .withSpec("Fury")
//                .withRole("DPS")
//                .withRace("Orc")
//                .withPublicNote("Public note")
//                .withOfficerNote("Officer note")
//                .withProfessionOne("Mining")
//                .withProfessionTwo("Blacksmithing")
//                .withAlternateCharacter(false)
//                .withWishList(Collections.emptySet())
//                .build();
//
//        // Invoke the handleRequest method and assert that it throws an exception
//        Assertions.assertThrows(InvalidAttributeValueException.class, () -> {
//            createCharacterActivity.handleRequest(request);
//        });
//
//        // Verify that the saveCharacter method was not called
//        verify(characterDao, never()).saveCharacter(any(Character.class));
//    }
//
//    @Test
//    void testHandleRequest_InvalidCharacterName_ExceptionThrown() {
//        // Mock input request with invalid character name
//        CreateCharacterRequest request = CreateCharacterRequest.builder()
//                .withUsername("testuser")
//                .withCharacterName("invalid@character")
//                .withCharacterClass("Warrior")
//                .withSpec("Fury")
//                .withRole("DPS")
//                .withRace("Orc")
//                .withPublicNote("Public note")
//                .withOfficerNote("Officer note")
//                .withProfessionOne("Mining")
//                .withProfessionTwo("Blacksmithing")
//                .withAlternateCharacter(false)
//                .withWishList(Collections.emptySet())
//                .build();
//
//        // Invoke the handleRequest method and assert that it throws an exception
//        Assertions.assertThrows(InvalidAttributeValueException.class, () -> {
//            createCharacterActivity.handleRequest(request);
//        });
//
//        // Verify that the saveCharacter method was not called
//        verify(characterDao, never()).saveCharacter(any(Character.class));
//    }
//
//    // Add more test cases to cover different scenarios and edge cases
//}
