package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 收支流水记录表
 * </p>
 *
 * @author wuxb
 * @since 2018-10-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallJournalRecord implements Serializable {

    private static final long serialVersionUID = 1L;
	private String id;
    /**
     * 标题
     */
	private String title;

	private String billType;

	private String billTypeName;

	private String billTypeDetail;

	private String billId;
    /**
     * 用户id
     */
	private String mallUserId;

	private String adminId;
    /**
     * 关联用户id
     */
	private String relevanceUserId;
    /**
     * 币种 0人民币，1积分，2美元
     */
	private String currency;
    /**
     * 交易前金额
     */
	private BigDecimal payBefore;
    /**
     * 交易金额
     */
	private BigDecimal payAmount;
    /**
     * 交易后金额
     */
	private BigDecimal payAfter;

	private Date createDate;

	private String isDel;

	private String optionType;

	private String optionId;

	private String remark;

	private String companyId;



}
