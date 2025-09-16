// package com.example;

// import java.util.List;
// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         AddVehicle add = new AddVehicle();
//         ListVehicle list = new ListVehicle();
//         RentVehicle rent = new RentVehicle();
//         ReturnVehicle ret = new ReturnVehicle();
//         DeleteVehicle delete = new DeleteVehicle();

//         int choice = 0;
//         while (choice != 6) {
//             System.out.println("\n--- Vehicle Rental System (Console) ---");
//             System.out.println("1. Add Vehicle");
//             System.out.println("2. List Vehicles");
//             System.out.println("3. Rent Vehicle");
//             System.out.println("4. Return Vehicle");
//             System.out.println("5. Delete Vehicle");
//             System.out.println("6. Exit");
//             System.out.print("Enter choice: ");
//             choice = sc.nextInt();
//             sc.nextLine(); // consume newline

//             switch (choice) {
//                 case 1:
//                     System.out.print("Vehicle Name: ");
//                     String name = sc.nextLine();
//                     System.out.print("Vehicle Type: ");
//                     String type = sc.nextLine();
//                     System.out.print("Rental Price: ");
//                     double price = sc.nextDouble();
//                     add.add(name, type, price);
//                     break;
//                 case 2:
//                     List<Vehicle> vehicles = list.listAll();
//                     if (vehicles.isEmpty()) System.out.println("No vehicles found.");
//                     else vehicles.forEach(System.out::println);
//                     break;
//                 case 3:
//                     System.out.print("Vehicle ID to Rent: ");
//                     rent.rent(sc.nextInt());
//                     break;
//                 case 4:
//                     System.out.print("Vehicle ID to Return: ");
//                     ret.returnVehicle(sc.nextInt());
//                     break;
//                 case 5:
//                     System.out.print("Vehicle ID to Delete: ");
//                     delete.delete(sc.nextInt());
//                     break;
//                 case 6:
//                     System.out.println("Exiting...");
//                     break;
//                 default:
//                     System.out.println("Invalid choice!");
//             }
//         }
//         sc.close();
//     }
// }





package com.example;

import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.service.VehicleService;
import com.example.vehiclerental.VehicleRentalSystemApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Start Spring Boot application context so we can use Service + Repository
        ApplicationContext context = SpringApplication.run(VehicleRentalSystemApplication.class, args);

        VehicleService service = context.getBean(VehicleService.class);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("\n--- Vehicle Rental System (Console + DB) ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. List Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Vehicle Name: ");
                    String name = sc.nextLine();
                    System.out.print("Vehicle Type: ");
                    String type = sc.nextLine();
                    Vehicle v = new Vehicle();
                    v.setModel(name);
                    v.setType(type);
                    v.setRented(false);
                    service.addVehicle(v);
                    System.out.println("✅ Vehicle added: " + name);
                    break;

                case 2:
                    List<Vehicle> vehicles = service.listVehicles();
                    if (vehicles.isEmpty()) {
                        System.out.println("No vehicles found.");
                    } else {
                        vehicles.forEach(vehicle ->
                            System.out.println(vehicle.getId() + " | " +
                                               vehicle.getModel() + " (" + vehicle.getType() + 
                                               ") [Rented: " + vehicle.isRented() + "]"));
                    }
                    break;

                case 3:
                    System.out.print("Vehicle ID to Rent: ");
                    long rentId = sc.nextLong();
                    try {
                        Vehicle rented = service.rentVehicle(rentId);
                        System.out.println("🚙 Rented: " + rented.getModel());
                    } catch (Exception e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Vehicle ID to Return: ");
                    long retId = sc.nextLong();
                    Vehicle returned = service.returnVehicle(retId);
                    if (returned != null) {
                        System.out.println("🔄 Returned: " + returned.getModel());
                    } else {
                        System.out.println("❌ Vehicle not found.");
                    }
                    break;

                case 5:
                    System.out.print("Vehicle ID to Delete: ");
                    long delId = sc.nextLong();
                    try {
                        service.deleteVehicle(delId);
                        System.out.println("❌ Deleted vehicle with ID: " + delId);
                    } catch (Exception e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    SpringApplication.exit(context);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
