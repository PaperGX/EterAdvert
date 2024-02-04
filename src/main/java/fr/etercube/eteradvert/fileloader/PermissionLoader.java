package fr.etercube.eteradvert.fileloader;

import fr.etercube.eteradvert.EterAdvert;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class PermissionLoader {
    public PermissionLoader(EterAdvert eterAdvert) {

        File file = new File(eterAdvert.getDataFolder(), "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        HashMap<String, String> permissionList = eterAdvert.getPermissionList();

        permissionList.put("advertuse", yamlConfiguration.getString("permission.advertuse"));
        permissionList.put("seeinventoryclose", yamlConfiguration.getString("permission.seeinventoryclose"));

        eterAdvert.setPermissionList(permissionList);

    }
}
