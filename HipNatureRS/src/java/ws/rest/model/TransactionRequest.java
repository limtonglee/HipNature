/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class TransactionRequest {
    private Long transactionId;
    private String planType;
    private LocalDate transactionDate;
    private Double price;
    private String ccNumber;

    public TransactionRequest() {
    }

    public TransactionRequest(Long transactionId, String planType, LocalDate transactionDate, Double price, String ccNumber) {
        this.transactionId = transactionId;
        this.planType = planType;
        this.transactionDate = transactionDate;
        this.price = price;
        this.ccNumber = ccNumber;
    }

    /**
     * @return the transactionId
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the planType
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * @param planType the planType to set
     */
    public void setPlanType(String planType) {
        this.planType = planType;
    }

    /**
     * @return the transactionDate
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the ccNumber
     */
    public String getCcNumber() {
        return ccNumber;
    }

    /**
     * @param ccNumber the ccNumber to set
     */
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }
    
}
