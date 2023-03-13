package dev.boiarshinov.breakingbad.clientlib.client;

import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdateRequestDto;
import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdatedDto;

public interface CrystalClient {

    CrystalUpdatedDto updateCrystalInfo(String crystalId, CrystalUpdateRequestDto crystalUpdateInfo);
}
