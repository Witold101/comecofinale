package by.vistar.comeco.services.autor;

import by.vistar.comeco.dao.autor.DaoPackageSoft;
import by.vistar.comeco.dao.autor.dto.DtoPackageSoft;
import by.vistar.comeco.entity.autor.PackageSoft;
import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;

import java.sql.Connection;
import java.sql.SQLException;

import static by.vistar.comeco.db.autor.DbConstantsAutor.*;

public class ServicePackageSoft extends ServiceTablesInitDrop implements DaoService<Long, PackageSoft>, ServiceSetup<PackageSoft> {

    private DaoPackageSoft daoPackageSoft;
    private DtoPackageSoft dtoPackageSoft;
    Connection connection;

    public ServicePackageSoft(Connection connection) {
        super(connection);
        this.connection=connection;
        daoPackageSoft = DaoPackageSoft.getInstance();
        dtoPackageSoft = DtoPackageSoft.getInstance();
        try {
            this.daoPackageSoft.initPrepareStatement(connection);
            this.dtoPackageSoft.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement.");
            e.printStackTrace();
        }
    }

    @Override
    public PackageSoft add(PackageSoft packageSoft) {
        if (packageSoft != null) {
            modificationLength(packageSoft);
            startTransaction();
            try {
                daoPackageSoft.add(packageSoft);
            } catch (SQLException e) {
                System.out.println("Error add PACKAGE_SOFT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return packageSoft;
    }

    @Override
    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoPackageSoft.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell PACKAGE_SOFT from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public PackageSoft edit(PackageSoft packageSoft) {
        if (packageSoft != null) {
            modificationLength(packageSoft);
            startTransaction();
            try {
                daoPackageSoft.edit(packageSoft);
            } catch (SQLException e) {
                System.out.println("Error edit PACKAGE_SOFT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return packageSoft;
    }

    @Override
    public PackageSoft get(Long id) {
        PackageSoft packageSoft = null;
        if (id != null) {
            startTransaction();
            try {
                packageSoft = daoPackageSoft.get(id);
            } catch (SQLException e) {
                System.out.println("Error get PACKAGE_SOFT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return packageSoft;
    }

    public PackageSoft getFoKey(Integer key){
        PackageSoft packageSoft = null;
        if (key !=null){
            startTransaction();
            try {
                packageSoft=dtoPackageSoft.getPackageFoKey(key);
            } catch (SQLException e) {
                System.out.println("Error get PackageSoftFoKey in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return packageSoft;
    }

    @Override
    public PackageSoft modificationLength(PackageSoft packageSoft) {
        if (packageSoft != null) {
            if (packageSoft.getName().trim().length() > MAX_LENGTH_NAME) {
                packageSoft.setName(packageSoft.getName().trim().substring(0, MAX_LENGTH_NAME));
            } else {
                packageSoft.setName(packageSoft.getName().trim());
            }
            if (packageSoft.getInfo().trim().length() > MAX_LENGTH_INFO) {
                packageSoft.setInfo(packageSoft.getInfo().trim().substring(0, MAX_LENGTH_INFO));
            } else {
                packageSoft.setInfo(packageSoft.getInfo().trim());
            }
        }
        return packageSoft;
    }
}

