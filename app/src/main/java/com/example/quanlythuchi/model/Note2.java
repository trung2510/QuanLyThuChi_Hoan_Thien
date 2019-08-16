package com.example.quanlythuchi.model;

public class Note2 {
    private String noteId2;
    private String noteTitle2;

    public Note2(String noteId, String noteTitle) {
        this.noteId2 = noteId;
        this.noteTitle2 = noteTitle;
    }

    public Note2(String noteTitle, Double noteContent) {
        this.noteTitle2 = noteTitle;
    }

    public Note2() {

    }

    public String getNoteId2() {
        return noteId2;
    }

    public void setNoteId2(String noteId2) {
        this.noteId2 = noteId2;
    }

    public String getNoteTitle2() {
        return noteTitle2;
    }

    public void setNoteTitle2(String noteTitle2) {
        this.noteTitle2 = noteTitle2;
    }

}
