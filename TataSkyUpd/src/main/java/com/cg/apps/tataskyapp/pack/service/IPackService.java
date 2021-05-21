package com.cg.apps.tataskyapp.pack.service;

import java.util.List;
import com.cg.apps.tataskyapp.pack.entities.Pack;

public interface IPackService {

	Pack add(Pack pack);

	Pack findPackById(Long id);

	List<Pack> findPacksInAscendingOrderByCost();

	List<Pack> findPacksInAscendingOrderByDaysValidity();

	List<Pack> findPacksGreaterThanAmount(Double amount);

	 List <String> popularPacks();

	Pack update(Pack pack);

	void deleteByPackId(Long id);

}
