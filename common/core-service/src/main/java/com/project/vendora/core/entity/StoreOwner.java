package com.project.vendora.core.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue("STORE_OWNER")
public class StoreOwner extends User {
}
