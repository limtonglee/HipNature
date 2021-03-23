/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.RefundEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.RefundNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface RefundEntitySessionBeanLocal {

    public Long createNewRefund(RefundEntity newRefund);

    public RefundEntity retrieveRefundByRefundId(Long refundId) throws RefundNotFoundException;

    public List<RefundEntity> retrieveAllRefunds();
    
}
