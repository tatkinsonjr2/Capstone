package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.UpdateCharacterRequest;
import sanctuaryraider.activity.results.UpdateCharacterResult;

public class UpdateCharacterLambda extends LambdaActivityRunner<UpdateCharacterRequest, UpdateCharacterResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateCharacterRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateCharacterRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateCharacterRequest unauthenticatedRequest = input.fromBody(UpdateCharacterRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateCharacterRequest.builder()
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
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateCharacterActivity().handleRequest(request)
        );
    }
}