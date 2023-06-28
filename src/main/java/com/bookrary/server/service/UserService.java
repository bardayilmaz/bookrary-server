package com.bookrary.server.service;

import com.bookrary.server.entity.*;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.UpdateUserRequest;
import com.bookrary.server.model.response.UserResponse;
import com.bookrary.server.model.response.UserStatsResponse;
import com.bookrary.server.repository.SaleRepository;
import com.bookrary.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponse::fromEntity);
    }

    public UserResponse getUser(String id) {
        return UserResponse.fromEntity(userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", ErrorCode.resource_missing)));
    }

    public UserStatsResponse getUserStats(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", ErrorCode.resource_missing));
        int totalSale = saleRepository.countBySeller(user);
        int totalPurchased = saleRepository.countByBuyerAndSaleStatus(user, SaleStatus.RECEIVED);
        int totalDonated = saleRepository.countByBuyerAndSaleStatus(user, SaleStatus.EXPIRED);
        List<BookType> mostBoughtBookTypes = saleRepository.mostPurchasedDonatedGenres(user.getId());
        List<Sale> purchaseDonationHistory = saleRepository.findFirst10ByBuyerOrderByCreatedDesc(user);
        List<BookType> mostSoldBookTypes = saleRepository.mostSoldGenresOfSeller(user.getId());
        return UserStatsResponse.fromStats(totalSale, totalPurchased, totalDonated, mostBoughtBookTypes, purchaseDonationHistory, mostSoldBookTypes);
    }

    public UserResponse updateUser(String id, UpdateUserRequest updateUserRequest, Optional<User> authenticatedUserOptional) {
        if(!authenticatedUserOptional.isPresent()) {
            throw new BusinessException("Auth user is not present", ErrorCode.forbidden);
        }
        User currentUser = authenticatedUserOptional.get();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", ErrorCode.resource_missing));
        if (currentUser.getId().equals(user.getId()) && !currentUser.getRole().equals(updateUserRequest.getRole())) {
            throw new BusinessException("Can not update self role", ErrorCode.forbidden);
        }
        if (!user.getRole().equals(UserRole.ADMIN) && !currentUser.getId().equals(user.getId())) {
            throw new BusinessException("Can not update another user", ErrorCode.forbidden);
        }
        fromRequest(user, updateUserRequest);
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    private User fromRequest(User user, UpdateUserRequest updateUserRequest) {
        if(!user.getEmail().equals(updateUserRequest.getEmail()) &&
                userRepository.existsByEmail(updateUserRequest.getEmail())) {
            throw new BusinessException("Email already exists.", ErrorCode.validation);
        }
        user.setLibrary(updateUserRequest.getLibrary());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        user.setEmail(updateUserRequest.getEmail());
        user.setRole(updateUserRequest.getRole());
        return user;
    }

}
