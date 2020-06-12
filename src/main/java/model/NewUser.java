package model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUser {
    String username;
    String password;
}

