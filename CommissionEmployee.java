public class CommissionEmployee extends CompanyEmployee {
   private double grossSales;
   private double commissionRate;
   public static final double MIN_GROSS_SALES = 0.00;
   public static final double MIN_COMMISSION_RATE = 0.0;
   public static final double MAX_COMMISSION_RATE = 1.0;
   
   public CommissionEmployee(String firstName, String lastName, String idNumber) {
      super(firstName, lastName, idNumber);
   }
   
   public double getGrossSales() { return this.grossSales; }
   public double getCommissionRate() { return this.commissionRate; }
   
   public void setGrossSales(double grossSales) {
      if (grossSales < MIN_GROSS_SALES) {
         throw new IllegalArgumentException(
            "Gross sales must be at least " + String.format("$%.2f", MIN_GROSS_SALES));
      }
      this.grossSales = grossSales;
   }
   
   public void setCommissionRate(double commissionRate) {
      if (commissionRate < MIN_COMMISSION_RATE || commissionRate > MAX_COMMISSION_RATE) {
         throw new IllegalArgumentException(
            "Commission rate must be between "
            + String.format("%.1f", MIN_COMMISSION_RATE)
            + " and " + String.format("%.1f", MAX_COMMISSION_RATE));
      }
      this.commissionRate = commissionRate;
   }
   
   public double calculateEarnings() {
      return this.getCommissionRate() * this.getGrossSales();
   }

   public String toString() {
      return super.toString()
         + "\nGross Sales: " + String.format("$%.2f", this.getGrossSales())
         + "\nCommission Rate: " + String.format("%.1f", this.getCommissionRate() * 100) + "%";
   }     
}
