//package sanctuaryraider.lambda;
//public class GetAllCharactersLambda
//        extends LambdaActivityRunner<GetAllTicketsRequest, GetAllTicketsResult>
//        implements RequestHandler<LambdaRequest<GetAllTicketsRequest>, LambdaResponse> {
//
//    private final Logger log = LogManager.getLogger();
//
//    @Override
//    public LambdaResponse handleRequest(LambdaRequest<GetAllTicketsRequest> input, Context context) {
//        log.info("handleRequest");
//        return super.runActivity(
//                () -> input.fromPath(path ->
//                        GetAllTicketsRequest.builder()
//                                .withId(path.get("projectId"))
//                                .build()),
//                (request, serviceComponent) ->
//                        serviceComponent.provideGetAllTicketsActivity().handleRequest(request)
//        );
//    }
//
