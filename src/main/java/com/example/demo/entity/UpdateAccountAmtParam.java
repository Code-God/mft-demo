package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: wll
 * @Date: 2018/10/17 13:39
 * @Auto: I AM A CODE MAN !
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountAmtParam implements Serializable {

    @NotNull
    private String mallUerId;

    private BigDecimal amount;

    private BigDecimal credit;
}


