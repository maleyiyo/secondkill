package com.fish.dataobject;

import java.util.Date;

public class PromoDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.prome_name
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    private String promeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.start_time
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    private Date startTime;

    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.item_id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.promo_item_price
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    private Double promoItemPrice;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.id
     *
     * @return the value of promo.id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.id
     *
     * @param id the value for promo.id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.prome_name
     *
     * @return the value of promo.prome_name
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public String getPromeName() {
        return promeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.prome_name
     *
     * @param promeName the value for promo.prome_name
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public void setPromeName(String promeName) {
        this.promeName = promeName == null ? null : promeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.start_time
     *
     * @return the value of promo.start_time
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.start_time
     *
     * @param startTime the value for promo.start_time
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.item_id
     *
     * @return the value of promo.item_id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.item_id
     *
     * @param itemId the value for promo.item_id
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.promo_item_price
     *
     * @return the value of promo.promo_item_price
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public Double getPromoItemPrice() {
        return promoItemPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.promo_item_price
     *
     * @param promoItemPrice the value for promo.promo_item_price
     *
     * @mbg.generated Thu Dec 20 13:39:32 SGT 2018
     */
    public void setPromoItemPrice(Double promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}