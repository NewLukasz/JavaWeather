package org.javaweather.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class PersistenceTest {

    @Test
    void shouldCreateFileWithTwoCitiesAfterSaveToPersistence() {
        //given
        String fileName = "chosenCitiesWeatherApplicationForTestPurposeOnly.ser";
        Persistence persistence = new Persistence(fileName);
        //when
        persistence.saveToPersistence("Tokyo", "Berlin");
        //then
        assertThat(persistence.checkPersistenceAndLoadIfIsInUse(), equalTo(true));
        assertThat(persistence.getHomeCity(), equalTo("Tokyo"));
        assertThat(persistence.getVacationDestination(), equalTo("Berlin"));
        deleteTestFile(fileName);
    }

    private void deleteTestFile(String fileName) {
        File file = new File(System.getProperty("user.home") + File.separator + fileName);
        file.delete();
    }
}