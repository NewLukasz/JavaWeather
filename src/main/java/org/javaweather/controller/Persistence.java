package org.javaweather.controller;

import java.io.*;

public class Persistence {
    private static final String CHOOSEN_CITIES_LOCATION = System.getProperty("user.home") + File.separator + "choosenCitiesWeatherApplication.ser";
    private String homeCity;
    private String vacationDestination;

    public Persistence() {
    }

    public void saveToPersistence(String homeLocation, String vacationDestination) {
        try {
            File file = new File(CHOOSEN_CITIES_LOCATION);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            String homeLocationModified = homeLocation.replace(" ", "+");
            String vacationDestinationModified = vacationDestination.replace(" ", "+");
            objectOutputStream.writeObject(homeLocationModified);
            objectOutputStream.writeObject(vacationDestinationModified);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkPersistenceAndLoadIfIsInUse() {
        try {
            FileInputStream fileInputStream = new FileInputStream(CHOOSEN_CITIES_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.homeCity = (String) objectInputStream.readObject();
            this.vacationDestination = (String) objectInputStream.readObject();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getHomeCity() {
        return this.homeCity;
    }

    public String getVacationDestination() {
        return this.vacationDestination;
    }
}
