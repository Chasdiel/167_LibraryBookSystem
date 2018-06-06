package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper extends BaseMapper <ClientModel> {

    public ClientMapper() {
        super("Clients");
    }

    @Override
    public ClientModel createFromRS(ResultSet rs) {

        try {
            int clientId = rs.getInt("Id");
            String name = rs.getString("Name");

            return new ClientModel(clientId, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
