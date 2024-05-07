/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author penil
 */
public class Request {
    private int id_request;
    private int id_user_sended;
    private boolean id_user_recieved;
    private int id_group;
    private String status;
    

    
    // Constructor
    public Request(int id_request, int id_user_sended, boolean id_user_recieved, int id_group, String status) {
        this.id_request = id_request;
        this.id_user_sended = id_user_sended;
        this.id_user_recieved = id_user_recieved;
        this.id_group = id_group;
        this.status = status;
    }

    // Getters y Setters
    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public int getId_user_sended() {
        return id_user_sended;
    }

    public void setId_user_sended(int id_user_sended) {
        this.id_user_sended = id_user_sended;
    }

    public boolean getId_user_recieved() {
        return id_user_recieved;
    }

    public void setId_user_recieved(boolean id_user_recieved) {
        this.id_user_recieved = id_user_recieved;
    }
    
    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


/*
No te escucho, creo que se trabo

Propuesta de ricardo
Recibe los datos en un arreglo de string
Si la persona esta en linea, le envie un caraccter especial 
Para que el servidor le diga qn esta en linea o no
Solo si el usuario esta en linea, solo le agrega un caracter

O sea
Pedrito
Pedrito #

isConnected(string)
{
    if(string.contains('#'))
    {
        state = 1;
    }else
    {
        state = 0;
    }
}

*/