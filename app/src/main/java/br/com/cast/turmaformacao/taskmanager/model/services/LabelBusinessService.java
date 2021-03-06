package br.com.cast.turmaformacao.taskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.persistence.LabelRepository;

public final class LabelBusinessService {

    private LabelBusinessService() {
        super();
    }

    public static void save(Label label) {
        LabelRepository.save(label);
    }

    public static Label getById(long id) {
        return LabelRepository.getById(id);
    }

    public static List<Label> getAll() {
        return LabelRepository.getAll();
    }
}
