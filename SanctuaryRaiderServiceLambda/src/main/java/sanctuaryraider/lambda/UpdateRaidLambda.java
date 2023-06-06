package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.UpdateCharacterRequest;
import sanctuaryraider.activity.requests.UpdateRaidRequest;
import sanctuaryraider.activity.results.UpdateCharacterResult;
import sanctuaryraider.activity.results.UpdateRaidResult;

public class UpdateRaidLambda extends LambdaActivityRunner<UpdateRaidRequest, UpdateRaidResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateRaidRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateRaidRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateRaidRequest unauthenticatedRequest = input.fromBody(UpdateRaidRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateRaidRequest.builder()
                                    .withRaidName(unauthenticatedRequest.getRaidName())
                                    .withInstanceName(unauthenticatedRequest.getInstanceName())
                                    .withAttendees(unauthenticatedRequest.getAttendees())
                                    .withDate(unauthenticatedRequest.getDate())
                                    .withStatus(unauthenticatedRequest.getStatus())
                                    .withPublicNote(unauthenticatedRequest.getPublicNote())
                                    .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateRaidActivity().handleRequest(request)
        );
    }
}