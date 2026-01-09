
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteStudent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Load MySQL driver and connect to the database
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE",
                    "system",
                    "system"
            );

            // Ask the user for the student ID
            System.out.print("Enter student ID to delete: ");
            int id = sc.nextInt();

            // Prepare the DELETE query
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute the query
            int rows = ps.executeUpdate();

            // Show the result
            if (rows > 0) {
                System.out.println("Student record deleted successfully.");
            } else {
                System.out.println("No student found with that ID.");
            }

            // Close everything
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
