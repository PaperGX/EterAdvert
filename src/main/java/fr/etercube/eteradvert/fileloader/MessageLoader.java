package fr.etercube.eteradvert.fileloader;

import fr.etercube.eteradvert.EterAdvert;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class MessageLoader {
    public MessageLoader(EterAdvert eterAdvert) {

        File file = new File(eterAdvert.getDataFolder(), "message.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        HashMap<String, String> messageList = eterAdvert.getMessageList();

        messageList.put("nopermission", yamlConfiguration.getString("message.nopermission").replace("&", "§"));
        messageList.put("messageformat", yamlConfiguration.getString("message.messageformat").replace("&", "§"));
        messageList.put("playernotonline", yamlConfiguration.getString("message.playernotonline").replace("&", "§"));
        messageList.put("closeinventory", yamlConfiguration.getString("message.closeinventory").replace("&", "§"));

        eterAdvert.setMessageList(messageList);
    }
}
