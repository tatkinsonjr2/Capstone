package sanctuaryraider.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.UpdateProfileRequest;
import sanctuaryraider.activity.results.UpdateProfileResult;

public class UpdateProfileLambda extends LambdaActivityRunner<UpdateProfileRequest, UpdateProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateProfileRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateProfileRequest unauthenticatedRequest = input.fromBody(UpdateProfileRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateProfileRequest.builder()
                                    .withUsername(unauthenticatedRequest.getUsername())
                                    .withGuild(unauthenticatedRequest.getGuild())
                                    .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                    .withPublicNote(unauthenticatedRequest.getPublicNote())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateProfileActivity().handleRequest(request)
        );
    }


}