package samportfolio;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
@ExtendWith(MockitoExtension.class)
public class ApplicationTest {

    public static final String PORTFOLIO = "portfolio";
    public static final String TOTAL_VISITORS = "total_visitors";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    @Mock
    private APIGatewayProxyRequestEvent input;
    @Mock
    private Context context;
    @Mock
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    @Mock
    private DynamoDbTable<Portfolio> portfolioTable;
    @Mock
    private Gson gson;

    @Test
    void handleGetRequest() {
        String expected = "{\"total_visitors\":0}";
        when(dynamoDbEnhancedClient.table(eq(PORTFOLIO), any(TableSchema.class))).thenReturn(portfolioTable);
        when(input.getHttpMethod()).thenReturn(GET);
        when(portfolioTable.getItem(any(Key.class))).thenReturn(new Portfolio());
        when(gson.toJson(any(Portfolio.class))).thenReturn(expected);
        try (MockedStatic<DependencyFactory> dependencyFactory = mockStatic(DependencyFactory.class)) {
            dependencyFactory.when(DependencyFactory::dynamoDbEnhancedClient).thenReturn(dynamoDbEnhancedClient);
            dependencyFactory.when(DependencyFactory::gson).thenReturn(gson);
            APIGatewayProxyResponseEvent actual = new Application().handleRequest(input, context);
            assertEquals(HttpStatusCode.OK, actual.getStatusCode());
            assertTrue(actual.getBody().contains(TOTAL_VISITORS));
        }
    }

    @Test
    void handlePutRequest() {
        String expected = "{\"total_visitors\":1}";
        when(dynamoDbEnhancedClient.table(eq(PORTFOLIO), any(TableSchema.class))).thenReturn(portfolioTable);
        when(input.getHttpMethod()).thenReturn(PUT);
        when(portfolioTable.updateItem(any(Portfolio.class))).thenReturn(new Portfolio());
        when(gson.toJson(any(Portfolio.class))).thenReturn(expected);
        try (MockedStatic<DependencyFactory> dependencyFactory = mockStatic(DependencyFactory.class)) {
            dependencyFactory.when(DependencyFactory::dynamoDbEnhancedClient).thenReturn(dynamoDbEnhancedClient);
            dependencyFactory.when(DependencyFactory::gson).thenReturn(gson);
            APIGatewayProxyResponseEvent actual = new Application().handleRequest(input, context);
            assertEquals(HttpStatusCode.OK, actual.getStatusCode());
            assertTrue(actual.getBody().contains(TOTAL_VISITORS));
        }
    }

}