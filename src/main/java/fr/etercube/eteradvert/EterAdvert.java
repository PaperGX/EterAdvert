package fr.etercube.eteradvert;

import fr.etercube.eteradvert.command.CommandAdvert;
import fr.etercube.eteradvert.event.InteractInventoryEvent;
import fr.etercube.eteradvert.event.PlayerCloseInventory;
import fr.etercube.eteradvert.fileloader.MessageLoader;
import fr.etercube.eteradvert.fileloader.PermissionLoader;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class EterAdvert extends JavaPlugin {

    // - Stockage Cache MSG

    private HashMap<String, String> permissionList;
    private HashMap<String, String> messageList;
    private ArrayList<Player> listPlayerInAdvert;

    @Override
    public void onEnable() {

        // - Creation fichier de configuration / data

        this.saveResource("config.yml", false);
        this.saveResource("message.yml", false);

        // - Creation HashMap cache

        this.permissionList = new HashMap<>();
        this.messageList = new HashMap<>();
        this.listPlayerInAdvert = new ArrayList<>();

        // - Load HashMap Cache

        new PermissionLoader(this);
        new MessageLoader(this);

        // - Command Manage

        getCommand("advert").setExecutor(new CommandAdvert(this));

        // - Event Manage

        getServer().getPluginManager().registerEvents(new InteractInventoryEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCloseInventory(this), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    // - Getter

    public HashMap<String, String> getPermissionList() {
        return permissionList;
    }

    public HashMap<String, String> getMessageList() {
        return messageList;
    }

    public ArrayList<Player> getListPlayerInAdvert() {
        return listPlayerInAdvert;
    }

    // - Setter


    public void setPermissionList(HashMap<String, String> permissionList) {
        this.permissionList = permissionList;
    }

    public void setMessageList(HashMap<String, String> messageList) {
        this.messageList = messageList;
    }

    public void setListPlayerInAdvert(ArrayList<Player> listPlayerInAdvert) {
        this.listPlayerInAdvert = listPlayerInAdvert;
    }
}
