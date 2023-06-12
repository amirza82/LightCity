package org.example;

import org.example.defualtSystem.Bank;
import org.example.defualtSystem.Life;
import org.example.models.*;
import org.example.models.Character;

import java.util.ArrayList;

public class Information {
    public static ArrayList<Industry> industrys = new ArrayList<>();
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Property> properties = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Job> jobs = new ArrayList<>();
    public static ArrayList<Life> lives = new ArrayList<>();

    public static Bank getBank(){
        for (Industry i:industrys) {
            if (i.getTitle().equals("Bank")){
                return (Bank) i;
            }
        }
        return null;
    }

    public static void plus(Object o) {
//        Takes an object and checks what it is. It belongs to any list, adds to that list
        if (o instanceof Industry) {
            ((Industry) o).setId((industrys.size() + 1));
            industrys.add((Industry) o);
        }
        if (o instanceof BankAccount) {
            ((BankAccount) o).setId(bankAccounts.size() + 1);
            serviceMens.add((ServiceMen) o);
        }
        if (o instanceof Doctor) {
            ((Doctor) o).setId(doctors.size() + 1);
            doctors.add((Doctor) o);
        }
        if (o instanceof Nurse) {
            ((Nurse) o).setId(nurses.size() + 1);
            nurses.add((Nurse) o);
        }
        if (o instanceof Visit) {
            ((Visit) o).setId(visits.size() + 1);
            visits.add((Visit) o);
            Patient p = (Patient) getByNationalID(((Visit) o).getPatientNationalID());
            p.patientVisits.add((Visit) o);
        }
    }

    public static void delete(Object o) {
//        Takes an object and checks what it is. It belongs to any list, remove from that list
        if (o instanceof Patient) {
            if (patients.contains(o)) {
                patients.remove((Patient) o);
                return;
            }
        } else if (o instanceof ServiceMen) {
            if (serviceMens.contains(o)) {
                serviceMens.remove((ServiceMen) o);
                return;
            }
        } else if (o instanceof Doctor) {
            if (doctors.contains(o)) {
                doctors.add((Doctor) o);
                return;
            }
        } else if (o instanceof Nurse) {
            if (nurses.contains(o)) {
                nurses.add((Nurse) o);
                return;
            }
        } else if (o instanceof Visit) {
            Visit visit = (Visit) o;
            boolean exist = false;
            Visit deletVisit = null;
            for (Visit v : visits) {
                if ((v.getPatientNationalID().equals(visit.getPatientNationalID())
                        && (v.getDoctorNationalID().equals(visit.getDoctorNationalID()))
                        && (v.getNurseNationalID().equals(visit.getNurseNationalID()))
                        && (v.getSickness().equals(visit.getSickness()))
                        && (v.getDate().equals(visit.getDate()))
                        && (v.getPrescription().equals(visit.getPrescription())))) {
                    deletVisit = v;
                    exist = true;
                    break;
                }
            }
            if (exist) {
                visits.remove(deletVisit);
                return;
            }
        }
        System.out.println("There is nothing to delete !");
    }
}
