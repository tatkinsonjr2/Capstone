package sanctuaryraider.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.CreateProfileRequest;
import sanctuaryraider.activity.results.CreateProfileResult;

public class CreateProfileLambda
        extends LambdaActivityRunner<CreateProfileRequest, CreateProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateProfileRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateProfileRequest unauthenticatedRequest = input.fromBody(CreateProfileRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateProfileRequest.builder()
                                    .withUsername(unauthenticatedRequest.getUsername())
                                    .withGuild(unauthenticatedRequest.getGuild())
                                    .withPublicNote(unauthenticatedRequest.getPublicNote())
                                    .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateProfileActivity().handleRequest(request)
        );
    }
}
