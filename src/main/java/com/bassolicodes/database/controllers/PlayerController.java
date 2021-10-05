package com.bassolicodes.database.controllers;

import com.bassolicodes.MinecraftCore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerController {

    public static Connection connection;

    public void PlayerController() {
        this.connection = MinecraftCore.getInstance().getStorage().getConnection();
        onEnable();
    }

    public static void onEnable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `user` (`identifier` varchar(32), `json` text);");
            System.out.print("chegou aq");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
