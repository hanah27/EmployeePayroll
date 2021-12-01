public abstract class CompanyEmployee {
   private String firstName;
   private String lastName;
   private String idNumber;
   private static int numEmployees;
   
   public CompanyEmployee(String firstName, String lastName, String idNumber) {
      if (firstName == null || firstName.equals("")) {
         throw new IllegalArgumentException("First name must be provided");
      }
      if (lastName == null || lastName.equals("")) {
         throw new IllegalArgumentException("Last name must be provided");
      }
      if (idNumber == null || idNumber.equals("")) {
         throw new IllegalArgumentException("ID number must be provided");
      }          
      this.firstName = firstName;
      this.lastName = lastName;
      this.idNumber = idNumber;
      ++numEmployees;
   }
   
   public String getFirstName() { return this.firstName; }
   public String getLastName() { return this.lastName; }
   public String getIdNumber() { return this.idNumber; }
   public static int getNumEmployees() { return numEmployees; }
   
   public void setFirstName(String firstName) {
      if (firstName == null || firstName.equals("")) {
         throw new IllegalArgumentException("First name must be provided");
      }
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      if (lastName == null || lastName.equals("")) {
         throw new IllegalArgumentException("Last name must be provided");
      }
      this.lastName = lastName;
   }
   
   public void setIdNumber(String idNumber) {
      if (idNumber == null || idNumber.equals("")) {
         throw new IllegalArgumentException("ID number must be provided");
      }  
      this.idNumber = idNumber;
   }
   
   public abstract double calculateEarnings();
   
   public String toString() {
      return this.getLastName() + ", "
           + this.getFirstName() + " (" + this.getIdNumber() + ")";
   }
}
