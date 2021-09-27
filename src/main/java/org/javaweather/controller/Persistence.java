package org.javaweather.controller;

import java.io.*;

public class Persistence {
    private static final String CHOSEN_CITIES_LOCATION = System.getProperty("user.home") + File.separator;
    private String homeCity;
    private String vacationDestination;
    String locationOfFileWithCitiesWithName;

    public Persistence(String fileName) {
        locationOfFileWithCitiesWithName=CHOSEN_CITIES_LOCATION+fileName;
    }

    public void saveToPersistence(String homeLocation, String vacationDestination) {
        try {
            File file = new File(locationOfFileWithCitiesWithName);
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
            FileInputStream fileInputStream = new FileInputStream(locationOfFileWithCitiesWithName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.homeCity = (String) objectInputStream.readObject();
            this.vacationDestination = (String) objectInputStream.readObject();
            objectInputStream.close();
            return true;
        } catch (Exception e) {
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
