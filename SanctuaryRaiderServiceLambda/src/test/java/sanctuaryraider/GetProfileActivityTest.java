package sanctuaryraider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import sanctuaryraider.activity.GetProfileActivity;
import sanctuaryraider.activity.requests.GetProfileRequest;
import sanctuaryraider.activity.results.GetProfileResult;
import sanctuaryraider.converters.ProfileModelConverter;
import sanctuaryraider.dynamodb.dao.ProfileDao;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.models.ProfileModel;

import static org.mockito.ArgumentMatchers.anyString;

public class GetProfileActivityTest {

    @Mock
    private ProfileDao profileDao;

    @InjectMocks
    private GetProfileActivity getProfileActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleRequest() {
        // Mocking the ProfileDao
        Profile expectedProfile = new Profile();
        expectedProfile.setUsername("testuser");
        // Set other properties of the profile as needed
        Mockito.when(profileDao.getProfile(anyString())).thenReturn(expectedProfile);

        // Creating a request
        GetProfileRequest request = GetProfileRequest.builder()
                .withUsername("testuser")
                .build();

        // Invoking the handleRequest method
        GetProfileResult result = getProfileActivity.handleRequest(request);

        // Verifying the result
        ProfileModel expectedProfileModel = new ProfileModelConverter().toProfileModel(expectedProfile);
        Assertions.assertEquals(expectedProfileModel, result.getProfile());
    }
}
