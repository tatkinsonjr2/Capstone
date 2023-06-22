package sanctuaryraider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sanctuaryraider.activity.CreateProfileActivity;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.results.CreateProfileResult;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.models.ProfileModel;

import static org.mockito.Mockito.*;

class CreateProfileActivityTest {
    @Mock
    private ProfileDao profileDao;

    private CreateProfileActivity createProfileActivity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createProfileActivity = new CreateProfileActivity(profileDao);
    }

    @Test
    void testHandleRequest_ValidRequest_Success() {
        // Mock input request
        CreateProfileRequest request = CreateProfileRequest.builder()
                .withUsername("testuser")
                .withGuild("testguild")
                .withPublicNote("Public note")
                .withOfficerNote("Officer note")
                .build();

        // Invoke the handleRequest method
        CreateProfileResult result = createProfileActivity.handleRequest(request);

        // Verify that the saveProfile method was called
        verify(profileDao, times(1)).saveProfile(any(Profile.class));

        // Verify the returned result
        Assertions.assertNotNull(result);
        ProfileModel profileModel = result.getProfile();
        Assertions.assertNotNull(profileModel);
        Assertions.assertEquals("testuser", profileModel.getUsername());
        Assertions.assertEquals("testguild", profileModel.getGuild());
        // Verify other properties...
    }

//    @Test
//    void testHandleRequest_InvalidUsername_ExceptionThrown() {
//        // Mock input request with invalid username
//        CreateProfileRequest request = CreateProfileRequest.builder()
//                .withUsername("invalid@username")
//                .withGuild("testguild")
//                .withPublicNote("Public note")
//                .withOfficerNote("Officer note")
//                .build();
//
//        // Invoke the handleRequest method and assert that it throws an exception
//        Assertions.assertThrows(InvalidAttributeValueException.class, () -> {
//            createProfileActivity.handleRequest(request);
//        });
//
//        // Verify that the saveProfile method was not called
//        verify(profileDao, never()).saveProfile(any(Profile.class));
//    }
//
//    @Test
//    void testHandleRequest_InvalidGuild_ExceptionThrown() {
//        // Mock input request with invalid guild
//        CreateProfileRequest request = CreateProfileRequest.builder()
//                .withUsername("testuser")
//                .withGuild("invalid@guild")
//                .withPublicNote("Public note")
//                .withOfficerNote("Officer note")
//                .build();
//
//        // Invoke the handleRequest method and assert that it throws an exception
//        Assertions.assertThrows(InvalidAttributeValueException.class, () -> {
//            createProfileActivity.handleRequest(request);
//        });
//
//        // Verify that the saveProfile method was not called
//        verify(profileDao, never()).saveProfile(any(Profile.class));
//    }

    // Add more test cases to cover different scenarios and edge cases
}
