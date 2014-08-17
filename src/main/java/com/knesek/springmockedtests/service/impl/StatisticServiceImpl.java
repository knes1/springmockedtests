package com.knesek.springmockedtests.service.impl;

import com.knesek.springmockedtests.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Dummy implementation for example purposes.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Service
public class StatisticServiceImpl implements StatisticsService {

	private final static Logger log = LoggerFactory.getLogger(StatisticServiceImpl.class);

	@Override
	public void recalculateStatisticReports() {
		log.info("Recalculating Statistics...");
	}
}
