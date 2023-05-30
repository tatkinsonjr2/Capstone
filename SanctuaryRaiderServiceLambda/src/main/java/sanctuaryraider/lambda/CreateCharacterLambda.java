package sanctuaryraider.lambda;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.CreateCharacterRequest;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.results.CreateCharacterResult;
import sanctuaryraider.activity.results.CreateProfileResult;

public class CreateCharacterLambda
        extends LambdaActivityRunner<CreateCharacterRequest, CreateCharacterResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateCharacterRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateCharacterRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateCharacterRequest unauthenticatedRequest = input.fromBody(CreateCharacterRequest.class);
                   return input.fromUserClaims(claims ->
                            CreateCharacterRequest.builder()
                                    .withUsername(unauthenticatedRequest.getUsername())
                                    .withCharacterClass(unauthenticatedRequest.getCharacterClass())
                                    .withCharacterName(unauthenticatedRequest.getCharacterName())
                                    .withSpec(unauthenticatedRequest.getSpec())
                                    .withRace(unauthenticatedRequest.getRace())
                                    .withRole(unauthenticatedRequest.getRole())
                                    .withPublicNote(unauthenticatedRequest.getPublicNote())
                                    .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                    .withProfessionOne(unauthenticatedRequest.getProfessionOne())
                                    .withProfessionTwo(unauthenticatedRequest.getProfessionTwo())
                                    .withAlternateCharacter(unauthenticatedRequest.getAlternateCharacter())
                                    .withWishList(unauthenticatedRequest.getWishList())
                                    .build());
                },
                (request, serviceComponent) -> serviceComponent.provideCreateCharacterActivity().handleRequest(request)
        );
    }
}
