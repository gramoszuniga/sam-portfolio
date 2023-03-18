package samportfolio;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.http.SdkHttpMethod;

import java.util.HashMap;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
public class Application implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public static final String PORTFOLIO = System.getenv("DYNAMODB_TABLE");
    public static final String TOTAL_VISITORS = "total_visitors";
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final Gson gson;
    private final HashMap<String, String> headers = new HashMap<>();

    public Application() {
        dynamoDbEnhancedClient = DependencyFactory.dynamoDbEnhancedClient();
        gson = DependencyFactory.gson();
        headers.put("Access-Control-Allow-Headers", "Content-Type");
        headers.put("Access-Control-Allow-Methods", "GET,OPTIONS,PUT");
        headers.put("Access-Control-Allow-Origin", "*");
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String response = "";
        DynamoDbTable<Portfolio> portfolioTable = dynamoDbEnhancedClient.table(PORTFOLIO, TableSchema
                .fromBean(Portfolio.class));
        if (input.getHttpMethod().equals(SdkHttpMethod.GET.name())) {
            Portfolio gottenPortfolio = portfolioTable.getItem(Key.builder().partitionValue(TOTAL_VISITORS).build());
            response = gson.toJson(gottenPortfolio);
        } else if (input.getHttpMethod().equals(SdkHttpMethod.PUT.name())) {
            Portfolio portfolio = new Portfolio();
            portfolio.setKey(TOTAL_VISITORS);
            Portfolio updatedPortfolio = portfolioTable.updateItem(portfolio);
            response = gson.toJson(updatedPortfolio);
        }
        return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatusCode.OK).withIsBase64Encoded(Boolean.FALSE)
                .withHeaders(headers).withBody(response);
    }

}