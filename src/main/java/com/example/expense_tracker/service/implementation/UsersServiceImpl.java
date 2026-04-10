package com.example.expense_tracker.service.implementation;

import com.example.expense_tracker.dto.user.UserResponseDto;
import com.example.expense_tracker.entity.Users;
import com.example.expense_tracker.exception.ResourceNotFoundException;
import com.example.expense_tracker.repository.UsersRepository;
import com.example.expense_tracker.service.UsersService;
import com.example.expense_tracker.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Couldn't find user with id " + userId
                        ));

        return userMapper.toUserResponseDto(user);

    }
}
