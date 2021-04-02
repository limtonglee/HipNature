/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.ClassEntity;
import java.util.List;

/**
 *
 * @author kelly
 */
public class RetrieveAllClassesRsp {

    private List<ClassEntity> classEntities;

    public RetrieveAllClassesRsp() {
    }

    public RetrieveAllClassesRsp(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

}
