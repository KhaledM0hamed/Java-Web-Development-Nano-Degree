package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential){
        return credentialMapper.insertCredential(credential);
    }

    public List<Credential> getUserCredentials(User user){
        return credentialMapper.getAllCredentialsForUser(user.getUserId());
    }

    public Credential getCredential(int credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    public int deleteCredentialById(int id){
        return credentialMapper.deleteCredentialById(id);
    }

    public int updateCredential(Credential credential) {
        Credential cred = credentialMapper.getCredential(credential.getCredentialId());
        credential.setKey(cred.getKey());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(),credential.getKey()));
        return credentialMapper.updateCredential(credential);
    }
}
