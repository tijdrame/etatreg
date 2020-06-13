package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class ObjetCreditTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetCredit.class);
        ObjetCredit objetCredit1 = new ObjetCredit();
        objetCredit1.setId(1L);
        ObjetCredit objetCredit2 = new ObjetCredit();
        objetCredit2.setId(objetCredit1.getId());
        assertThat(objetCredit1).isEqualTo(objetCredit2);
        objetCredit2.setId(2L);
        assertThat(objetCredit1).isNotEqualTo(objetCredit2);
        objetCredit1.setId(null);
        assertThat(objetCredit1).isNotEqualTo(objetCredit2);
    }
}
