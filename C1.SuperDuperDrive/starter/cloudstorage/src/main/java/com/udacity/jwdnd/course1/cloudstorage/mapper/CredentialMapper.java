package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredentials(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredential(int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Select("SELECT c.credentialid, c.url, c.username, c.key, c.password, c.userid" +
            " FROM USERS AS u" +
            " JOIN CREDENTIALS AS c" +
            " ON u.userid = c.userid" +
            " WHERE u.userid = #{userId}")
    List<Credential> getAllCredentialsForUser(int userId);

    @Select("SELECT c.credentialid, c.url, c.username, c.password, c.userid" +
            " FROM USERS AS u" +
            " JOIN CREDENTIALS AS c" +
            " ON u.userid = c.userid" +
            " AND c.credentialid = #{credentialId}")
    Credential getCredentialForUserByCredentialId(Integer credentialId);

    @Update("Update CREDENTIALS AS c " +
            "SET c.url = #{url}, c.username = #{userName}, c.password = #{password}" +
            "WHERE c.credentialId = #{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS AS c WHERE c.credentialid = #{credentialId}")
    int deleteCredentialById(int credentialId);
}
