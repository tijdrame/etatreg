package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class AcheteurVendeurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcheteurVendeur.class);
        AcheteurVendeur acheteurVendeur1 = new AcheteurVendeur();
        acheteurVendeur1.setId(1L);
        AcheteurVendeur acheteurVendeur2 = new AcheteurVendeur();
        acheteurVendeur2.setId(acheteurVendeur1.getId());
        assertThat(acheteurVendeur1).isEqualTo(acheteurVendeur2);
        acheteurVendeur2.setId(2L);
        assertThat(acheteurVendeur1).isNotEqualTo(acheteurVendeur2);
        acheteurVendeur1.setId(null);
        assertThat(acheteurVendeur1).isNotEqualTo(acheteurVendeur2);
    }
}
