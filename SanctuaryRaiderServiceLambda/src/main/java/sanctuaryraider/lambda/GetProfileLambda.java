package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetProfileRequest;
import sanctuaryraider.activity.results.GetProfileResult;

public class GetProfileLambda extends LambdaActivityRunner<GetProfileRequest, GetProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetProfileRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetProfileRequest> input, Context context){
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path -> GetProfileRequest.builder().withUsername(path.get("username")).build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetProfileActivity().handleRequest(request)

        );
    }
}