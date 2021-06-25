package app.data.entity.listener;

import app.data.entity.basement.Entity_Main;

import app.utility.AES256;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import java.util.UUID;

public class UUIDListener {

    @Autowired
    private AES256 aes;

    @PrePersist
    public void preInsert(Entity_Main basement){
        basement.setUuid(aes.encrypt(UUID.randomUUID().toString().replaceAll("-", "")));
    }


}
