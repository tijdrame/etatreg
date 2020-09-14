package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class ChargeFileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChargeFile.class);
        ChargeFile chargeFile1 = new ChargeFile();
        chargeFile1.setId(1L);
        ChargeFile chargeFile2 = new ChargeFile();
        chargeFile2.setId(chargeFile1.getId());
        assertThat(chargeFile1).isEqualTo(chargeFile2);
        chargeFile2.setId(2L);
        assertThat(chargeFile1).isNotEqualTo(chargeFile2);
        chargeFile1.setId(null);
        assertThat(chargeFile1).isNotEqualTo(chargeFile2);
    }
}
