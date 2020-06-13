package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp3InfosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp3Infos.class);
        Bp3Infos bp3Infos1 = new Bp3Infos();
        bp3Infos1.setId(1L);
        Bp3Infos bp3Infos2 = new Bp3Infos();
        bp3Infos2.setId(bp3Infos1.getId());
        assertThat(bp3Infos1).isEqualTo(bp3Infos2);
        bp3Infos2.setId(2L);
        assertThat(bp3Infos1).isNotEqualTo(bp3Infos2);
        bp3Infos1.setId(null);
        assertThat(bp3Infos1).isNotEqualTo(bp3Infos2);
    }
}
