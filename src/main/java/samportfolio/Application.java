package samportfolio;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
public class Application implements RequestHandler<Object, Object> {

    public static final String PORTFOLIO = "portfolio";
    public static final String TOTAL_VISITORS = "total_visitors";
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public Application() {
        dynamoDbEnhancedClient = DependencyFactory.dynamoDbEnhancedClient();
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        DynamoDbTable<Portfolio> portfolioTable = dynamoDbEnhancedClient.table(PORTFOLIO, TableSchema
                .fromBean(Portfolio.class));
        Portfolio portfolio = new Portfolio();
        portfolio.setKey(TOTAL_VISITORS);
        Portfolio updatedPortfolio = portfolioTable.updateItem(portfolio);
        System.out.println(updatedPortfolio);
        Portfolio gottenPortfolio = portfolioTable.getItem(Key.builder().partitionValue(TOTAL_VISITORS).build());
        System.out.println(gottenPortfolio);
        return input;
    }

}