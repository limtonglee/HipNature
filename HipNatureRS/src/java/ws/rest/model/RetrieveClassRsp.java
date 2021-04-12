/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.ClassEntity;

/**
 *
 * @author kelly
 */
public class RetrieveClassRsp {
    
    private ClassEntity classEntity;

    public RetrieveClassRsp() {
    }

    public RetrieveClassRsp(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    /**
     * @return the classEntity
     */
    public ClassEntity getClassEntity() {
        return classEntity;
    }

    /**
     * @param classEntity the classEntity to set
     */
    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }
             
}
