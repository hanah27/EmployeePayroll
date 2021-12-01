import javax.swing.JOptionPane;
public class Payroll {
   public static void main(String[] args) {
      final int MAX_NUM_COMPANY_EMPLOYEES = 10;
      
      CompanyEmployee employees[] = new CompanyEmployee[MAX_NUM_COMPANY_EMPLOYEES];
      CompanyEmployee oneEmployee;

      do {
         try {
            oneEmployee = getNewEmployee();
            populateEmployee(oneEmployee);
            employees[CompanyEmployee.getNumEmployees() - 1] = oneEmployee;
         } catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Employee could not be created\n" + e.getMessage());
         }
      } while (JOptionPane.showConfirmDialog(null, "Would you like to enter another employee?", "Create Employee",
         JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
      
      printEmployeeReport(employees, CompanyEmployee.getNumEmployees());
   }
   
   public static void printEmployeeReport(CompanyEmployee[] emp, int numEmployees) {
      final double salariedCommissionEmployeeBonus = 0.10;   
      if (numEmployees > 0) {
         String report = "";

         double total = 0;
         for (int x = 0; x < numEmployees; x++) {
         
            // The report must take into account a 10% bonus for Salaried Commission Employees
            if (emp[x] instanceof SalariedCommissionEmployee) {
               SalariedCommissionEmployee sce = (SalariedCommissionEmployee) emp[x];
               double oldSalary = sce.getWeeklySalary();
               sce.setWeeklySalary(oldSalary * (1 + salariedCommissionEmployeeBonus));
            }
         
            total += emp[x].calculateEarnings();
            report += "[" + emp[x].getClass().getName() + "]\n" + emp[x].toString() + "\n"
                    + "Weekly Earnings: " + formatCurrency(emp[x].calculateEarnings()) + "\n\n";
         }
         
         report += "Total Payroll: " + formatCurrency(total);
         
         JOptionPane.showMessageDialog(null, report);
      }
      else {
         JOptionPane.showMessageDialog(null, "There are no employees!");
      }
   }
   
   public static CompanyEmployee getNewEmployee() {
      CompanyEmployee emp;

      Object[] options = {"Salaried Employee", "Hourly Employee", "Commission Employee", "Salaried Commission Employee" };   
      int employeeType = JOptionPane.showOptionDialog(null, "What type of employee are you entering?", "Create Employee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

      String firstName = answerString("Enter the employee's first name:");
      String lastName = answerString("Enter the employee's last name:");
      String idNumber = answerString("Enter the employee's ID Number:");

      switch (employeeType) {
         case 0: // Salaried Employee
            emp = new SalariedEmployee(firstName, lastName, idNumber);
            break;

         case 1: // Hourly Employee
            emp = new HourlyEmployee(firstName, lastName, idNumber);  
            break;

         case 2: // Commission Employee
            emp = new CommissionEmployee(firstName, lastName, idNumber);    
            break;

         case 3: // Salaried Commission Employee
            emp = new SalariedCommissionEmployee(firstName, lastName, idNumber);   
            break;
            
         default: // Using showOptionDialog, this case should never be hit
            emp = null;
            break;
      }
      
      return emp;
   }
   
   public static void populateEmployee(CompanyEmployee emp) {                  
      // Determine which additional questions to ask
      if (emp instanceof SalariedEmployee) {
         boolean salarySet = false;
         do {
            try {
               ((SalariedEmployee)emp).setWeeklySalary(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's weekly salary: "))); // downcast required to get to setWeeklySalary method
               salarySet = true;               
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, "Invalid salary! Please enter a value of at least " + formatDecimal(SalariedEmployee.MIN_SALARY, 2));
            }               
         } while (!salarySet);
      }

      if (emp instanceof HourlyEmployee) {
         boolean rateSet = false;         
         do {
            try {
               ((HourlyEmployee)emp).setHourlyRate(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's hourly rate: "))); // downcast required to get to setHourlyRate method
               rateSet = true;
            }
            catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Hourly rate must be entered as a number");
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }               
         } while (!rateSet); 
 
         boolean hoursSet = false;
         do {
            try {
               ((HourlyEmployee)emp).setHoursWorked(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's hours worked: "))); // downcast required to get to setHoursWorked method
               hoursSet = true;               
            }
            catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Employee's hours worked must be entered as a number");
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }                
         } while (!hoursSet);
      }

     if (emp instanceof SalariedCommissionEmployee) {
         boolean salarySet = false;
         do {
            try {
               ((SalariedCommissionEmployee)emp).setWeeklySalary(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's weekly salary: "))); // downcast required to get to setWeeklySalary method
               salarySet = true;
            }
            catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Salary must be entered as a number");
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }               
         } while (!salarySet);
      }
            
     // Note: Keep in mind a SalariedCommissionEmployee IS-A CommissionEmployee. This is why we can downcast to CommissionEmployee instead of SalariedCommissionEmployee
     if (emp instanceof CommissionEmployee || emp instanceof SalariedCommissionEmployee) {
         boolean salesSet = false;
         do {
            try {
               ((CommissionEmployee)emp).setGrossSales(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's gross sales"))); // downcast required to get to setGrossSales method
               salesSet = true;
            }
            catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Gross sales must be entered as a number");
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }             
         } while (!salesSet);                             

         boolean commissionSet = false;
         do {
         try {
               ((CommissionEmployee)emp).setCommissionRate(Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's commission rate: "))); // downcast required to get to setCommissionRate method
               commissionSet = true;
            }
            catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Commission rate must be entered as a number");
            }
            catch (IllegalArgumentException e) {
               JOptionPane.showMessageDialog(null, e.getMessage());
            }                  
         } while (!commissionSet); 
      }
   }
   
	public static String answerString(String prompt) {
      String answerString = "";
      
      do {
         answerString = JOptionPane.showInputDialog(prompt);
         if (answerString.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a value");
         }
      } while (answerString.trim().equals(""));
   
		return answerString;
	}
   
   public static String formatCurrency(double amount) {
      return String.format("$%.2f", amount);
   }
   
   public static String formatDecimal(double amount, int precision) {
      return String.format("%." + precision + "f", amount);
   }   
}