package samportfolio;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
public class DependencyFactory {

    private DependencyFactory() {
    }

    public static DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder().credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.US_EAST_1).httpClientBuilder(UrlConnectionHttpClient.builder()).build();
    }

}