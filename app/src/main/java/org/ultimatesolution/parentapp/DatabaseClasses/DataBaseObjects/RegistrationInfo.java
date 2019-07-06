package org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects;

import java.util.List;

public class RegistrationInfo {

    public String RegistrationID;
    public String PersonName;
    public String PhoneMobileNumber;
    public String EmailID;
    public String PIN;
    public String PERSONPICTURE;
    public String Department;
    public ClassesAndSectionsInfo ClassTeacherOf;
    public List<ClassSubjectsInfo> Teaches;
    public String ProfilePic;
    public String SchoolKey;
    public String classdesc;
    public String sectiondesc;
    public String rolls;
    public String RollNumber;

    public RegistrationInfo(String PersonName, String PhoneMobileNumber,
                            String EmailID,
                            String classdesc,
                            String sectiondesc,
                            String SchoolKey,
                            String RollNumber


    ) {
        this.PersonName = PersonName;
        this.PhoneMobileNumber = PhoneMobileNumber;
        this.EmailID = EmailID;
        this.classdesc = classdesc;
        this.sectiondesc = sectiondesc;
        this.SchoolKey = SchoolKey;
        this.RollNumber = RollNumber;
    }

    public String getRegistrationID() {
        return RegistrationID;
    }

    public void setRegistrationID(String registrationID) {
        RegistrationID = registrationID;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPhoneMobileNumber() {
        return PhoneMobileNumber;
    }

    public void setPhoneMobileNumber(String phoneMobileNumber) {
        PhoneMobileNumber = phoneMobileNumber;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getPERSONPICTURE() {
        return PERSONPICTURE;
    }

    public void setPERSONPICTURE(String PERSONPICTURE) {
        this.PERSONPICTURE = PERSONPICTURE;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public ClassesAndSectionsInfo getClassTeacherOf() {
        return ClassTeacherOf;
    }

    public void setClassTeacherOf(ClassesAndSectionsInfo classTeacherOf) {
        ClassTeacherOf = classTeacherOf;
    }

    public List<ClassSubjectsInfo> getTeaches() {
        return Teaches;
    }

    public void setTeaches(List<ClassSubjectsInfo> teaches) {
        Teaches = teaches;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getSchoolKey() {
        return SchoolKey;
    }

    public void setSchoolKey(String schoolKey) {
        SchoolKey = schoolKey;
    }

    public String getClassdesc() {
        return classdesc;
    }

    public void setClassdesc(String classdesc) {
        this.classdesc = classdesc;
    }

    public String getSectiondesc() {
        return sectiondesc;
    }

    public void setSectiondesc(String sectiondesc) {
        this.sectiondesc = sectiondesc;
    }

    public String getRolls() {
        return rolls;
    }

    public void setRolls(String rolls) {
        this.rolls = rolls;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }
}
