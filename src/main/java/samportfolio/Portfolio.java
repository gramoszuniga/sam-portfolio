package samportfolio;

import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbAtomicCounter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Objects;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-31
 **/
@DynamoDbBean
public class Portfolio {

    private String key;
    private Long value;

    @DynamoDbPartitionKey
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @DynamoDbAtomicCounter
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(getKey(), portfolio.getKey()) && Objects.equals(getValue(), portfolio.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}