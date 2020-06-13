package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class NatureDebiteurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureDebiteur.class);
        NatureDebiteur natureDebiteur1 = new NatureDebiteur();
        natureDebiteur1.setId(1L);
        NatureDebiteur natureDebiteur2 = new NatureDebiteur();
        natureDebiteur2.setId(natureDebiteur1.getId());
        assertThat(natureDebiteur1).isEqualTo(natureDebiteur2);
        natureDebiteur2.setId(2L);
        assertThat(natureDebiteur1).isNotEqualTo(natureDebiteur2);
        natureDebiteur1.setId(null);
        assertThat(natureDebiteur1).isNotEqualTo(natureDebiteur2);
    }
}
