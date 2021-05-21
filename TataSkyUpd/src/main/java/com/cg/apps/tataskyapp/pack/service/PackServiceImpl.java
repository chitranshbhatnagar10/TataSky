package com.cg.apps.tataskyapp.pack.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tataskyapp.exception.PackNotFoundException;
import com.cg.apps.tataskyapp.pack.entities.Pack;
import com.cg.apps.tataskyapp.pack.repository.IPackRepository;

@Service
@Transactional
public class PackServiceImpl implements IPackService {

	@Autowired
	private IPackRepository sDao;

	private Logger logger = LoggerFactory.getLogger(PackServiceImpl.class);

	@Override
	public Pack add(Pack pack) {
		logger.info("******** Pack Created*****");
		return sDao.save(pack);
	}

	@Override
	public Pack findPackById(Long id) {
		logger.info("******** Pack found for id *******" + id);
		Optional<Pack> opt = sDao.findById(id);
		if (!opt.isPresent()) {
			throw new PackNotFoundException("Pack not found for id:" + id);
		}
		return opt.get();
	}

	@Override
	public List<Pack> findPacksInAscendingOrderByCost() {
		logger.info("*******findingPacksInAscendingOrderByCost*******");
		List<Pack> list = sDao.findPacksInAscendingOrderByCost();
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<Pack> findPacksInAscendingOrderByDaysValidity() {
		logger.info("*******findingPacksInAscendingOrderByAmount*******");
		List<Pack> list = sDao.findPacksInAscendingOrderByDaysValidity();
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<Pack> findPacksGreaterThanAmount(Double amount) {
		logger.info("*******findingPacksGreaterThanAmount*******");
		List<Pack> list = sDao.findPacksGreaterThanAmount(amount);
		if (list.isEmpty()) {
			throw new PackNotFoundException("Pack not found");
		}
		return list;
	}

	@Override
	public List<String> popularPacks() {
		return sDao.popularPacks();
	}

	@Override
	public Pack update(Pack pack) {
		return sDao.save(pack);
	}

	@Override
	public void deleteByPackId(Long id) {
		logger.info("******** Pack Deleted for id *******" + id);
		Optional<Pack> opt = sDao.findById(id);
		if (!opt.isPresent()) {
			throw new PackNotFoundException("Pack not found");

		}

		sDao.deleteById(id);

	}

}
