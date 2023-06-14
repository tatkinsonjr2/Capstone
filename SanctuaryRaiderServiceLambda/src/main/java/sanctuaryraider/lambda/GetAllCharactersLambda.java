package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetAllCharactersRequest;
import sanctuaryraider.activity.results.GetAllCharactersResult;

public class GetAllCharactersLambda
        extends LambdaActivityRunner<GetAllCharactersRequest, GetAllCharactersResult>
        implements RequestHandler<LambdaRequest<GetAllCharactersRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllCharactersRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllCharactersRequest.builder()
                                .withUsername(path.get("username"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllCharactersActivity().handleRequest(request)
        );
    }
}

