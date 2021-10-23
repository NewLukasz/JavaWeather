package org.javaweather.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class PersistenceTest {

    private static String FILE_NAME = "chosenCitiesWeatherApplicationForTestPurposeOnly.ser";

    @AfterEach
    void cleanUp() {
        deleteTestFile(FILE_NAME);
    }

    @Test
    void shouldCreateFileWithTwoCitiesAfterSaveToPersistence() {
        //given
        Persistence persistence = new Persistence(FILE_NAME);
        //when
        persistence.saveToPersistence("Tokyo", "Berlin");
        //then
        assertThat(persistence.checkPersistenceAndLoadIfIsInUse(), equalTo(true));
        assertThat(persistence.getHomeCity(), equalTo("Tokyo"));
        assertThat(persistence.getVacationDestination(), equalTo("Berlin"));
    }

    private void deleteTestFile(String fileName) {
        File file = new File(System.getProperty("user.home") + File.separator + fileName);
        file.delete();
    }
}