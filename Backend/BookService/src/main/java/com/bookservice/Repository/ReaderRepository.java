package com.bookservice.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookservice.entity.Reader;

@Transactional
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

	Boolean existsByEmail(String email);

	Reader findByEmail(String email);

	@Modifying
	@Query("UPDATE Payment p SET p.refund_status = :rStatus WHERE p.id = :paymentId")
	Integer updateRefundStatus(@Param("paymentId") int paymentId, @Param("rStatus") String rStatus);
}
