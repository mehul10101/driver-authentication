package com.project.driverauthentication.services;

import com.project.driverauthentication.entities.DriverEntity;
import com.project.driverauthentication.pojo.requests.SignUpRequest;
import com.project.driverauthentication.pojo.responses.SignUpResponse;
import com.project.driverauthentication.repositories.DriverRepository;
import com.project.driverauthentication.repositories.VehicleTypeRepository;
import com.project.driverauthentication.utils.RegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DriverSignUpService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RegistrationUtil registrationUtil;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    // business logic for sign up with some validations
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpResponse signUpResponse1 = registrationUtil.emailUserNamePasswordValidations(signUpRequest, signUpResponse);
        if (signUpResponse1 != null) {
            return signUpResponse1;
        }
        signUpResponse.setResponseMessage(createUser(signUpRequest));
        return signUpResponse;
    }

    // creating user entity and saving it
    private String createUser(SignUpRequest signUpRequest) {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setEmail(signUpRequest.getEmail());
        driverEntity.setName(signUpRequest.getName());
        driverEntity.setPhoneNumber(signUpRequest.getPhoneNumber());
        driverEntity.setUserName(signUpRequest.getUserName());
        driverEntity.setVehicleNumber(signUpRequest.getVehicleNumber());
        driverEntity.setVehicleType(vehicleTypeRepository.findByVehicleType(signUpRequest.getVehicleType()).get());
        driverEntity.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt(12)));
        driverRepository.save(driverEntity);
        return "Welcome " + signUpRequest.getUserName() + ", account created successfully";
    }

}
