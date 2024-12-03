package com.ufsm.portaldengue.repository;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointSituation;

public class PointRepositoryTests {
	@Autowired
	PointRepository pointRepository;

	@Test
	public void PointRepositorySaveTest() {
		Point point = new Point(1L, 5.5, 5.5, "teste", LocalDateTime.now(), PointSituation.getDefaultPointSituation());
		Point savedPoint = pointRepository.save(point);

		Assertions.assertThat(savedPoint).isNotNull();
		Assertions.assertThat(savedPoint.getId()).isGreaterThan(0);
	}

}
