package com.example.social.service;

import com.example.social.domain.model.Dialog;
import com.example.social.repository.DialogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DialogService {

    private final DialogRepository repository;

    public Dialog send(Long from, Long to, String text) {
        Dialog dialog = new Dialog();
        dialog.setFrom(from);
        dialog.setTo(to);
        dialog.setText(text);
       return repository.save(dialog);
    }

    public List<Dialog> getDialogs(Long from, Long to) {
        return repository.getDialogs(from, to);
    }



}
