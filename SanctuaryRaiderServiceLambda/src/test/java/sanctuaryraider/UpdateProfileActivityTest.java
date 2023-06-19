package sanctuaryraider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sanctuaryraider.activity.UpdateProfileActivity;
import sanctuaryraider.activity.requests.UpdateProfileRequest;
import sanctuaryraider.activity.results.UpdateProfileResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.models.ProfileModel;

import static org.mockito.ArgumentMatchers.anyString;

public class UpdateProfileActivityTest {

    @Mock
    private ProfileDao profileDao;

    @InjectMocks
    private UpdateProfileActivity updateProfileActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleRequest() {
        // Mocking the ProfileDao
        Profile profile = new Profile();
        profile.setUsername("testuser");
        profile.setGuild("testguild");
        // Set other properties of the profile as needed
        Mockito.when(profileDao.getProfile(anyString())).thenReturn(profile);
        Mockito.when(profileDao.saveProfile(Mockito.any(Profile.class))).thenReturn(profile);

        // Creating a request
        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .withUsername("testuser")
                .withGuild("newguild")
                .withPublicNote("Public Note")
                .build();

        // Invoking the handleRequest method
        UpdateProfileResult result = updateProfileActivity.handleRequest(request);

        // Verifying the result
        ProfileModel expectedProfileModel = new ProfileModelConverter().toProfileModel(profile);
        Assertions.assertEquals(expectedProfileModel, result.getProfile());
    }
}
