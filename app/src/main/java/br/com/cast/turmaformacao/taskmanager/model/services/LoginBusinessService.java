package br.com.cast.turmaformacao.taskmanager.model.services;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LoginRepository;

public final class LoginBusinessService {

    public LoginBusinessService() {
        super();
    }

    public static void save(User user) {
        LoginRepository.save(user);
    }

    public static User getById(long id) {
        return LoginRepository.getById(id);
    }
}
