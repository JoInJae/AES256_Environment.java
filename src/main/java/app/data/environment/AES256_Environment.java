package app.data.environment;

import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "aes256")
@ConstructorBinding
@Getter
public class AES256_Environment {

    private final String iv;
    private final String private_key;

    public AES256_Environment(String iv, String private_key) {
        this.iv = iv;
        this.private_key = private_key;
    }

}
