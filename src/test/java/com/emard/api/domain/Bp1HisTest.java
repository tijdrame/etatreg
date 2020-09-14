package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp1HisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp1His.class);
        Bp1His bp1His1 = new Bp1His();
        bp1His1.setId(1L);
        Bp1His bp1His2 = new Bp1His();
        bp1His2.setId(bp1His1.getId());
        assertThat(bp1His1).isEqualTo(bp1His2);
        bp1His2.setId(2L);
        assertThat(bp1His1).isNotEqualTo(bp1His2);
        bp1His1.setId(null);
        assertThat(bp1His1).isNotEqualTo(bp1His2);
    }
}
