package com.fish.dao;

import com.fish.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Dec 20 13:45:28 SGT 2018
     */
    int updateByPrimaryKey(PromoDO record);
}