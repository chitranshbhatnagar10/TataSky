package com.cg.apps.tataskyapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.pack.dto.PackDetails;
import com.cg.apps.tataskyapp.pack.entities.Pack;
import com.cg.apps.tataskyapp.pack.service.IPackService;
import com.cg.apps.tataskyapp.util.PackUtil;

@RestController
@RequestMapping("/pack")
@Validated
public class PackController {

	@Autowired
	private IPackService pService;

	@Autowired
	private PackUtil appUtil;

//to test controller	
	@RequestMapping("/hello")
	public String greet() {
		return "Hello from pack controller";
	}

//for admin to add a new pack,sends http 201 created success status response
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public PackDetails add(@RequestBody @Valid CreatePackRequest requestData) {
		Pack pack = new Pack(requestData.getCost(), requestData.getDaysValidity(), requestData.getDescription(),
				requestData.getPlanName(), requestData.getChannels());
		Pack pck = pService.add(pack);
		return PackUtil.toDetails(pck);

	}

//to update an existing pack	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PutMapping("/update")
	public Pack update(@RequestBody Pack pack) {
		return pService.update(pack);
	}

//to find a pack by it's id	
	@GetMapping("/by/id/{id}")
	public PackDetails findPackById(@PathVariable("id") Long id) {
		Pack pck = pService.findPackById(id);
		return PackUtil.toDetails(pck);
	}

//returns a list of packs in ascending order of their costs	i.e. least to most expensive pack
	@GetMapping("/by/cost")
	public List<PackDetails> findByCost() {
		List<Pack> list = pService.findPacksInAscendingOrderByCost();
		return PackUtil.toDetails(list);
	}

//returns a list with pack details as per increasing order of their validity 	
	@GetMapping("/by/daysValidity")
	public List<PackDetails> findBydaysValidity() {
		List<Pack> list = pService.findPacksInAscendingOrderByDaysValidity();
		return 	 PackUtil.toDetails(list);
	}

//returns a list of packs having amount greater than a specific amount entered by the client/user
	@GetMapping("/by/greaterAmount/{amount}")
	public List<PackDetails> findByAmount(@PathVariable("amount") Double amount) {
		List<Pack> list = pService.findPacksGreaterThanAmount(amount);
		return PackUtil.toDetails(list);
	}

	@GetMapping("/by/popularPacks")
	public List<String> findBypopularity() {
		return pService.popularPacks();

	}

//to delete a pack using it's id 	
	@DeleteMapping("/delete/by/id/{id}")
	public void deleteByPackId(@PathVariable("id") Long id) {
		pService.deleteByPackId(id);
	}

}
