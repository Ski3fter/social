package com.example.social.service;

import com.example.social.domain.model.Role;
import com.example.social.domain.model.User;
import com.example.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


    /**
     * Получение пользователя по имени пользователя по лайку
     *
     * @return список пользователей
     */
    public List<User> getFirstLastNameLike(String firstName, String lastName) {
        return repository.findByFirstLastNameLike(firstName, lastName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователи не найдены"));
    }

    /**
     * Получение пользователя по имени пользователя по индексу gin
     *
     * @return список пользователей
     */
    public List<User> getFirstLastNameIndex(String firstName, String lastName) {
        return repository.findByFirstLastNameIndex(String.format("%s:*", firstName), String.format("%s:*", lastName))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователи не найдены"));
    }

    /**
     * Получение пользователя по идентифкатору
     *
     * @return пользователь
     */
    public User getByUserId(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
