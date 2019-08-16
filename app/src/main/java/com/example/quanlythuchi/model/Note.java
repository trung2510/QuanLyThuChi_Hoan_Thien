package com.example.quanlythuchi.model;

public class Note {
    private String noteId;
    private String noteTitle;

    public Note(String noteId, String noteTitle) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
    }

    public Note(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public Note() {

    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
