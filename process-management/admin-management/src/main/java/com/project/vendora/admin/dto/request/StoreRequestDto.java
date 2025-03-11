package com.project.vendora.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDto {
    private Long id;
    private String storeName;
    private String email;
    private String contactNumber;
    private String businessType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String taxId;
    private String registrationNumber;
    private String website;
    private String description;
    private String currency;
    private String timezone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime openingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime closingTime;
    private Long ownerId;
    private String facebookUrl;
    private String instagramUrl;
    private String twitterUrl;
}
