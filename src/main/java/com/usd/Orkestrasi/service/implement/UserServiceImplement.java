package com.usd.Orkestrasi.service.implement;

import com.usd.Orkestrasi.dto.request.SearchUserRequest;
import com.usd.Orkestrasi.dto.response.SearchUserResponse;
import com.usd.Orkestrasi.entity.User;
import com.usd.Orkestrasi.repository.UserRepository;
import com.usd.Orkestrasi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SearchUserResponse> getAll(SearchUserRequest searchUserRequest) {
        List<User> userList;
        if (searchUserRequest.getNameRequest() == null || searchUserRequest.getNameRequest().isEmpty()){
            userList = userRepository.findAll();
        } else {
            userList = userRepository.findUsersByFullNameLike(searchUserRequest.getNameRequest());
        }
        List<SearchUserResponse> responses = userList.stream().map(
            userRequestList-> {
                return SearchUserResponse.builder()
                        .id(userRequestList.getId())
                        .firstName(userRequestList.getFirstName())
                        .lastName(userRequestList.getLastName())
                        .email(userRequestList.getEmail())
                        .gender(userRequestList.getGender())
                        .build();
            }
        ).toList();
        return responses;
    }
}
