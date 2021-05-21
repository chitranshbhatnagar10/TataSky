package com.cg.apps.tataskyapp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.apps.tataskyapp.pack.dto.PackDetails;
import com.cg.apps.tataskyapp.pack.entities.Pack;

@Component
public class PackUtil {

	public static PackDetails toDetails(Pack pck) {

		List<String> channels = pck.getChannels();
		List<String> details = new ArrayList<>();
		for (String channel : channels) {
			details.add(channel);
		}

		return new PackDetails(pck.getId(), pck.getCost(), pck.getDaysValidity(),
				pck.getDescription(), pck.getPlanName(), details);

	}

	public static List<PackDetails> toDetails(List<Pack> list) {
		List<PackDetails> pckDetailList = new ArrayList<>();
		for (Pack pack : list) {
			pckDetailList.add(toDetails(pack));
		}
		return pckDetailList;
	}
}
