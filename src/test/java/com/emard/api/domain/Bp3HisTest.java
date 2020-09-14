package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp3HisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp3His.class);
        Bp3His bp3His1 = new Bp3His();
        bp3His1.setId(1L);
        Bp3His bp3His2 = new Bp3His();
        bp3His2.setId(bp3His1.getId());
        assertThat(bp3His1).isEqualTo(bp3His2);
        bp3His2.setId(2L);
        assertThat(bp3His1).isNotEqualTo(bp3His2);
        bp3His1.setId(null);
        assertThat(bp3His1).isNotEqualTo(bp3His2);
    }
}
