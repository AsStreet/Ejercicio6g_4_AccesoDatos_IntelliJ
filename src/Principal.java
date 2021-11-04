import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {
        Componente c = new Componente("localhost", "Chat");
        // Apartado A
        Object item = c.consultaEscalar("SELECT count(*) FROM Mensajes WHERE receptor=2");
        System.out.println(item);
        String sentencia = "SELECT * FROM Mensajes WHERE fecha BETWEEN ? AND ?";
        // Apartado B
        List<Object> parametros = new ArrayList<>();
        parametros.add("2021-11-01 15:00:00");
        parametros.add("2021-12-01 17:00:00");
        List<Map> lista = c.consultar(sentencia, parametros);
        for(Map<String, Object> m : lista){
            for(Map.Entry<String, Object> fila : m.entrySet()){
                System.out.println(fila.getKey() + ": " + fila.getValue());
            }
        }
        // Apartado C
        sentencia = "UPDATE Mensajes SET papelera = 'True' WHERE idMensaje=1002";
        int filas = c.ejecutar(sentencia);
        System.out.println(filas);
        // Apartado D
        sentencia = "DELETE FROM Mensajes WHERE idMensaje=?";
        parametros.clear();
        parametros.add(3);
        filas = c.ejecutar(sentencia, parametros);
        if(filas == 0)
            System.out.println("No existe ningún mensaje con ese código");
        else
            System.out.println("Mensaje enviado a la papelera correctamente");
    }

}
