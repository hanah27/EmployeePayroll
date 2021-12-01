public class SalariedEmployee extends CompanyEmployee {
   private double weeklySalary;
   public static final double MIN_SALARY = 0.00;
   
   public SalariedEmployee(String firstName, String lastName, String idNumber) {
      super(firstName, lastName, idNumber);
   }
   
   public double getWeeklySalary() { return this.weeklySalary; }
   
   public void setWeeklySalary(double salary) {
      if (salary < MIN_SALARY) {
         throw new IllegalArgumentException(
            "Salary must be at least " + String.format("$%.2f", MIN_SALARY));
      }
      this.weeklySalary = salary;
   }
   
   public double calculateEarnings() {
      return this.getWeeklySalary();
   }
   
   public String toString() {
      return super.toString();
   }
}
