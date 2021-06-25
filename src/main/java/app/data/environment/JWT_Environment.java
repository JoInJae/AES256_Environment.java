package app.data.environment;

import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
@Getter
public class JWT_Environment {

    private final String private_key;

    public JWT_Environment(String private_key) {
        this.private_key = private_key;
    }

}
