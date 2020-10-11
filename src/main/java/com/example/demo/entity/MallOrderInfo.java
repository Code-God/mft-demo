package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuzh
 * @since 2018-09-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mall_order_info")
public class MallOrderInfo {

    private static final long serialVersionUID = 1L;

    @TableId("order_id")
	private String orderId;

    @TableField("mall_user_id")
	private String mallUserId;

    @TableField("payment_amt")
	private BigDecimal paymentAmt;

    @TableField("credit")
	private BigDecimal credit;

    @TableField("origin_amt")
	private BigDecimal originAmt;

    @TableField("post_fee_amt")
	private BigDecimal postFeeAmt;

    @TableField("order_type")
	private String orderType;

    @TableField("order_review")
	private String orderReview;

    @TableField("order_status")
	private String orderStatus;

    @TableField("belongs_code")
	private String belongsCode;

    @TableField("express_code")
	private String expressCode;

    @TableField("express_company")
	private String expressCompany;


    @TableField("buyer_memo")
	private String buyerMemo;

    @TableField("cs_memo")
	private String csMemo;

    @TableField("system_memo")
	private String systemMemo;

    @TableField("create_date")
	private Date createDate;

    @TableField("update_date")
	private Date updateDate;

    @TableField("eclpsono")
    private String eclpSoNo;

    @TableField("transaction_id")
    private String transactionId;

    @TableField("trade_no")
    private String tradeNo;


    public static void main(String[] args) {
        System.out.println(20/2);
    }
}
