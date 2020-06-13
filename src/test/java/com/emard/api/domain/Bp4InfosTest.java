package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp4InfosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp4Infos.class);
        Bp4Infos bp4Infos1 = new Bp4Infos();
        bp4Infos1.setId(1L);
        Bp4Infos bp4Infos2 = new Bp4Infos();
        bp4Infos2.setId(bp4Infos1.getId());
        assertThat(bp4Infos1).isEqualTo(bp4Infos2);
        bp4Infos2.setId(2L);
        assertThat(bp4Infos1).isNotEqualTo(bp4Infos2);
        bp4Infos1.setId(null);
        assertThat(bp4Infos1).isNotEqualTo(bp4Infos2);
    }
}
