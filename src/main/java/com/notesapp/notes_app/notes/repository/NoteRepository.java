package com.notesapp.notes_app.notes.repository;

import com.notesapp.notes_app.notes.entity.Note;
import com.notesapp.notes_app.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,String> {
    List<Note> findByUser(User user);
}
