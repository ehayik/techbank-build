package com.github.playground.techbank.account.query;

import com.github.playground.techbank.account.common.AccountType;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BankAccount implements Serializable {

	@Id
	private String id;

	private String accountHolder;

	private LocalDate creationDate;

	private AccountType accountType;

	private BigDecimal balance = BigDecimal.ZERO;

	public void deposit(@NonNull BigDecimal amount) {
		balance = balance.add(amount);
	}

	public void withdraw(@NonNull BigDecimal amount) {

		if (balance.compareTo(amount) < 0) {
			throw new IllegalArgumentException("Insufficient balance in account " + id);
		}

		balance = balance.subtract(amount);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false;
		BankAccount that = (BankAccount) other;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

