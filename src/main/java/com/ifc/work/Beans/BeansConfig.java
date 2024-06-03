package com.ifc.work.Beans;

import com.ifc.work.gateways.UserGateway;
import com.ifc.work.services.AddressService;
import com.ifc.work.services.BookLoanService;
import com.ifc.work.servicesImpl.AddressServiceImpl;
import com.ifc.work.servicesImpl.BookLoanServiceImpl;
import com.ifc.work.servicesImpl.UserGatewayImpl;
import com.ifc.work.usecases.CreateUserUseCase;
import com.ifc.work.usecases.GetAllUsersUseCase;
import com.ifc.work.usecases.usecasesImpl.CreateUserUseCaseImpl;
import com.ifc.work.usecases.usecasesImpl.GetAllUsersUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public UserGateway userGateway() {
        // Instantiate and return your UserGateway implementation
        return new UserGatewayImpl(); // Example: Replace with your actual UserGateway implementation
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCaseImpl(userGateway);
    }

    @Bean
    public GetAllUsersUseCase getAllUsersUseCase(UserGateway userGateway) {
        return new GetAllUsersUseCaseImpl(userGateway);
    }

    @Bean
    public AddressService addressService() {
        return new AddressServiceImpl(); // Instantiate the service implementation
    }

    @Bean
    public BookLoanService bookLoanService(){
        return new BookLoanServiceImpl();
    }

}
