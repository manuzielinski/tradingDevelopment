package com.manudev.walletService.controller;

import com.manudev.common.dto.AssetDTO;
import com.manudev.common.dto.CoinDTO;
import com.manudev.common.dto.CreateAssetRequest;
import com.manudev.common.dto.UserDTO;
import com.manudev.walletService.client.UserClient;
import com.manudev.walletService.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private AssetController assetController;

    private AssetDTO assetDTO;
    private UserDTO userDTO;
    private CoinDTO coinDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setUserId(1L);

        coinDTO = new CoinDTO();
        coinDTO.setId("btc");

        assetDTO = new AssetDTO();
        assetDTO.setAssetId(100L);
        assetDTO.setQuantity(10.0);
    }

    @Test
    void testGetAssetById() {
        when(assetService.getAssetById(100L)).thenReturn(assetDTO);

        ResponseEntity<AssetDTO> response = assetController.getAssetById(100L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(assetDTO, response.getBody());
    }

    @Test
    void testGetAssetByUserIdAndCoinId() {
        when(assetService.findAssetByUserIdAndCoinId(1L, "btc")).thenReturn(assetDTO);

        ResponseEntity<AssetDTO> response = assetController.getAssetByUserIdAndCoinId(1L, "btc");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(assetDTO, response.getBody());
    }

    @Test
    void testGetAssetsForUser() throws Exception {
        when(userClient.findUserProfileByJwt("Bearer test.jwt.token")).thenReturn(userDTO);
        when(assetService.getUsersAssets(1L)).thenReturn(List.of(assetDTO));

        ResponseEntity<List<AssetDTO>> response = assetController.getAssetsForUser("Bearer test.jwt.token");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(assetDTO, response.getBody().get(0));
    }

    @Test
    void testCreateAsset() {
        CreateAssetRequest request = new CreateAssetRequest();
        request.setUserDTO(userDTO);
        request.setCoinDTO(coinDTO);
        request.setQuantity(10.0);

        when(assetService.createAsset(userDTO, coinDTO, 10.0)).thenReturn(assetDTO);

        ResponseEntity<AssetDTO> response = assetController.createAsset(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(assetDTO, response.getBody());
    }

    @Test
    void testUpdateAsset() {
        when(assetService.updateAsset(100L, 5.0)).thenReturn(assetDTO);

        AssetDTO result = assetController.updateAsset(100L, 5.0);

        assertEquals(assetDTO, result);
    }

    @Test
    void testDeleteAsset() {
        doNothing().when(assetService).deleteAsset(100L);

        ResponseEntity<String> response = assetController.deleteAsset(100L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Asset deleted Successfully", response.getBody());
    }
}
