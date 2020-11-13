package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BpsectinstTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bpsectinst.class);
        Bpsectinst bpsectinst1 = new Bpsectinst();
        bpsectinst1.setId(1L);
        Bpsectinst bpsectinst2 = new Bpsectinst();
        bpsectinst2.setId(bpsectinst1.getId());
        assertThat(bpsectinst1).isEqualTo(bpsectinst2);
        bpsectinst2.setId(2L);
        assertThat(bpsectinst1).isNotEqualTo(bpsectinst2);
        bpsectinst1.setId(null);
        assertThat(bpsectinst1).isNotEqualTo(bpsectinst2);
    }
}
