package sanctuaryraider.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sanctuaryraider.activity.requests.GetAllRaidsByCharacterNameRequest;
import sanctuaryraider.activity.results.GetAllRaidsByCharacterNameResult;

public class GetAllRaidsByCharacterNameLambda
        extends LambdaActivityRunner<GetAllRaidsByCharacterNameRequest, GetAllRaidsByCharacterNameResult>
        implements RequestHandler<LambdaRequest<GetAllRaidsByCharacterNameRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllRaidsByCharacterNameRequest> input, Context context) {
        log.info("handleRequest");
       return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllRaidsByCharacterNameRequest.builder()
                                .withCharacterName(path.get("characterName"))
                                .build()),
                (request, serviceComponent) ->
                    serviceComponent.provideGetAllRaidsByCharacterNameActivity().handleRequest(request)


        );
      }
    }

