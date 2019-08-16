package com.example.quanlythuchi.model;

public class Note4 {
    private String noteId4;
    private String noteTitle4;
    private String noteContent4;
    private Double Gia4;

    public Note4() {

    }

    public String getNoteId4() {
        return noteId4;
    }

    public void setNoteId4(String noteId4) {
        this.noteId4 = noteId4;
    }

    public String getNoteTitle4() {
        return noteTitle4;
    }

    public void setNoteTitle4(String noteTitle4) {
        this.noteTitle4 = noteTitle4;
    }

    public String getNoteContent4() {
        return noteContent4;
    }

    public void setNoteContent4(String noteContent4) {
        this.noteContent4 = noteContent4;
    }

    public Double getGia4() {
        return Gia4;
    }

    public void setGia4(Double gia4) {
        Gia4 = gia4;
    }

    public Note4(String noteTitle4, String noteContent4, Double gia4) {
        this.noteTitle4 = noteTitle4;
        this.noteContent4 = noteContent4;
        this.Gia4 = gia4;
    }

    public Note4(String noteId4, String noteTitle4, String noteContent4, Double gia4) {
        this.noteId4 = noteId4;
        this.noteTitle4 = noteTitle4;
        this.noteContent4 = noteContent4;
        this.Gia4 = gia4;
    }
}
