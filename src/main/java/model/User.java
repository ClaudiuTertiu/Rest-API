package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Value
@Builder
public class User {
    @JsonProperty("id")
<<<<<<< Updated upstream
    private String id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
=======
    String id;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
>>>>>>> Stashed changes
}

