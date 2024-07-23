package com.softwaredevs.proyecto;

import com.softwaredevs.proyecto.entities.Employee;
import com.softwaredevs.proyecto.entities.Enterprise;
import com.softwaredevs.proyecto.entities.Enum_RoleName;
import com.softwaredevs.proyecto.entities.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
		/*Enterprise empresa1 = new Enterprise("Alqueria","8300769803-2","3176253465","calle 45 #20-30");
		System.out.println("<-------Creacion empresa 1--------->");
		System.out.println(empresa1.toString());
		empresa1.setName("Algarra");
		empresa1.setDocument("93078494-2");
		empresa1.setAddress("Carrera 20 #30-20");
		empresa1.setPhone("6015203024");
		System.out.println("<-------Modificacion empresa 1--------->");
		System.out.println(empresa1.toString());

		//--------- PRUEBA Employee----------//

		Employee empleado1 = new Employee("Aldair","Gmail", Enum_RoleName.ADMIN,empresa1);
		System.out.println("--------- PRUEBA Employee----------");
		System.out.println(empleado1.toString());
		Enterprise empresa2 = new Enterprise("Ford","10023","310752","34 - 45");
		empleado1.setName("Chris Evans");
		empleado1.setEmail("Hotmail");
		empleado1.setEnterprise(empresa2);
		empleado1.setRole(Enum_RoleName.OPERARIO);
		System.out.println("\n -------Modificacion-----");
		System.out.println(empleado1.toString());

        //TRANSACTION

        Transaction transaccion = new Transaction ("Abono cuenta",2500.0f,empleado1);

        System.out.println("<-------Transaction--------->");
        System.out.println(transaccion.toString());
		transaccion.setAmount(-2000.0f);
		transaccion.setConcept("Pago intereses");
		System.out.println("<-------Modificacion--------->");
		System.out.println(transaccion.toString());*/


	}



}
