public class HourlyEmployee extends CompanyEmployee {
   private double hourlyRate;
   private double hoursWorked;
   public static final double MIN_HOURLY_RATE = 9.00;
   public static final double MIN_HOURS_WORKED = 0.0;
   public static final double MAX_HOURS_WORKED = 168.0;
   public static final double MIN_HOURS_FOR_OVERTIME = 40.0;
   public static final double OVERTIME_MULTIPLIER = 1.5;
  
   public HourlyEmployee(String firstName, String lastName, String idNumber) {
      super(firstName, lastName, idNumber); 
   }
   
   public double getHourlyRate() { return this.hourlyRate; }
   public double getHoursWorked() { return this.hoursWorked; }
   
   public void setHourlyRate(double hourlyRate) {
      if (hourlyRate < MIN_HOURLY_RATE) {
         throw new IllegalArgumentException(
            "Hourly Rate must be at least " + String.format("$%.2f", MIN_HOURLY_RATE));         
      }
      this.hourlyRate = hourlyRate;
   }
   
   public void setHoursWorked(double hoursWorked) {
      if (hoursWorked < MIN_HOURS_WORKED || hoursWorked > MAX_HOURS_WORKED) {
         throw new IllegalArgumentException(
            "Hours worked must be between "
            + String.format("%.1f", MIN_HOURS_WORKED)
            + " and " + String.format("%.1f", MAX_HOURS_WORKED));
      } 
      this.hoursWorked = hoursWorked;
   }
   
   public double calculateEarnings() {
      if (this.getHoursWorked() <= MIN_HOURS_FOR_OVERTIME) {
         return this.getHourlyRate() * this.getHoursWorked();
      }
      else {
         return (MIN_HOURS_WORKED * this.getHourlyRate())
            + ((this.getHoursWorked() - MIN_HOURS_FOR_OVERTIME)
                  * this.getHourlyRate() * OVERTIME_MULTIPLIER);
      }
   }
   public String toString() {
      return super.toString()
         + "\nHourly Rate: " + String.format("$%.2f", this.getHourlyRate())
         + "\nHours Worked: " + String.format("%.1f", this.getHoursWorked());
   }   
}