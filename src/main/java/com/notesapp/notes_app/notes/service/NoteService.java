package com.notesapp.notes_app.notes.service;

import com.notesapp.notes_app.notes.dto.CreateNoteRequest;
import com.notesapp.notes_app.notes.dto.NoteResponse;
import com.notesapp.notes_app.notes.entity.Note;
import com.notesapp.notes_app.notes.repository.NoteRepository;
import com.notesapp.notes_app.users.entity.User;
import com.notesapp.notes_app.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public NoteResponse createNote(
            CreateNoteRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        Note note = Note.builder()

                .title(request.getTitle())

                .content(request.getContent())

                .pinned(false)

                .archived(false)

                .createdAt(LocalDateTime.now())

                .updatedAt(LocalDateTime.now())

                .user(user)

                .build();

        Note savedNote =
                noteRepository.save(note);

        return mapToResponse(savedNote);
    }

    public List<NoteResponse> getMyNotes() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return noteRepository
                .findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private NoteResponse mapToResponse(
            Note note
    ) {

        return NoteResponse.builder()

                .id(note.getId())

                .title(note.getTitle())

                .content(note.getContent())

                .pinned(note.isPinned())

                .archived(note.isArchived())

                .build();
    }
}