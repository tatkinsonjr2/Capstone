package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.DeleteCharacterRequest;
import sanctuaryraider.activity.results.DeleteCharacterResult;

    public class DeleteCharacterLambda extends LambdaActivityRunner<DeleteCharacterRequest, DeleteCharacterResult>
            implements RequestHandler<AuthenticatedLambdaRequest<DeleteCharacterRequest>, LambdaResponse> {
        @Override
        public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteCharacterRequest> input, Context context) {
            return super.runActivity(
                    () -> {
                        DeleteCharacterRequest unauthenticatedRequest = input.fromBody(DeleteCharacterRequest.class);
                        return input.fromUserClaims(claims ->
                                DeleteCharacterRequest.builder()
                                        .withCharacterName(unauthenticatedRequest.getCharacterName())
                                        .withUsername(unauthenticatedRequest.getUsername())
                                        .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                        .withPublicNote(unauthenticatedRequest.getPublicNote())
                                        .withRole(unauthenticatedRequest.getRole())
                                        .withSpec(unauthenticatedRequest.getSpec())
                                        .withCharacterClass(unauthenticatedRequest.getCharacterClass())
                                        .withRace(unauthenticatedRequest.getRace())
                                        .withAlternateCharacter(unauthenticatedRequest.getAlternateCharacter())
                                        .withProfessionOne(unauthenticatedRequest.getProfessionOne())
                                        .withProfessionTwo(unauthenticatedRequest.getProfessionTwo())
                                        .withWishList(unauthenticatedRequest.getWishList())
                                        .build());
                    },
                    (request, serviceComponent) ->
                            serviceComponent.provideDeleteCharacterActivity().handleRequest(request)
            );
        }
    }
