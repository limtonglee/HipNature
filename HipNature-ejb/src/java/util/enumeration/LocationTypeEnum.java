/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.enumeration;

import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "locationTypeEnum")
public enum LocationTypeEnum {
    NORTH,
    WEST,
    EAST,
    CENTRAL,
    SOUTH
}
