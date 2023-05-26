package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetCharacterRequest;
import sanctuaryraider.activity.requests.GetProfileRequest;
import sanctuaryraider.activity.results.GetCharacterResult;
import sanctuaryraider.activity.results.GetProfileResult;

public class GetCharacterLambda extends LambdaActivityRunner<GetCharacterRequest, GetCharacterResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetCharacterRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetCharacterRequest> input, Context context){
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path -> GetCharacterRequest.builder().withUsername(path.get("username")).withCharacterName(path.get("characterName")).build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetCharacterActivity().handleRequest(request)

        );
    }
}
