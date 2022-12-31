package samportfolio;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
public class Application implements RequestHandler<Object, Object> {

    private final DynamoDbClient dynamoDbClient;

    public Application() {
        dynamoDbClient = DependencyFactory.dynamoDbClient();
        dynamoDbClient.listTables();
    }

    @Override
    public Object handleRequest(final Object input, final Context context) {
        return input;
    }

}