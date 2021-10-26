package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.HoldingsDto;
import com.example.portfolioservice.model.HoldingsResponseDto;
import com.example.portfolioservice.service.AssetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Asset Service")
@RestController
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping(value = "/asset/{userId}")
    public ResponseEntity<EntityModel<HoldingsResponseDto>> getAsset(@PathVariable(name = "userId") long userId) {

        HoldingsResponseDto response = new HoldingsResponseDto();
        return ResponseEntity.ok().body(EntityModel.of(assetService.getAsset(userId)));
    }
}
