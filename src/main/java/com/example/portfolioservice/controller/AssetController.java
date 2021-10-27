package com.example.portfolioservice.controller;

import com.example.portfolioservice.model.HoldingsResponseDto;
import com.example.portfolioservice.service.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Asset Service")
@RestController
public class AssetController {

    @Autowired
    AssetService assetService;

    @ApiOperation(value = "사용자 자산 조회", notes = "특정 유저의 자산 포트폴리오를 조회한다.")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "long", defaultValue = "None")
    @GetMapping(value = "/asset/{userId}")
    public ResponseEntity<EntityModel<HoldingsResponseDto>> getAsset(@PathVariable(name = "userId") long userId) {

        return ResponseEntity.ok().body(EntityModel.of(assetService.getAsset(userId)));
    }
}
