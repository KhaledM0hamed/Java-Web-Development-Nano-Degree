package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNote(String noteTitle);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNoteById(Integer noteId);

    @Select("SELECT n.noteid, n.notetitle, n.notedescription, n.userid" +
            " FROM USERS AS u" +
            " JOIN NOTES AS n" +
            " ON u.userid = n.userid" +
            " WHERE u.userid = #{userId}")
    List<Note> getUserNotesById(int userId);

    @Select("SELECT n.noteid, n.notetitle, n.notedescription, n.userid" +
            " FROM USERS AS u" +
            " JOIN NOTES AS n" +
            " ON u.userid = n.userid" +
            " WHERE u.username = #{username}")
    List<Note> getUserNotes(String username);

    @Insert("INSERT INTO NOTES (noteTitle, notedescription, userid)" +
            " VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Update("Update NOTES AS n " +
            "SET n.notetitle = #{noteTitle}, n.notedescription = #{noteDescription} " +
            "WHERE n.noteid = #{noteId}")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTES AS n WHERE n.noteid = #{noteId}")
    int deleteNote(Integer noteId);
}
