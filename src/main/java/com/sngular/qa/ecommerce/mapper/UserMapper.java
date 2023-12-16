package com.sngular.qa.ecommerce.mapper;

import com.sngular.qa.ecommerce.domain.Review;
import com.sngular.qa.ecommerce.domain.User;
import com.sngular.qa.ecommerce.dto.perfume.PerfumeResponse;
import com.sngular.qa.ecommerce.dto.review.ReviewRequest;
import com.sngular.qa.ecommerce.dto.user.UserRequest;
import com.sngular.qa.ecommerce.dto.user.UserResponse;
import com.sngular.qa.ecommerce.exception.InputFieldException;
import com.sngular.qa.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserResponse findUserById(Long userId) {
        return commonMapper.convertToResponse(userService.findUserById(userId), UserResponse.class);
    }

    public UserResponse findUserByEmail(String email) {
        return commonMapper.convertToResponse(userService.findUserByEmail(email), UserResponse.class);
    }

    public List<PerfumeResponse> getCart(List<Long> perfumesIds) {
        return commonMapper.convertToResponseList(userService.getCart(perfumesIds), PerfumeResponse.class);
    }

    public List<UserResponse> findAllUsers() {
        return commonMapper.convertToResponseList(userService.findAllUsers(), UserResponse.class);
    }

    public UserResponse updateProfile(String email, UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        User user = commonMapper.convertToEntity(userRequest, User.class);
        return commonMapper.convertToResponse(userService.updateProfile(email, user), UserResponse.class);
    }

    public PerfumeResponse addReviewToPerfume(ReviewRequest reviewRequest, Long perfumeId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Review review = commonMapper.convertToEntity(reviewRequest, Review.class);
        return commonMapper.convertToResponse(userService.addReviewToPerfume(review, perfumeId), PerfumeResponse.class);
    }
}
