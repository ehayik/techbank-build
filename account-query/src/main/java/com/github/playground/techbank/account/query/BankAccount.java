package com.github.playground.techbank.account.query;

import com.github.playground.techbank.account.common.AccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BankAccount implements Serializable {

	@Id
	@EqualsAndHashCode.Include
	private String id;

	private String accountHolder;

	private LocalDate creationDate;

	private AccountType accountType;

	private BigDecimal balance;

}
