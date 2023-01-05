package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileById(int fileId);

    @Select("SELECT f.fileid, f.filename, f.contenttype, f.filesize, f.userid, f.filedata" +
            " FROM USERS AS u" +
            " JOIN FILES AS f" +
            " ON u.userid = f.userid" +
            " WHERE u.username = #{userName}")
    List<File> getUserFiles(String userName);

    @Select("SELECT f.fileid, f.filename, f.contenttype, f.filesize, f.userid, f.filedata" +
            " FROM USERS AS u" +
            " JOIN FILES AS f" +
            " ON u.userid = f.userid" +
            " WHERE u.userid = #{userId}")
    List<File> getUserFilesById(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES AS f WHERE f.fileid = #{fileId}")
    int deleteFileById(int fileId);
}
