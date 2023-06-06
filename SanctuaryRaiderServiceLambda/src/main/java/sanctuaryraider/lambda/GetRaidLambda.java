package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetRaidRequest;
import sanctuaryraider.activity.results.GetRaidResult;

public class GetRaidLambda extends LambdaActivityRunner<GetRaidRequest, GetRaidResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRaidRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRaidRequest> input, Context context){
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path -> GetRaidRequest.builder().withRaidName(path.get("raidName")).build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetRaidActivity().handleRequest(request)

        );
    }
}