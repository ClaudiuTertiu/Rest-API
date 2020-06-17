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
    String id;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
}

