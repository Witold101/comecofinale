package by.vistar.comeco.controler;

import com.cameco.services.ServicePackageSoft;
import com.cameco.services.ServiceTablesInitDrop;
import com.cameco.services.ServiceUser;

import java.sql.Connection;

/**
 * Класс который умеет разворачивать таблицы в зависимости от купленного пакета,
 * изменять структуру в случае изменения пакета или удалять таблицы в случае окончания услуг.
 */

public class InitDropTables {
    Connection connection;

    public InitDropTables(Connection connection) {
        this.connection = connection;
    }

    public void initUserTable(){
        new ServiceTablesInitDrop(connection).initTable("");
    }



    public void initTablesPackage(String prefix, Integer infoPackage){
        switch (infoPackage){
            case 1:{
            }
            break;
        }

    }

    public void dropTables(String prefix){

    }

    public void editTables(String prefix, Integer newInfoPackage){

    }
}
