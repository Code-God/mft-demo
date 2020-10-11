package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuxb
 * @since 2018-10-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mall_pay_info")
public class MallPayInfo extends Model<MallPayInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 支付id
     */
	@TableId("pay_id")
	private String payId;
    /**
     * 订单号
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 用户id
     */
	@TableField("mall_user_id")
	private String mallUserId;
    /**
     * 入账账户号
     */
	@TableField("in_account_code")
	private String inAccountCode;
    /**
     * 出账账户号
     */
	@TableField("out_account_code")
	private String outAccountCode;
    /**
     * 标题
     */
	@TableField("title")
	private String title;
    /**
     * 总金额
     */
	@TableField("total_amt")
	private BigDecimal totalAmt;
    /**
     * 余额支付金额
     */
	@TableField("balance_payment_amt")
	private BigDecimal balancePaymentAmt;
    /**
     * 移动支付金额
     */
	@TableField("mobile_payment_amt")
	private BigDecimal mobilePaymentAmt;
    /**
     * 积分
     */
	@TableField("credit")
	private BigDecimal credit;
    /**
     * 支付方式 与运算，0钱包支付，1现金支付，2支付宝支付，3微信支付，4组合支付
     */
	@TableField("pay_type")
	private String payType;

	@TableField(exist = false)
	private String payTypeName;
    /**
     * 支付状态。0未支付。1已支付。2,待退款 3已退款，4已过期，5已取消，6失败，7=支付中
     */
	@TableField("pay_status")
	private String payStatus;
    /**
     * 第三方支付返回订单号
     */
	@TableField("vendor_order_id")
	private String vendorOrderId;
    /**
     * 支付时间
     */
	@TableField("pay_time")
	private Date payTime;
    /**
     * 币种
     */
	@TableField("currency")
	private String currency;
    /**
     * 备注
     */
	@TableField("memo")
	private String memo;
    /**
     * 创建日期
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 修改日期
     */
	@TableField("update_date")
	private Date updateDate;
    /**
     * 状态，0正常，1作废
     */
	@TableField("status")
	private String status;
    /**
     * 是否删除，0正常，1已删除
     */
	@TableField("is_del")
	private String isDel;

	@TableField("transaction_id")
	private String transactionId;

	@TableField("trade_no")
	private String tradeNo;

//	@Override
//	protected Serializable pkVal() {
//		return this.payId;
//	}

}
