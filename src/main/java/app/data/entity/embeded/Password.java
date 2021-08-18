package app.data.entity.embeded;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
@Getter
@Embeddable
public class Password {

    private String hashing;

    public Password(String password, String salt) {

        this.hashing = Hashing.sha256().hashString(password + salt, StandardCharsets.UTF_8).toString()+salt;

    }

    public boolean match(String input){

        String origin = hashing.substring(0, hashing.length() - 12);
        String salt = hashing.substring(hashing.length() - 12);

        return Hashing.sha256().hashString(input + salt, StandardCharsets.UTF_8).toString().equals(origin);

    }

}
