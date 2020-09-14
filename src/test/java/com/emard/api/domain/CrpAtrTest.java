package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class CrpAtrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrpAtr.class);
        CrpAtr crpAtr1 = new CrpAtr();
        crpAtr1.setId(1L);
        CrpAtr crpAtr2 = new CrpAtr();
        crpAtr2.setId(crpAtr1.getId());
        assertThat(crpAtr1).isEqualTo(crpAtr2);
        crpAtr2.setId(2L);
        assertThat(crpAtr1).isNotEqualTo(crpAtr2);
        crpAtr1.setId(null);
        assertThat(crpAtr1).isNotEqualTo(crpAtr2);
    }
}
