package com.project.vendora.auth.service.impl;

import com.project.vendora.auth.model.request.EmailDetails;
import com.project.vendora.auth.model.request.PasswordRequest;
import com.project.vendora.auth.model.request.SignUpRequest;
import com.project.vendora.auth.service.NotificationService;
import com.project.vendora.auth.service.UserService;
import com.project.vendora.core.constant.ErrorMessage;
import com.project.vendora.core.entity.Address;
import com.project.vendora.core.entity.Role;
import com.project.vendora.core.entity.User;
import com.project.vendora.core.exception.VendoraException;
import com.project.vendora.core.model.request.AddressRequest;
import com.project.vendora.core.repository.UserRepository;
import com.project.vendora.core.utility.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final NotificationService notificationService;

    private final Mapper mapper = new Mapper();

    /**
     * Registers a new user.
     *
     * @param signUpRequest the sign-up request data transfer object
     * @return the registered User
     */
    @Override
    @Transactional
    public User saveUser(SignUpRequest signUpRequest) {
        User userToSave;

        if (Objects.nonNull(signUpRequest.getId())) {

            userToSave = userRepository.findById(signUpRequest.getId())
                    .orElseThrow(() -> new VendoraException(ErrorMessage.USER_NOT_FOUND.getCode(), ErrorMessage.USER_NOT_FOUND.getMessage()));

        } else {

            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                throw new VendoraException(500, "User already exists with the username: " + signUpRequest.getUsername());
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                throw new VendoraException(500, "Email already in use: " + signUpRequest.getEmail());
            }

            userToSave = new User();
        }

        userToSave.setUsername(signUpRequest.getUsername());
        userToSave.setEmail(signUpRequest.getEmail());
        userToSave.setFirstName(signUpRequest.getFirstName());
        userToSave.setLastName(signUpRequest.getLastName());
        userToSave.setPhoneNumber(signUpRequest.getPhoneNumber());
        userToSave.setDateOfBirth(signUpRequest.getDateOfBirth());
        userToSave.setProfilePictureUrl(signUpRequest.getProfilePictureUrl());
        if (Objects.isNull(signUpRequest.getId())) {
            Role role = new Role();
            role.setName(com.project.vendora.core.constant.Role.BASIC_USER.getValue());
            role.setDescription(com.project.vendora.core.constant.Role.BASIC_USER.getValue());
            userToSave.getRoles().add(role);
        }

        User savedUser = userRepository.save(userToSave);

        if (Objects.nonNull(signUpRequest.getAddresses())) {
            Set<Address> existingAddresses = new HashSet<>(savedUser.getAddresses());

            for (AddressRequest addressRequest : signUpRequest.getAddresses()) {
                if (addressRequest.getId() != null) {
                    Address existingAddress = existingAddresses.stream()
                            .filter(address -> address.getId().equals(addressRequest.getId()))
                            .findFirst()
                            .orElseThrow(() -> new VendoraException(404, "Address not found"));

                    existingAddress.setStreet(addressRequest.getStreet());
                    existingAddress.setCity(addressRequest.getCity());
                    existingAddress.setState(addressRequest.getState());
                    existingAddress.setCountry(addressRequest.getCountry());
                    existingAddress.setZipCode(addressRequest.getZipCode());

                    existingAddresses.remove(existingAddress);
                    savedUser.getAddresses().add(existingAddress);
                } else {
                    Address newAddress = mapper.convert(addressRequest, Address.class);
                    newAddress.setUser(savedUser);
                    savedUser.getAddresses().add(newAddress);
                }
            }

            for (Address addressToRemove : existingAddresses) {
                savedUser.getAddresses().remove(addressToRemove);
            }
        }

        if (Objects.isNull(signUpRequest.getId())) {
            notificationService.sendActivationEmail(EmailDetails.builder()
                    .messageBody("Your registration was successful.")
                    .recipient(signUpRequest.getEmail())
                    .subject("Registration Success")
                    .build());
        }

        return userRepository.save(savedUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void activateUser(String email, String token, String expiresAt, PasswordRequest passwordRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new VendoraException(404, "User not found with email : " + email));

        if (Objects.isNull(user)) {
            return;
        }

        LocalDateTime expirationTime = LocalDateTime.parse(expiresAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (expirationTime.isBefore(LocalDateTime.now())) {
            return;
        }

        if (!passwordRequest.getPassword().equals(passwordRequest.getConfirmPassword())) {
            throw new VendoraException(ErrorMessage.PASSWORDS_DO_NOT_MATCH.getCode(),
                    ErrorMessage.PASSWORDS_DO_NOT_MATCH.getMessage());
        }

        user.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        user.setActive(Boolean.TRUE);
        user.setEmailVerified(Boolean.TRUE);
        userRepository.save(user);
    }
}
