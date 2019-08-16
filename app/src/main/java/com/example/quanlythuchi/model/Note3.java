package com.example.quanlythuchi.model;

public class Note3 {
    private String noteId3;
    private String noteTitle3;
    private String noteContent3;
//    int khoanThu;
    private Float Gia3;

    public Note3() {

    }

    public String getNoteId3() {
        return noteId3;
    }

    public void setNoteId3(String noteId3) {
        this.noteId3 = noteId3;
    }

    public String getNoteTitle3() {
        return noteTitle3;
    }

    public void setNoteTitle3(String noteTitle3) {
        this.noteTitle3 = noteTitle3;
    }

    public String getNoteContent3() {
        return noteContent3;
    }

    public void setNoteContent3(String noteContent3) {
        this.noteContent3 = noteContent3;
    }

    public Float getGia3() {
        return Gia3;
    }

    public void setGia3(Float gia3) {
        Gia3 = gia3;
    }

    public Note3(String noteTitle3, String noteContent3, Float gia3) {
        this.noteTitle3 = noteTitle3;
        this.noteContent3 = noteContent3;
        Gia3 = gia3;
    }

    public Note3(String noteId3, String noteTitle3, String noteContent3, Float gia3) {
        this.noteId3 = noteId3;
        this.noteTitle3 = noteTitle3;
        this.noteContent3 = noteContent3;
        Gia3 = gia3;
    }
}
