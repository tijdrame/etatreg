package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp2InfosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp2Infos.class);
        Bp2Infos bp2Infos1 = new Bp2Infos();
        bp2Infos1.setId(1L);
        Bp2Infos bp2Infos2 = new Bp2Infos();
        bp2Infos2.setId(bp2Infos1.getId());
        assertThat(bp2Infos1).isEqualTo(bp2Infos2);
        bp2Infos2.setId(2L);
        assertThat(bp2Infos1).isNotEqualTo(bp2Infos2);
        bp2Infos1.setId(null);
        assertThat(bp2Infos1).isNotEqualTo(bp2Infos2);
    }
}
