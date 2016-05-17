import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class Test {

    public static void main(String[] args) {

        MyHashMap<Integer,Person> myHashMap = new MyHashMap<Integer,Person>();
        Person p;
        boolean menuCheck = true;
        myHashMap = load();
        int uniqID = loadID();
        while(menuCheck) {
            showMenu();
            Scanner scanTool = new Scanner(System.in);
            int selectMenu = scanTool.nextInt();
            switch (selectMenu) {
                case 1:
                    Person[] results = new Person[uniqID];
                    int k=0;
                    System.out.println("---Search a person---");
                    System.out.println();
                    System.out.println("Please enter search key :");
                    String key = scanTool.next();
                    for (int i=0;i<uniqID;i++) {
                        if (myHashMap.get(i) != null) {
                            if(myHashMap.get(i).getName().equalsIgnoreCase(key) || myHashMap.get(i).getSurname().equalsIgnoreCase(key) || myHashMap.get(i).getAdress().equalsIgnoreCase(key)) {
                                results[k] = myHashMap.get(i);
                                k++;
                            }
                        }
                    }
                    int listSizeforPersonSearch = 0;
                    for(int l=0;l<results.length;l++) {
                        if(results[l] != null) {
                            listSizeforPersonSearch++;
                        }
                    }
                    if (results.length > 9) {
                        for (int j = 0; j < 9; j++) {
                            if (results[j] != null) {
                                System.out.println(j + 1 + "." + results[j]);
                            }
                        }
                    } else if (listSizeforPersonSearch ==  0) {
                        System.out.println("No results found.");
                        break;
                    } else {
                        for (int j = 0; j < uniqID; j++) {
                            if (results[j] != null) {
                                System.out.println(j + 1 + "." + results[j]);
                            }
                        }
                    }
                    System.out.println();
                    System.out.println("Please enter number to see record :");
                    Integer chooseforPerson = scanTool.nextInt();
                    traverseMenu(myHashMap,results,chooseforPerson-1,uniqID);
                    break;
                case 2:
                    Person[] resultsforPhones = new Person[uniqID];
                    int r = 0;
                    System.out.println("---Search a phone number---");
                    System.out.println();
                    System.out.println("Please enter search key :");
                    String keyPhoneNumber = scanTool.next();
                    for (int i=0;i<uniqID;i++) {
                        if (myHashMap.get(i) != null) {
                            for (int j = 0; j < 3; j++) {
                                if ((myHashMap.get(i).getPhoneNumber()[j].getPhoneNumber().equals(keyPhoneNumber))) {
                                    resultsforPhones[r] = myHashMap.get(i);
                                    r++;
                                    break;
                                }
                            }
                        }
                    }
                    int listSizeforPhoneSearch = 0;
                    for(int l=0;l<resultsforPhones.length;l++) {
                        if(resultsforPhones[l] != null) {
                            listSizeforPhoneSearch++;
                        }
                    }
                    if (resultsforPhones.length > 9) {
                        for (int j = 0; j < 9; j++) {
                            if (resultsforPhones[j] != null) {
                                    System.out.println(j + 1 + "." + resultsforPhones[j]);
                            }
                        }
                    } else if (listSizeforPhoneSearch == 0) {
                        System.out.println("No results found.");
                        break;
                    } else {
                        for (int j = 0; j < uniqID; j++) {
                            if (resultsforPhones[j] != null) {
                                    System.out.println(j + 1 + "." + resultsforPhones[j]);
                            }
                        }
                    }
                    System.out.println();
                    System.out.println("Please enter number to see record :");
                    Integer chooseforPersonPhone = scanTool.nextInt();
                    traverseMenu(myHashMap,resultsforPhones,chooseforPersonPhone-1,uniqID);
                    break;
                case 3:
                    System.out.println("---Create a new person---");
                    System.out.println();
                    System.out.println("Enter the name :");
                    String name = scanTool.next();
                    System.out.println("Enter the surname :");
                    String surname = scanTool.next();
                    System.out.println("Enter the adress :");
                    String adress = scanTool.next();
                    p = new Person(name,surname,adress,uniqID);
                    boolean phoneCheck = true;
                    int checkerPhone = 0;
                    while (phoneCheck) {
                        int control = 0;
                        if (checkerPhone == 3) {
                            myHashMap.put(uniqID, p);
                            uniqID++;
                            phoneCheck = false;
                        } else {
                            System.out.println("Do you want to add phone numbers ? (Y/N)");
                            String selectPhoneNumber = scanTool.next();
                            if (selectPhoneNumber.equalsIgnoreCase("Y")) {
                                System.out.println("[1] Work - [2] Home - [3] Cell");
                                int tControl = scanTool.nextInt();
                                System.out.println("Enter the phone number :");
                                String phoneNumber = scanTool.next();
                                switch (tControl) {
                                    case 1:
                                        PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber);
                                        if( phoneNumberChecker(checkPhoneNumber,myHashMap,uniqID) == true) {
                                            for(int j=0;j<3;j++) {
                                                if(p.getPhoneNumber()[j].getPhoneNumber().equalsIgnoreCase(checkPhoneNumber.getPhoneNumber()) &&
                                                        p.getPhoneNumber()[j].getPhoneType().equalsIgnoreCase(checkPhoneNumber.getPhoneType())) {
                                                    control =1;
                                                }
                                            }
                                            if(control==0) {
                                                p.setPhoneNumber("Work", phoneNumber);
                                                myHashMap.put(uniqID,p);
                                                checkerPhone++;
                                            } else {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                        } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,uniqID) == false) {
                                            System.out.println("This phone number already take.");
                                            System.out.println();
                                        }
                                        break;
                                    case 2:
                                        PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber);
                                        if( phoneNumberChecker(checkPhoneNumber2,myHashMap,uniqID) == true) {
                                            for(int j=0;j<3;j++) {
                                                if(p.getPhoneNumber()[j].getPhoneNumber().equalsIgnoreCase(checkPhoneNumber2.getPhoneNumber()) &&
                                                        p.getPhoneNumber()[j].getPhoneType().equalsIgnoreCase(checkPhoneNumber2.getPhoneType())) {
                                                    control =1;
                                                }
                                            }
                                            if(control == 0) {
                                                p.setPhoneNumber("Home", phoneNumber);
                                                myHashMap.put(uniqID,p);
                                                checkerPhone++;
                                            } else {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                        } else if( phoneNumberChecker(checkPhoneNumber2,myHashMap,uniqID) == false) {
                                            System.out.println("This phone number already take.");
                                            System.out.println();
                                        }
                                        break;
                                    case 3:
                                        PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber);
                                        if( phoneNumberChecker(checkPhoneNumber3,myHashMap,uniqID) == true) {
                                            for(int j=0;j<3;j++) {
                                                if(p.getPhoneNumber()[j].getPhoneNumber().equalsIgnoreCase(checkPhoneNumber3.getPhoneNumber()) &&
                                                        p.getPhoneNumber()[j].getPhoneType().equalsIgnoreCase(checkPhoneNumber3.getPhoneType())) {
                                                    control =1;
                                                }
                                            }
                                            if(control == 0) {
                                                p.setPhoneNumber("Cell", phoneNumber);
                                                myHashMap.put(uniqID,p);
                                                checkerPhone++;
                                            } else {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                        } else if( phoneNumberChecker(checkPhoneNumber3,myHashMap,uniqID) == false) {
                                            System.out.println("This phone number already take.");
                                            System.out.println();
                                        }
                                        break;
                                    default:
                                        System.out.println("Something went wrong.");
                                        break;
                                }
                            } else if (selectPhoneNumber.equalsIgnoreCase("N")) {
                                myHashMap.put(uniqID,p);
                                uniqID++;
                                phoneCheck = false;
                            }
                        }
                    }
                    break;
                case 4:
                    Person[] allResults = new Person[uniqID];
                    int u = 0;
                    for (int i=0;i<uniqID;i++) {
                        if (myHashMap.get(i) != null) {
                           allResults[u] = myHashMap.get(i);
                           u++;
                        }
                    }
                    if (allResults.length == 0) {
                        System.out.println("No record found.");
                    } else {
                        Integer listSize = 0;
                        for(int o=0;o<allResults.length;o++){
                            if(allResults[o] != null) {
                                listSize++;
                            }
                        }
                        quickSort(allResults,0,listSize-1);
                        if (listSize != 0) {
                            for (int j = 0; j < uniqID; j++) {
                                if (allResults[j] != null) {
                                    System.out.println(j + 1 + "." + allResults[j]);
                                }
                            }
                            System.out.println();
                            System.out.println("Please enter number to see record :");
                            Integer chooseforAllPerson = scanTool.nextInt();
                            traverseMenu(myHashMap,allResults,chooseforAllPerson-1,uniqID);
                        } else {
                            System.out.println("No record found.");
                        }

                    }
                    break;
                case 5:
                    System.out.println("Do you want to exit ? (Y/N)");
                    String selectExit = scanTool.next();
                    if (selectExit.equalsIgnoreCase("Y")) {
                        save(myHashMap,uniqID);
                        saveID(uniqID);
                        menuCheck = false;
                    }
                    break;
                default:
                    System.out.println("You must enter number between 1-5.");
                    break;
            }
        }
    }

    public static void showMenu() {
        System.out.println();
        System.out.println();
        System.out.println("1.Search a person");
        System.out.println("2.Search a phone number");
        System.out.println("3.Create a new person");
        System.out.println("4.List all");
        System.out.println("5.Exit");
    }

    public static void traverseMenu(MyHashMap<Integer,Person> myHashMap,Person p[],int i,int id) {
        int c = 0;
        int listSize = 0;
        Scanner scanforTraverse = new Scanner(System.in);
        for(int k=0;k<p.length;k++){
            if(p[k] != null) {
                listSize++;
            }
        }
        while (p != null && c != 6) {
            int counterP = 0;
            System.out.println();
            System.out.println("\t<--- " + p[i].getName() + " " + p[i].getSurname() + " " + p[i].getAdress() + " --->");
            System.out.println();
            for (int j = 0; j < 3; j++) {
                if (!p[i].getPhoneNumber()[j].getPhoneNumber().equalsIgnoreCase("") && !p[i].getPhoneNumber()[j].getPhoneType().equalsIgnoreCase("")) {
                    System.out.println("         " + p[i].getPhoneNumber()[j] + "       ");
                    counterP++;
                }
            }
            System.out.println();
            System.out.println();
            System.out.println("[1] Move Right - [2] Move Left - [3] Add New Phone Number - [4] Delete - [5] Upload Person Information - [6] Menu");
            c = scanforTraverse.nextInt();
            switch (c) {
                case 1:
                    if (i > 0) {
                        i--;
                    }
                    break;
                case 2:
                    if (i < listSize - 1) {
                        i++;
                    }
                    break;
                case 3:
                    if (counterP < 3) {
                        System.out.println("Do you want to add phone numbers ? (Y/N)");
                        String selectPhoneNumber = scanforTraverse.next();
                        if (selectPhoneNumber.equalsIgnoreCase("Y")) {
                            System.out.println("[1] Work - [2] Home - [3] Cell");
                            int tControl = scanforTraverse.nextInt();
                            System.out.println("Enter the phone number :");
                            String phoneNumber = scanforTraverse.next();
                            switch (tControl) {
                                case 1:
                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber);
                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                        p[i].setPhoneNumber("Work", phoneNumber);
                                        myHashMap.put(p[i].getKey(), p[i]);
                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                        System.out.println("This phone number already take.");
                                        System.out.println();
                                    }
                                    break;
                                case 2:
                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber);
                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                        p[i].setPhoneNumber("Home", phoneNumber);
                                        myHashMap.put(p[i].getKey(), p[i]);
                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                        System.out.println("This phone number already take.");
                                        System.out.println();
                                    }
                                    break;
                                case 3:
                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber);
                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                        p[i].setPhoneNumber("Cell", phoneNumber);
                                        myHashMap.put(p[i].getKey(), p[i]);
                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                        System.out.println("This phone number already take.");
                                        System.out.println();
                                    }
                                    break;
                                default:
                                    System.out.println("You must select between 1-3.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("You can not add phone numbers up to 3.");
                        System.out.println();
                    }
                    break;
                case 4:
                    myHashMap.remove(p[i].getKey());
                    c=6;
                    break;
                case 5:
                    System.out.println("[1] Name - [2] Surname - [3] Adress - [4] Phone Numbers");
                    int updateC = scanforTraverse.nextInt();
                    switch (updateC) {
                        case 1 :
                            System.out.println("Please enter the new name :");
                            String newName = scanforTraverse.next();
                            p[i].setName(newName);
                            myHashMap.put(p[i].getKey(),p[i]);
                            break;
                        case 2 :
                            System.out.println("Please enter the new surname :");
                            String newSurname = scanforTraverse.next();
                            p[i].setSurname(newSurname);
                            myHashMap.put(p[i].getKey(),p[i]);
                            break;
                        case 3 :
                            System.out.println("Please enter the new adress :");
                            String newAdress = scanforTraverse.next();
                            p[i].setAdress(newAdress);
                            myHashMap.put(p[i].getKey(),p[i]);
                            break;
                        case 4 :
                            if (counterP == 0) {
                                System.out.println("No phone numbers found to update.");
                            } else if (counterP == 1) {
                                System.out.println("One phone numbers found to update. Do you want to update ? (Y/N)");
                                String answer = scanforTraverse.next();
                                if(answer.equalsIgnoreCase("Y")) {
                                    System.out.println("[1] Work - [2] Home - [3] Cell");
                                    int tControl = scanforTraverse.nextInt();
                                    System.out.println("Enter the phone number :");
                                    String phoneNumber = scanforTraverse.next();
                                    switch (tControl) {
                                        case 1:
                                            PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber);
                                            if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                p[i].getPhoneNumber()[0].setPhoneNumber("Work",phoneNumber);
                                                myHashMap.put(p[i].getKey(), p[i]);
                                            } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                            break;
                                        case 2:
                                            PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber);
                                            if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                p[i].getPhoneNumber()[0].setPhoneNumber("Home",phoneNumber);
                                                myHashMap.put(p[i].getKey(), p[i]);
                                            } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                            break;
                                        case 3:
                                            PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber);
                                            if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                p[i].getPhoneNumber()[0].setPhoneNumber("Cell",phoneNumber);
                                                myHashMap.put(p[i].getKey(), p[i]);
                                            } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                System.out.println("This phone number already take.");
                                                System.out.println();
                                            }
                                            break;
                                        default:
                                            System.out.println("You must select between 1-3.");
                                            break;
                                    }
                                }
                            } else if (counterP == 2) {
                                System.out.println("Two phone numbers found to update. Do you want to update phone numbers ? (Y/N)");
                                String answer = scanforTraverse.next();
                                if(answer.equalsIgnoreCase("Y")) {
                                    System.out.println("[1] First Number - [2] Second Number");
                                    int choose = scanforTraverse.nextInt();
                                    switch (choose) {
                                        case 1 :
                                            System.out.println("[1] Work - [2] Home - [3] Cell");
                                            int tControl = scanforTraverse.nextInt();
                                            System.out.println("Enter the phone number :");
                                            String phoneNumber = scanforTraverse.next();
                                            switch (tControl) {
                                                case 1:
                                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Work",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 2:
                                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Home",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 3:
                                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Cell",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("You must select between 1-3.");
                                                    break;
                                            }
                                            break;
                                        case 2 :
                                            System.out.println("[1] Work - [2] Home - [3] Cell");
                                            int tControl1 = scanforTraverse.nextInt();
                                            System.out.println("Enter the phone number :");
                                            String phoneNumber1 = scanforTraverse.next();
                                            switch (tControl1) {
                                                case 1:
                                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Work",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 2:
                                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Home",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 3:
                                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Cell",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("You must select between 1-3.");
                                                    break;
                                            }
                                            break;
                                        default:
                                            System.out.println("You must enter number between 1-2.");
                                            break;
                                    }
                                }
                            } else if (counterP == 3) {
                                System.out.println("Three phone numbers found to update. Do you want to update phone numbers ? (Y/N)");
                                String answer = scanforTraverse.next();
                                if (answer.equalsIgnoreCase("Y")) {
                                    System.out.println("[1] First Number - [2] Second Number - [3] Third Number");
                                    int choose = scanforTraverse.nextInt();
                                    switch (choose) {
                                        case 1:
                                            System.out.println("[1] Work - [2] Home - [3] Cell");
                                            int tControl = scanforTraverse.nextInt();
                                            System.out.println("Enter the phone number :");
                                            String phoneNumber = scanforTraverse.next();
                                            switch (tControl) {
                                                case 1:
                                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Work",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 2:
                                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Home",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 3:
                                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber);
                                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[0].setPhoneNumber("Cell",phoneNumber);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("You must select between 1-3.");
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            System.out.println("[1] Work - [2] Home - [3] Cell");
                                            int tControl1 = scanforTraverse.nextInt();
                                            System.out.println("Enter the phone number :");
                                            String phoneNumber1 = scanforTraverse.next();
                                            switch (tControl1) {
                                                case 1:
                                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Work",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 2:
                                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Home",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 3:
                                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber1);
                                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[1].setPhoneNumber("Cell",phoneNumber1);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("You must select between 1-3.");
                                                    break;
                                            }
                                            break;
                                        case 3:
                                            System.out.println("[1] Work - [2] Home - [3] Cell");
                                            int tControl2 = scanforTraverse.nextInt();
                                            System.out.println("Enter the phone number :");
                                            String phoneNumber2 = scanforTraverse.next();
                                            switch (tControl2) {
                                                case 1:
                                                    PhoneNumber checkPhoneNumber = new PhoneNumber("Work", phoneNumber2);
                                                    if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[2].setPhoneNumber("Work",phoneNumber2);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 2:
                                                    PhoneNumber checkPhoneNumber2 = new PhoneNumber("Home", phoneNumber2);
                                                    if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[2].setPhoneNumber("Home",phoneNumber2);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber2,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                case 3:
                                                    PhoneNumber checkPhoneNumber3 = new PhoneNumber("Cell", phoneNumber2);
                                                    if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == true) {
                                                        p[i].getPhoneNumber()[2].setPhoneNumber("Cell",phoneNumber2);
                                                        myHashMap.put(p[i].getKey(), p[i]);
                                                    } else if(phoneNumberChecker(checkPhoneNumber3,myHashMap,id) == false) {
                                                        System.out.println("This phone number already take.");
                                                        System.out.println();
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("You must select between 1-3.");
                                                    break;
                                            }
                                            break;
                                        default:
                                            System.out.println("You must enter number between 1-3.");
                                            break;
                                    }
                                }
                            }
                    }
                    break;
                case 6:
                    c=6;
                    break;
                default:
                    System.out.println("You must enter number between 1-6.");
                    break;
            }
        }
    }

    public static boolean phoneNumberChecker(PhoneNumber checkPhone,MyHashMap<Integer,Person> myHashMap,int id) {
        int counterOfSize = 0;
        int r = 0;
        for(int numberOfIndex=0;numberOfIndex<id;numberOfIndex++) {
            for(int b=0;b<3;b++) {
                if (myHashMap.get(numberOfIndex) != null) {
                    if (myHashMap.get(numberOfIndex).getPhoneNumber()[b] != null) {
                        counterOfSize++;
                    }
                }
            }
        }
        PhoneNumber[] checkPhoneNumberList = new PhoneNumber[counterOfSize];
        int c = 0;
        for(int numberOfIndex=0;numberOfIndex<id;numberOfIndex++) {
                for(int b = 0 ; b<3;b++) {
                    if (myHashMap.get(numberOfIndex) != null) {
                        if (myHashMap.get(numberOfIndex).getPhoneNumber()[b] != null) {
                        checkPhoneNumberList[c] = myHashMap.get(numberOfIndex).getPhoneNumber()[b];
                        c++;
                    }
                }
            }
        }
        for(int h = 0 ; h<checkPhoneNumberList.length;h++) {
            if(checkPhoneNumberList[h] != null) {
                if (checkPhoneNumberList[h].getPhoneNumber().equalsIgnoreCase(checkPhone.getPhoneNumber()) && checkPhoneNumberList[h].getPhoneType().equals(checkPhone.getPhoneType())) {
                    r = 1;
                }
            }
        }
        if(r == 1) {
            return false;
        } else {
            return true;
        }
    }

    public static MyHashMap<Integer,Person> load()
    {
        MyHashMap<Integer,Person> myHashMap2 = new MyHashMap<Integer,Person>();
        String file = "database/phonebook.txt";
        String type, number;
        try {
            LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(file)));
            lnr.skip(Long.MAX_VALUE);

            Person[] tempPerson = new Person[lnr.getLineNumber() + 1];

            File openFile = new File(file);
            Scanner inFile = new Scanner(openFile);

            while(inFile.hasNextLine())
            {
                for(int i = 0; i < lnr.getLineNumber() + 1; i++)
                {
                    StringTokenizer tokens = new StringTokenizer(inFile.next(), ";");
                    tempPerson[i] = new Person();
                    tempPerson[i].setKey(Integer.parseInt(tokens.nextToken()));
                    tempPerson[i].setName(tokens.nextToken());
                    tempPerson[i].setSurname(tokens.nextToken());
                    tempPerson[i].setAdress(tokens.nextToken());
                    while(tokens.hasMoreTokens()) {
                        type = tokens.nextToken();
                        number = tokens.nextToken();
                        tempPerson[i].setPhoneNumber(type, number);
                    }
                    myHashMap2.put(tempPerson[i].getKey(), tempPerson[i]);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found.");

        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            System.out.println("Unsupported encoding.");

        } catch (IOException ioException) {
            System.out.println("Something went wrong.");
        }

        finally {
            return myHashMap2;
        }
    }

    public static int loadID() {
        String  file = "database/id.txt";
        Integer key = 0;
        try {

            File openFile = new File(file);
            Scanner inFile = new Scanner(openFile);
            key= inFile.nextInt();

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found.");

        } 

        finally {
        }
        return key;
    }

    public static void save(MyHashMap<Integer,Person> myHashMap,int id)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("database/phonebook.txt","UTF-8");
            for(int i = 0; i < id; i++) {
                if (myHashMap.get(i) != null) {
                    printWriter.print(myHashMap.get(i).getKey() + ";" + myHashMap.get(i).getName() + ";" + myHashMap.get(i).getSurname() + ";"
                            + myHashMap.get(i).getAdress() + ";" + ((myHashMap.get(i).getPhoneNumber())[0]).getPhoneType() + ";"
                            + (myHashMap.get(i).getPhoneNumber()[0]).getPhoneNumber() + ";" + ((myHashMap.get(i).getPhoneNumber())[1]).getPhoneType()
                            + ";" + (myHashMap.get(i).getPhoneNumber()[1]).getPhoneNumber() + ";" + ((myHashMap.get(i).getPhoneNumber())[2]).getPhoneType()
                            + ";" + (myHashMap.get(i).getPhoneNumber()[2]).getPhoneNumber() + ";");
                    printWriter.println();
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    public static void saveID(int id) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("database/id.txt","UTF-8");
            printWriter.print(id);
    } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
        unsupportedEncodingException.printStackTrace();
    } finally {
            printWriter.close();
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] array, int first, int last) {
        int pivotIndex;
        if (first < last) {
            pivotIndex = partition(array, first, last);
            quickSort(array, first, pivotIndex-1);
            quickSort(array, pivotIndex+1, last);
        }
    }

    public static <T extends Comparable<? super T>> int partition(T[] array, int first, int last) {
        T tempItem;
        choosePivot(array, first, last);
        T pivot = array[first];
        int lastS1 = first;
        for (int firstUnsorted = first + 1; firstUnsorted <= last; ++firstUnsorted) {
            if (array[firstUnsorted].compareTo(pivot) < 0) {
                ++lastS1;
                tempItem = array[firstUnsorted];
                array[firstUnsorted] = array[lastS1];
                array[lastS1] = tempItem;
            }
        }
        tempItem = array[first];
        array[first] = array[lastS1];
        array[lastS1] = tempItem;
        return lastS1;
    }

    public static <T extends Comparable<? super T>> void choosePivot(T[] array, int first, int last) {
        int middle = (first+last) / 2;
        T tempItem = array[first];
        array[first] = array[middle];
        array[middle] = tempItem;
    }
}
