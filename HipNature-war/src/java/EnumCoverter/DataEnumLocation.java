/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnumCoverter;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
@Named(value = "dataEnumLocation")
@ApplicationScoped
public class DataEnumLocation {

    public DataEnumLocation() {
    }
    public LocationTypeEnum[] getLocationTypeEnum(){
        return LocationTypeEnum.values();
    }
    
}
