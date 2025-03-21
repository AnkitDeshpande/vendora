package com.project.vendora.auth.model.request;

import com.project.vendora.core.model.request.AddressRequest;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class SignUpRequest {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String profilePictureUrl;
    private Set<AddressRequest> addresses;
}
