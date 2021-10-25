package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.HoldingsDto;

import io.swagger.annotations.Api;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Asset Service")
@RestController
@RequestMapping(value = "/portfolio")
public class AssetController {

    @GetMapping(value = "/{userId}/asset")
    public ResponseEntity<EntityModel<HoldingsDto>> getAsset(@PathVariable(name = "userId") long user_id) {

        HoldingsDto vo = new HoldingsDto();
        vo.setUser_id(user_id);
        // List<HoldingsDto> result = portfolioService.getAsset(vo);

        return ResponseEntity.ok().body(
                EntityModel.of(vo).add(linkTo(methodOn(AssetController.class).getAsset(user_id)).withSelfRel()));

    }
}
