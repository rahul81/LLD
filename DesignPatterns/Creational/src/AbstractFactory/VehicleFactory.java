package AbstractFactory;


public interface VehicleFactory {
    Vehicle createVehicle();
}

/*
 * Now in Factory method code if we needed to add more car brands it will require code change in the if else ladder
 * eventually it will grow with new types and look ugly.
 * To fix this problem Abstract factory is used
 */
// public class VehicleFactory {
    
//     public static Vehicle createVehicle(String vehicleBrand){

//         if(vehicleBrand == "Honda"){
//             return new Honda();
//         }else if (vehicleBrand == "Toyota"){
//             return new Totyota();
//         }
//         else if (vehicleBrand == "BMW") {
//             return new BMW();
//         }else{
//             throw new IllegalArgumentException("Unkown Vehicle Brand");
//         }
//     }
// }

