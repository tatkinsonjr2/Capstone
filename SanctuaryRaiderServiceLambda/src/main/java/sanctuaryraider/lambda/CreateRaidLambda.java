package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import sanctuaryraider.activity.requests.CreateCharacterRequest;
import sanctuaryraider.activity.requests.CreateRaidRequest;
import sanctuaryraider.activity.results.CreateCharacterResult;
import sanctuaryraider.activity.results.CreateRaidResult;

public class CreateRaidLambda
        extends LambdaActivityRunner<CreateRaidRequest, CreateRaidResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateRaidRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateRaidRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateRaidRequest unauthenticatedRequest = input.fromBody(CreateRaidRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateRaidRequest.builder()
                                    .withRaidName(unauthenticatedRequest.getRaidName())
                                    .withDate(unauthenticatedRequest.getDate())
                                    .withPublicNote(unauthenticatedRequest.getPublicNote())
                                    .withOfficerNote(unauthenticatedRequest.getOfficerNote())
                                    .withStatus(unauthenticatedRequest.getStatus())
                                    .withInstanceName(unauthenticatedRequest.getInstanceName())
                                    .withAttendees(unauthenticatedRequest.getAttendees())
                                    .build());
                },
                (request, serviceComponent) -> serviceComponent.provideCreateRaidActivity().handleRequest(request)
        );
    }
}
