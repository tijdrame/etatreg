package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class FilesInfosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FilesInfos.class);
        FilesInfos filesInfos1 = new FilesInfos();
        filesInfos1.setId(1L);
        FilesInfos filesInfos2 = new FilesInfos();
        filesInfos2.setId(filesInfos1.getId());
        assertThat(filesInfos1).isEqualTo(filesInfos2);
        filesInfos2.setId(2L);
        assertThat(filesInfos1).isNotEqualTo(filesInfos2);
        filesInfos1.setId(null);
        assertThat(filesInfos1).isNotEqualTo(filesInfos2);
    }
}
